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
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentGiardinoLinguistico2Binding
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.ImageMapping
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiardinoLinguistico2Binding.inflate(inflater, container, false)
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

        loadMappingPositions()

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

                val currPixel = image.getPixel(imageX, imageY)
                val pixelRGBCode = "${Integer.toHexString(currPixel)}".substring(2)

                val objectName = Util.findMappedObject(currentImageMapping,pixelRGBCode,xPerc,yPerc)

                Log.i(TAG, "<image> bitmap width:${imageWidth} height:${imageHeight} touch x:$imageX($xPerc%) touch y:$imageY($yPerc%) color:#$pixelRGBCode objectName: $objectName")
            }

            true
        }

        return binding?.root
    }

    private fun loadMappingPositions() {

        val langJson = context?.assets?.open("mapping_giardino_1.json")?.bufferedReader().use { it?.readText() }

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