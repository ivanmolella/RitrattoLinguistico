package com.anastaasiasenyshyn.ritrattolinguistico.giardino

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
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
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.anastaasiasenyshyn.ritrattolinguistico.Constants
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
class GiardinoLinguisticoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentGiardinoLinguistico2Binding? = null

    var imageX : Int? = null
    var imageY : Int? = null

    var bitmap : Bitmap?=null

    var currentImageMapping : ImageMapping? = null
    var currentGiardinoPage : Int = 0

    var wordListAdapter : WordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiardinoLinguistico2Binding.inflate(inflater, container, false)


        loadGardenView(1)

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
            }

            true
        }

        return binding?.root
    }

    private fun loadGardenView(viewNumber: Int) {
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
    }

    private fun loadView(viewNumber: Int) : Boolean{

        var viewFound = false
        when(viewNumber){
            0 -> {
                binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_0)
                viewFound=true
            }
            1 -> {
                binding?.imgGiardino?.setImageResource(R.drawable.giardino_linguistico_1)
                viewFound=true
            }
        }

        return viewFound
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
}