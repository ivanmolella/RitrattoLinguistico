package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentAltreRisorseBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AltreRisorse.newInstance] factory method to
 * create an instance of this fragment.
 */
class AltreRisorse : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAltreRisorseBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentAltreRisorseBinding.inflate(inflater, container, false)

        binding.moreContentLink1.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink2.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink3.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink4.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink5.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink6.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink7.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink8.movementMethod = LinkMovementMethod.getInstance()
        binding.moreContentLink9.movementMethod = LinkMovementMethod.getInstance()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SistemiDiScritturaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AltreRisorse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}