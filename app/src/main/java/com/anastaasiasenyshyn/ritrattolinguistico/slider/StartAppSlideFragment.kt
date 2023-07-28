package com.anastaasiasenyshyn.ritrattolinguistico.slider

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentStartappslideBinding
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment.Companion.SLIDER_IMG_ID
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment.Companion.SLIDER_POSITION
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment.Companion.SLIDER_TEXT


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartAppSlideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartAppSlideFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val page = arguments?.getString("PAGE")
        // Inflate the layout for this fragment
        val binding: FragmentStartappslideBinding = FragmentStartappslideBinding.inflate(
            inflater, container, false)

        val imgText = arguments?.getString(SLIDER_TEXT)
        val imgId = arguments?.getInt(SLIDER_IMG_ID)
        val position = arguments?.getInt(SLIDER_POSITION)

        Log.i(TAG,"StartAppSlideFragment id: $position")

        binding.rlImage.setImageResource(imgId!!)
        binding.payoff.text = "${imgText}"

        return binding.root
    }

    companion object {

        const val TAG = "StartAppSlideFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DemoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartAppSlideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}