package com.anastaasiasenyshyn.ritrattolinguistico.slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentDemoBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentRitrattoslide1Binding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentRitrattoslide2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [RitrattoSlideFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RitrattoSlideFragment2 : Fragment() {
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
        val binding: FragmentRitrattoslide2Binding = FragmentRitrattoslide2Binding.inflate(
            inflater, container, false
        )

        val imgText1 = arguments?.getString(RitrattoSliderFragment.SLIDER_TEXT_1)
        val imgId1 = arguments?.getInt(RitrattoSliderFragment.SLIDER_IMG_ID_1)

        val imgText2 = arguments?.getString(RitrattoSliderFragment.SLIDER_TEXT_2)
        val imgId2 = arguments?.getInt(RitrattoSliderFragment.SLIDER_IMG_ID_2)

        binding.rlImage1.setImageResource(imgId1!!)
        binding.payoff1.text = "$imgText1"

        binding.rlImage2.setImageResource(imgId2!!)
        binding.payoff2.text = "$imgText2"
        return binding.root
    }

    companion object {
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
            RitrattoSlideFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}