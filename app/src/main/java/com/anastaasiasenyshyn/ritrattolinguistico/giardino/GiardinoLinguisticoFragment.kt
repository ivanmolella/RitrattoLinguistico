package com.anastaasiasenyshyn.ritrattolinguistico.giardino

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.anastaasiasenyshyn.ritrattolinguistico.Constants
import com.anastaasiasenyshyn.ritrattolinguistico.IOnBackPressed
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentGiardinoLinguistico2Binding
import com.anastaasiasenyshyn.ritrattolinguistico.model.FamilyLanguages
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.ImageMapping
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.Mapping
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 * Use the [GiardinoLinguisticoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GiardinoLinguisticoFragment : Fragment(),IOnBackPressed {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentGiardinoLinguistico2Binding? = null

    var imageX : Int? = null
    var imageY : Int? = null

    var mapW : Int? = 0
    var mapH : Int? = 0

    var currentZone : Int = 0

    var bitmap : Bitmap?=null

    var currentImageMapping : ImageMapping? = null
    var currentGiardinoPage : Int = -1

    var wordListAdapter : WordListAdapter? = null

    var isMapOpen : Boolean? = false

    var handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiardinoLinguistico2Binding.inflate(inflater, container, false)


        setMapBtn()
        loadGardenView(0)

        binding?.imgGiardino?.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                val viewX = event.x.toInt()
                val viewY = event.y.toInt()

                val viewWidth: Int = v.getWidth()
                val viewHeight: Int = v.getHeight()

                val image = ((v as ImageView).drawable as BitmapDrawable).bitmap

                val imageWidth = image.width
                val imageHeight = image.height

                val imageX =
                    (viewX.toFloat() * (imageWidth.toFloat() / viewWidth.toFloat())).toInt()
                val imageY =
                    (viewY.toFloat() * (imageHeight.toFloat() / viewHeight.toFloat())).toInt()

                val xPerc=(imageX*100)/imageWidth
                val yPerc=(imageY*100)/imageHeight

                val currPixel = image.getPixel(imageX, imageY )
                val pixelRGBCode = "${Integer.toHexString(currPixel)}".substring(2)

                val objMapping = Util.findMappedObject(currentImageMapping,pixelRGBCode,xPerc,yPerc)
                if (objMapping == null){
                    Toast.makeText(requireContext(),getString(R.string.no_image_detected),Toast.LENGTH_SHORT).show()
                }else {
                    showTranslateWordDialog(objMapping)
                }
                Log.i(TAG, "<image> bitmap width:${imageWidth} height:${imageHeight} touch x:$imageX($xPerc%) touch y:$imageY($yPerc%) color:#$pixelRGBCode objectName: $objMapping")
                hideMapPanel(200)
            }

            true
        }

        return binding?.root
    }

    private fun setMapBtn() {
        initMap()
        binding?.btnMap?.viewTreeObserver?.addOnGlobalLayoutListener { hideMapPanel(0) }
        binding?.btnMap?.setOnClickListener {
            if (isMapOpen == true){
                hideMapPanel(200)
            }else{
                showMapPanel(200)
            }
        }

        binding?.map?.setOnTouchListener { view, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                val zone  = evalMapZone(event)
                Log.i(TAG,"<map> Map Touch on: ${event.x} ${event.y} zone: $zone")

                when(zone){
                    "00" -> {
                        changeCurrentPage(1)
                    }

                    "10" -> {
                        changeCurrentPage(2)
                    }
                    "01" -> {
                        changeCurrentPage(4)
                    }
                    "11" -> {
                        changeCurrentPage(3)
                    }
                }
            }

            true
        }
    }

    private fun evalMapZone(event: MotionEvent?): String {
        var xZone : Int = 0
        var yZone : Int = 0

        if (event?.x!! < mapW!! / 2){
            xZone = 0
        }else{
            xZone = 1
        }

        if (event?.y!! < mapH!! / 2){
            yZone = 0
        }else{
            yZone = 1
        }

        return "${xZone}${yZone}"

    }

    private fun initMap() {
        binding?.map?.viewTreeObserver?.addOnGlobalLayoutListener {
            val image = (( binding?.map as ImageView).drawable as BitmapDrawable).bitmap
            mapW =   binding?.map?.width
            mapH =  binding?.map?.height
            Log.i(TAG,"<map> mapW: $mapW mapH: $mapH")
        }
    }

    private fun changeCurrentPage(page: Int) {
        loadGardenView(page)
        hideMapPanel(200)
    }

    private fun hideMapPanel(duration : Long) {

        isMapOpen = false
        binding?.mapPanel!!.animate()
            .scaleX(0.0f)
            .scaleY(0.0f)
//            .translationY(1 * (binding?.mapPanel?.height!!/2.0f))
//            .translationX(1 * (binding?.mapPanel?.width!!/2.0f))// move to bottom / right
            .translationY(-1 * (binding?.mapPanel?.height!!.toFloat() / 2))
            .translationX(1 * (binding?.mapPanel?.width!!.toFloat() / 2))// m
            .withEndAction{
                binding?.mapPanel?.visibility=View.INVISIBLE
            }
            .duration = duration
    }

    private fun showMapPanel(duration : Long) {
        isMapOpen = true
        binding?.mapPanel!!.animate()
            .scaleX(1.0f)
            .scaleY(1.0f)
            .translationY(1 * (binding?.mapPanel?.height!!/16.0f).toFloat())
            .translationX(1 * (binding?.mapPanel?.width!!/256.0f).toFloat())
            .withStartAction{
                binding?.mapPanel?.visibility=View.VISIBLE
            }
            .duration = duration
    }


    private fun loadGardenView(viewNumber: Int) {
        currentZone=viewNumber
        Log.i(TAG,"Zone: $currentZone")
        var title = when (currentZone) {

            0 -> "${getString(R.string.giardino_linguistico)}"
            1 -> "${getString(R.string.zone_cucina)}"
            2 -> "${getString(R.string.zone_salotto)}"
            3 -> "${getString(R.string.zone_camera_da_letto)}"
            4 -> "${getString(R.string.zone_bagno)}"

            else -> "${getString(R.string.giardino_linguistico)}"

        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = title;


        Log.i(TAG,"loadGardenView: $viewNumber")
        loadView(viewNumber)
        loadMappingPositions(viewNumber)

        bitmap = binding!!.imgGiardino.drawable.toBitmap()

        binding?.imgGiardino?.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding?.imgGiardino?.viewTreeObserver?.removeGlobalOnLayoutListener(this)
                imageX = binding?.imgGiardino?.width
                imageY = binding?.imgGiardino?.height
                Log.i(TAG, "<image> w($imageX}) h(${imageY})")
            }
        })
        currentGiardinoPage=viewNumber
    }

    private fun loadView(viewNumber: Int) : Boolean{

        var viewFound = false
        when(viewNumber){
            0 -> {
                if (currentGiardinoPage != -1){
                    loadImageWithAnimation(R.drawable.giardino_linguistico_0)
                }else{
                    binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_0)
                }
                viewFound=true
            }
            1 -> {
                if (currentGiardinoPage != -1){
                    loadImageWithAnimation(R.drawable.giardino_linguistico_1)
                }else{
                    binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_1)
                }
                viewFound=true
            }
            2 -> {
                if (currentGiardinoPage != -1){
                    loadImageWithAnimation(R.drawable.giardino_linguistico_2)
                }else{
                    binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_2)
                }
                viewFound=true
            }
            3 -> {
                if (currentGiardinoPage != -1){
                    loadImageWithAnimation(R.drawable.giardino_linguistico_3)
                }else{
                    binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_3)
                }
                viewFound=true
            }
            4 -> {
                if (currentGiardinoPage != -1){
                    loadImageWithAnimation(R.drawable.giardino_linguistico_4)
                }else{
                    binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_4)
                }
                viewFound=true
            }
        }

        return viewFound
    }

    private fun loadImageWithAnimation(imageId: Int) {
        binding?.imgGiardino?.animate()?.alpha(0.6f)?.setDuration(200)?.withEndAction(Runnable {
            handler.post {
                binding?.imgGiardino?.setImageResource(imageId)
                binding?.imgGiardino?.animate()?.alpha(1.0f)?.duration = 500
            }
        })
    }

    private fun showTranslateWordDialog(objMapping: Mapping) {

        val dialog = MaterialDialog(requireActivity())
            .title(text = objMapping.itemItalianName?.capitalize())
            .customView(R.layout.translate_word_dialog)
            .positiveButton(text = getString(R.string.btn_close).toUpperCase())
            .cancelable(true)
        val viewDialog = dialog.getCustomView()
        
        loadCustomView(viewDialog,objMapping)

        dialog.show {

        }

        //mPromemoriaInDialog = viewDialog!!.findViewById(R.id.dialog_promemoria_text_promemoria)

    }

    private fun loadCustomView(viewDialog: View, objMapping: Mapping) {
        val familyLanguage =
            Util.readStringSharedPreference(Constants.SHAR_LANG_SELECTED, requireContext())

        val gson = Gson()
        val selectedLanguages = gson.fromJson(familyLanguage,FamilyLanguages::class.java)
        if (selectedLanguages != null) {
            Log.i(TAG, "familyLanguage: $selectedLanguages")
        }
        val itemList = buildWordItemList(selectedLanguages)
        val wordListRecycler : RecyclerView = viewDialog.findViewById(R.id.word_list) as RecyclerView
        val saveWordPanel : View = viewDialog.findViewById(R.id.saveWordPanel)
        val editWord : EditText = viewDialog.findViewById(R.id.editWord)
        val btnSave : View = viewDialog.findViewById(R.id.btnSaveWord)
        btnSave.isClickable = true
        wordListAdapter = WordListAdapter(requireContext(),itemList,currentGiardinoPage,objMapping){ itemPage,itemItalianName,itemLang ->
            Log.i(TAG,"OnClick pressed: $itemPage-$itemItalianName-${Util.trim(itemLang)}")
            saveWordPanel.visibility=View.VISIBLE
            btnSave.setOnClickListener {
                saveWordPanel.visibility=View.GONE
                val itemTranslated = editWord.text.toString()
                editWord.setText("")
                saveTranslatedWord(itemPage,itemItalianName,itemLang,itemTranslated)
            }
        }
        wordListRecycler.adapter=wordListAdapter
        wordListRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    private fun saveTranslatedWord(itemPage: Int, itemItalianName: String, itemLang: String, itemTranslated: String?) {
        val key = Util.getTranslatedWordKey(itemPage,itemItalianName,itemLang)
        Log.i(TAG,"Translated key (write): $key --> $itemTranslated")
        Util.writeStringSharedPreference(key,itemTranslated!!,requireContext())
        wordListAdapter?.notifyDataSetChanged()
    }

    private fun buildWordItemList(selectedLanguages: FamilyLanguages?) : List<WordListItem>{
        val itemList : MutableList<WordListItem> = mutableListOf()
        selectedLanguages?.languages?.forEach {
            val item = WordListItem()
            item.word=it
            itemList.add(item)
        }
        return itemList
    }

    private fun loadMappingPositions(viewNumber: Int) {

        val langJson = context?.assets?.open("mapping_giardino_${viewNumber}.json")?.bufferedReader().use { it?.readText() }

        val gson = Gson()
        currentImageMapping = gson.fromJson(langJson, ImageMapping::class.java)
        Log.i(TAG,"currentImageMapping: $currentImageMapping")
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    companion object {
        const val TAG = "GiardinoLinguisticoFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GiardinoLinguisticoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GiardinoLinguisticoFragment().apply {
//
            }
    }

    override fun onBackPressed(): Boolean {
        if (currentZone == 0){
           return false
        }else{
            loadGardenView(0)
            return true
        }
    }
}