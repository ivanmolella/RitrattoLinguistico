package com.anastaasiasenyshyn.ritrattolinguistico.ritratto

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anastaasiasenyshyn.ritrattolinguistico.BuildConfig
import com.anastaasiasenyshyn.ritrattolinguistico.Constants
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentRitrattoLinguisticoBinding
import com.anastaasiasenyshyn.ritrattolinguistico.slider.RitrattoSliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.DeviceInfo
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util
import java.io.File
import java.io.FileOutputStream


/**
 * A simple [Fragment] subclass.
 * Use the [RitrattoLinguisticoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * Anastasia: Creare 3/4 immagini di spiegazione del Ritratto Linguistico da inserire nel carosello (Anche con PowerPoint)
 * Ivan: Creazione del fragment del ritratto linguistico.
 *
 */

data class Color(val color: Int,val colorName: String)

interface ColorSelectionListener {
    fun onColorSelected(color : Int,colorName : String)
}

class RitrattoLinguisticoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentRitrattoLinguisticoBinding
    private var sliderFragment: RitrattoSliderFragment? = null
    private var isPaletteOpen : Boolean = false
    private var isPaintSliderOpen : Boolean = false
    private var painterSize : Float? = 6.0f

    private var isFooterOpen : Boolean = false

    private var adapter : ColorPaletteAdapter? = null
    private var colorPalette : MutableList<Color>? = mutableListOf()

    private val handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRitrattoLinguisticoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        //TODO Scommentare
        val isSlideRequired = true //checkSlideRequired()
        if (isSlideRequired) {
            initViewForSlider()
        } else {
            initViewForRitratto()
        }
    }

    fun showRitratto() {
        initViewForRitratto()
    }

    private fun initViewForRitratto() {
        Log.i(TAG, "initViewForRitratto")
        binding.ritratto.visibility = View.VISIBLE
        binding.slider.visibility = View.GONE

        initFooterPalette()
        initHeaderButtons()
    }

    private fun initHeaderButtons() {
        binding.btnSaveRitratto.setOnClickListener {
            binding.drawImageView.isDrawingCacheEnabled = true
            val bmp  = binding.drawImageView.drawingCache
            //val bmp: Bitmap = drawable.bitmap

            saveBitmapRitratto(bmp)
        }

        binding.btnShareRitratto.setOnClickListener {
            shareBitmapRistratto()
        }

    }

    private fun shareBitmapRistratto() {
        binding.drawImageView.isDrawingCacheEnabled = true
        val bmp  = binding.drawImageView.drawingCache
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg";
        val sharingFile = getSharingFile(bmp)

        val uri = FileProvider.getUriForFile(
            requireContext(),
            BuildConfig.APPLICATION_ID + ".provider",
            sharingFile
        )

        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));

    }

    private fun getSharingFile(bmp: Bitmap): File {
        val dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        if (!dir?.exists()!!) dir?.mkdirs()
        val file = File(dir, "ritratto.png")
        val fOut = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()

        return file
    }

    private fun saveBitmapRitratto(bmp: Bitmap) {
        val dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        if (!dir?.exists()!!) dir?.mkdirs()
        val file = File(dir, "ritratto.png")
        val fOut = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut)
        fOut.flush()
        fOut.close()
        Log.i(TAG,"saveBitmapRitratto in: ${file.path}")
        Toast.makeText(requireContext(),getString(R.string.ritratto_saved),Toast.LENGTH_SHORT).show()
    }

    private fun initFooterPalette() {
        initColorPalette()
        initPaintSlider()
        hideFooterPalette(0)
        setPaletteRecycle()
        binding.seekBarPanel.isClickable = true
        binding.footerPalette.isClickable = true
        binding.footerPalette.setOnClickListener {
            if (isFooterOpen){
                hideFooterPalette()
            }else{
                showFooterPalette()
            }
        }

        binding.btnUndo.setOnClickListener {
            binding.drawImageView.onClickUndo()
        }

        val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                hidePalettePanel(0)
                hidePaintSlider(0)
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(listener)



        binding.btnPalette.setOnClickListener {
            if (isPaletteOpen){
                hidePalettePanel(300)
            }else{
                showPalettePanel(300)
            }
        }

        binding.btnPaintSlider.setOnClickListener {
            if (isPaintSliderOpen){
                hidePaintSlider(300)
            }else{
                showPaintSlider(300)
            }
        }


    }

    private fun initPaintSlider() {
        val initStroke = 32.0f
        binding.drawImageView.setPaintStroke(initStroke/2)
        binding.seekBarPainter.progress = initStroke.toInt()
        binding.seekBarCaption.text = "${getString(R.string.stroke)} ${initStroke/2}"

        binding.seekBarPainter.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val stroke = (p1/2).toFloat()
                Log.i(TAG,"<seekBarPainter> onProgressChanged stroke: $stroke")
                binding.drawImageView.setPaintStroke(stroke)
                binding.seekBarCaption.text = "${getString(R.string.stroke)} $stroke"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.i(TAG,"<seekBarPainter> onStartTrackingTouch: $p0")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.i(TAG,"<seekBarPainter> onStopTrackingTouch: $p0")
            }
        })
    }

    private fun initColorPalette() {

        val colorNames = resources.getStringArray(com.anastaasiasenyshyn.ritrattolinguistico.R.array.colorNames)
        for (i in colorNames.indices) {
            //Getting the color resource id
            val ta = resources.obtainTypedArray(com.anastaasiasenyshyn.ritrattolinguistico.R.array.colors)
            val colorToUse = ta.getResourceId(i, 0)
            Log.i(TAG,"### Color: ${colorNames[i]} - ${String.format("0x%08X",colorToUse)}")

            colorPalette?.add(Color(colorToUse,colorNames[i]))
        }
    }

    private fun setPaletteRecycle() {
        var palette : MutableList<ColorItem> = mutableListOf()
        var curColorItem : ColorItem? = ColorItem()
        var colorItemIndex : Int = 0
        colorPalette?.forEachIndexed { _, color ->
            when(colorItemIndex){
                0 -> {
                    curColorItem?.color1 = color.color
                    curColorItem?.color1Name = color.colorName
                    colorItemIndex++
                }

                1 -> {
                    curColorItem?.color2 = color.color
                    curColorItem?.color2Name = color.colorName
                    colorItemIndex++
                }

                2 -> {
                    curColorItem?.color3 = color.color
                    curColorItem?.color3Name = color.colorName
                    palette.add(curColorItem!!)
                    colorItemIndex=0
                    curColorItem = ColorItem()
                }
            }
        }

        adapter = ColorPaletteAdapter(requireContext(), palette, object : ColorSelectionListener {
            override fun onColorSelected(color: Int,colorName: String) {
                Log.i(TAG,"onColorSelected: $colorName - ${String.format("0x%08X",color)}")
                binding.drawImageView.setPaintColor(color)
                hidePalettePanel(300)
                val msg = "${getString(R.string.new_selected_color)} $colorName"
                Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
            }
        })
        binding.paletteColorList.adapter = adapter
        binding.paletteColorList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun hidePalettePanel(duration : Long) {

        isPaletteOpen = false
        binding.paletteColorPanel.animate()
            .scaleX(0.0f)
            .scaleY(0.0f)
            .translationY(1 * (binding.paletteColorPanel?.height!!/2.0f))
            .translationX(1 * (binding.paletteColorPanel?.width!!/2.0f))// move to bottom / right
            .withEndAction{
                binding.paletteColorPanel.visibility=View.INVISIBLE
            }
            .duration = duration
    }

    private fun hidePaintSlider(duration : Long) {
        Log.i(TAG,"hidePaintSlider called with duration: $duration")
        isPaintSliderOpen = false
        binding.seekBarPanel.animate()
            .alpha(0.0f)
            .withEndAction(){
                binding.seekBarPanel.visibility=View.INVISIBLE
            }
            .duration = duration
    }

    private fun showPaintSlider(duration : Long) {
        Log.i(TAG,"showPaintSlider called")

        isPaintSliderOpen = true
        binding.seekBarPanel.animate()
            .alpha(1.0f)
            .withStartAction{
                binding.seekBarPanel.visibility=View.VISIBLE
            }
            .duration = duration
    }

    private fun showPalettePanel(duration : Long) {
        isPaletteOpen = true
        binding.paletteColorPanel.animate()
            .scaleX(1.0f)
            .scaleY(1.0f)
            .translationY(-1 * (binding.paletteColorPanel?.height!!/16.0f).toFloat())
            .translationX(-1 * (binding.paletteColorPanel?.width!!/256.0f).toFloat())
            .withStartAction{
                binding.paletteColorPanel.visibility=View.VISIBLE
            }
            .duration = duration
    }

    private fun initViewForSlider() {
        Log.i(TAG, "initViewForSlider")
        binding.ritratto.visibility = View.GONE
        binding.slider.visibility = View.VISIBLE
        loadSliderFragment()
        hideFooterPaletteTotal()
    }

    private fun loadSliderFragment() {
        sliderFragment = RitrattoSliderFragment.newInstance(null, null)
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.slider, sliderFragment!!)

        var sliderItems: MutableList<RitrattoSliderFragment.SliderItem>? = mutableListOf(
            RitrattoSliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "1 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
                "2 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
                ),
            RitrattoSliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "3 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
                "4 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
            ),
            RitrattoSliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "5 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
                "6 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
            ),
            RitrattoSliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "7 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
                "8 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                R.drawable.img_avatar_ritratto_linguistico,
            )
        )
        val sliderPolicy : RitrattoSliderFragment.SliderPolicy =
            RitrattoSliderFragment.SliderPolicy(autostartSlideShow = false)
        val args = Bundle()
        args.putParcelableArrayList(RitrattoSliderFragment.SLIDERS, ArrayList(sliderItems))
        args.putParcelable(RitrattoSliderFragment.SLIDER_POLICY, sliderPolicy)

        sliderFragment?.arguments = args

        Util.commitIfActivityAlive(requireActivity(), fragmentTransaction)
    }

    private fun checkSlideRequired(): Boolean {
        val isRequired =
            Util.readBooleanSharedPreference(Constants.SHAR_SLIDE_RITRATTO_DONE, requireContext())
        Log.i(TAG, "checkSlideRequired: $isRequired")
        return isRequired ?: true
    }

    private fun showFooterPalette() {
        binding.footerPalette.visibility= View.VISIBLE
        binding.footerPaletteFrame!!.animate().y(0.0f).duration = 200
        isFooterOpen = true
        Log.i(TAG, "showFooterPalette called")
    }

    private fun hideFooterPalette(duration : Long = 200) {
        binding.footerPalette.visibility= View.VISIBLE
        val numePrenotazioniPanelHeight = DeviceInfo.dpiToPx(60)
        binding.footerPaletteFrame!!.animate().y(numePrenotazioniPanelHeight.toFloat()).duration = duration
        isFooterOpen = false
        Log.i(TAG, "hideFooterPalette called")
        if (isPaletteOpen){
            hidePalettePanel(300)
        }
        if (isPaintSliderOpen){
            hidePaintSlider(300)
        }
    }

    private fun hideFooterPaletteTotal(duration : Long = 200) {
        hidePalettePanel(0)
        binding.footerPalette.visibility= View.GONE
        val numePrenotazioniPanelHeight = DeviceInfo.dpiToPx(120)
        binding.footerPaletteFrame!!.animate().y(numePrenotazioniPanelHeight.toFloat()).duration = duration
        isFooterOpen = false
        Log.i(TAG, "hideFooterPalette called")
    }

    companion object {

        const val TAG = "RitrattoLinguisticoFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RitrattoLinguisticoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RitrattoLinguisticoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}