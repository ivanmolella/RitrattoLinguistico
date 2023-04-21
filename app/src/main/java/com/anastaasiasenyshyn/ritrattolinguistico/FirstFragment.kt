package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentFirstBinding
import com.anastaasiasenyshyn.ritrattolinguistico.slider.SliderFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scopriDiPiuBtn.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_scopriDiPiuFragment)
        }

        binding.ritrattoLinguisticoBtn.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_ritrattoLinguisticoFragment2)
            var sliderItems : MutableList<SliderFragment.SliderItem>? = mutableListOf(
                SliderFragment.SliderItem(
                    Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                    "Slide 1",
                    R.drawable.rl_1
                ),
                SliderFragment.SliderItem(Constants.ID_SLIDER_RITRATTO_LINGUISTICO,"Slide 2", R.drawable.rl_2),
                SliderFragment.SliderItem(Constants.ID_SLIDER_RITRATTO_LINGUISTICO,"Slide 3", R.drawable.rl_3)
            )
            val args = Bundle()
            args.putParcelableArrayList(SliderFragment.SLIDERS, ArrayList(sliderItems))
            findNavController().navigate(R.id.action_FirstFragment_to_ritrattoLinguisticoFragment2,args)
        }

        binding.GiardinoLinguisticoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_giardinoLinguisticoFragment22)
        }


       binding.SistemidiScritturaBtn.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_sistemiDiScritturaFragment)
        }


//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}