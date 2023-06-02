package com.anastaasiasenyshyn.ritrattolinguistico

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentGiardinoLinguistico2Binding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiardinoLinguistico2Binding.inflate(inflater, container, false)

        binding?.imgGiardino?.viewTreeObserver?.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding?.imgGiardino?.viewTreeObserver?.removeGlobalOnLayoutListener(this)
                val w: Int? = binding?.imgGiardino?.width
                val h: Int? = binding?.imgGiardino?.height
                Log.i(TAG,"w($w}) h(${h})")
            }
        })

        binding?.imgGiardino?.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                val screenX: Float = event.getX()
                val screenY: Float = event.getY()
                val viewX: Float = screenX - v.getLeft()
                val viewY: Float = screenY - v.getTop()
                Log.i(TAG,"OnTouchListener: screenX pixel ${screenX} screenY ${screenY} - viewX $viewX viewY $viewY")
                Log.i(TAG,"OnTouchListener: screenX dp ${convertPixelsToDp(screenX,requireContext())} screenY ${convertPixelsToDp(screenY,requireContext())} - viewX $viewX viewY $viewY")
                true
            }else{
                Log.i(TAG,"OnTouchListener called");
            }
            false
        }

        return binding?.root
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
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}