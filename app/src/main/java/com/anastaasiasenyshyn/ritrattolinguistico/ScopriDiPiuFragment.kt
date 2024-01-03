package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentScopriDiPiuBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScopriDiPiuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScopriDiPiuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentScopriDiPiuBinding? = null
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
        _binding = FragmentScopriDiPiuBinding.inflate(inflater, container, false)
        setPanel(binding.btnExpand1,binding.testo12)
        setPanel(binding.btnExpand2,binding.testo22)

        setPanel(binding.btnExpand4,binding.testo42)
        setPanel(binding.btnExpand5,binding.testo52)
        setPanel(binding.btnExpand6,binding.testo6Panel)
        setPanel(binding.btnExpand7,binding.testo72)
        setPanel(binding.btnExpand8,binding.testo8Panel)
        setPanel(binding.btnExpand9,binding.testo92)
        setPanel(binding.btnExpand10,binding.testo10Panel)
        setPanel(binding.btnExpand11,binding.testo11Panel)

        return _binding!!.root
    }

    private fun setPanel(btnExpand: TextView, testo2: View) {
        testo2.visibility=View.GONE
        btnExpand.setOnClickListener {
            if (testo2.visibility == View.GONE){
                testo2.visibility=View.VISIBLE
            }else{
                testo2.visibility=View.GONE
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScopriDiPiuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScopriDiPiuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}