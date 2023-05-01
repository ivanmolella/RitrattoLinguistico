package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentFirstBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentRitrattoLinguisticoBinding
import com.anastaasiasenyshyn.ritrattolinguistico.slider.SliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RitrattoLinguisticoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * Anastasia: Creare 3/4 immagini di spiegazione del Ritratto Linguistico da inserire nel carosello (Anche con PowerPoint)
 * Ivan: Creazione del fragment del ritratto linguistico.
 *
 */

class RitrattoLinguisticoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentRitrattoLinguisticoBinding
    private var sliderFragment: SliderFragment? = null

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
        binding = FragmentRitrattoLinguisticoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        initView()

        return binding.root
    }

    private fun initView() {
        val isSlideRequired = checkSlideRequired()
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
    }

    private fun initViewForSlider() {
        Log.i(TAG, "initViewForSlider")

        binding.ritratto.visibility = View.GONE
        binding.slider.visibility = View.VISIBLE
        loadSliderFragment()
    }

    private fun loadSliderFragment() {
        sliderFragment = SliderFragment.newInstance(null, null)
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.slider, sliderFragment!!)

        var sliderItems: MutableList<SliderFragment.SliderItem>? = mutableListOf(
            SliderFragment.SliderItem(
                Constants.ID_SLIDER_AFTER_SPLASH,
                "",
                R.drawable.carosello_ritratto_linguistico_1
            ),
            SliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "",
                R.drawable.carosello_ritratto_linguistico_2
            ),
            SliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "",
                R.drawable.carosello_ritratto_linguistico_3
            ),
            SliderFragment.SliderItem(
                Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                "",
                R.drawable.carosello_ritratto_linguistico_4
            )

        )
        val sliderPolicy : SliderFragment.SliderPolicy = SliderFragment.SliderPolicy(autostartSlideShow = false)
        val args = Bundle()
        args.putParcelableArrayList(SliderFragment.SLIDERS, ArrayList(sliderItems))
        args.putParcelable(SliderFragment.SLIDER_POLICY, sliderPolicy)

        sliderFragment?.arguments = args

        Util.commitIfActivityAlive(requireActivity(), fragmentTransaction)
    }

    private fun checkSlideRequired(): Boolean {
        val isRequired =
            Util.readBooleanSharedPreference(Constants.SHAR_SLIDE_RITRATTO_DONE, requireContext())
        Log.i(TAG, "checkSlideRequired: $isRequired")
        return isRequired ?: true
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
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}