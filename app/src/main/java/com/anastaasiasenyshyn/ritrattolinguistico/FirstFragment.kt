package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentFirstBinding
import com.anastaasiasenyshyn.ritrattolinguistico.model.FamilyLanguages
import com.anastaasiasenyshyn.ritrattolinguistico.model.Languages
import com.anastaasiasenyshyn.ritrattolinguistico.slider.SliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    companion object {

        val TAG = "FirstFragment"
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_languages ->
                showLanguageDialog()

            else -> {}
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scopriDiPiuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_scopriDiPiuFragment)
        }

        binding.ritrattoLinguisticoBtn.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_ritrattoLinguisticoFragment2)
            var sliderItems: MutableList<SliderFragment.SliderItem>? = mutableListOf(
                SliderFragment.SliderItem(
                    Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                    "Slide 1",
                    R.drawable.rl_1
                ),
                SliderFragment.SliderItem(
                    Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                    "Slide 2",
                    R.drawable.rl_2
                ),
                SliderFragment.SliderItem(
                    Constants.ID_SLIDER_RITRATTO_LINGUISTICO,
                    "Slide 3",
                    R.drawable.rl_3
                )
            )
            val args = Bundle()
            args.putParcelableArrayList(SliderFragment.SLIDERS, ArrayList(sliderItems))
            findNavController().navigate(
                R.id.action_FirstFragment_to_ritrattoLinguisticoFragment2,
                args
            )
        }

        binding.GiardinoLinguisticoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_giardinoLinguisticoFragment22)
        }


        binding.SistemidiScritturaBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_sistemiDiScritturaFragment)
        }

        checkLanguageSelection()


//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    private fun checkLanguageSelection() {
        val familyLanguage =
            Util.readStringSharedPreference(Constants.SHAR_LANG_SELECTED, requireContext())
        Log.i(TAG, "isLanguageSelected: $familyLanguage")
        if (familyLanguage.isNullOrEmpty()) {
            showLanguageDialog()
        }
    }

    private fun showLanguageDialog() {

        val langJson = context?.assets?.open("languages.json")?.bufferedReader().use { it?.readText() }

        val gson = Gson()
        val languages = gson.fromJson(langJson, Languages::class.java)

        languages.forEach { langItem ->
            Log.i(TAG,"Lang: ${langItem.code} ${langItem.name} ${langItem.nativeName}")
        }

        var initSelection : IntArray = IntArray(0)
        val selectedLang = Util.readStringSharedPreference(Constants.SHAR_LANG_SELECTED,requireContext())
        if (!selectedLang.isNullOrEmpty()){
            val languages : FamilyLanguages = gson.fromJson(selectedLang,FamilyLanguages::class.java)
            initSelection = IntArray(languages.indexes?.size!!)
            languages.indexes?.forEachIndexed { index,value ->
                initSelection[index] = value
            }
        }

        MaterialDialog(requireContext())
            .title(R.string.choose_language_title)
            .message(res = R.string.msg_choose_language_title)
            .positiveButton(text = getString(R.string.dialog_done).toUpperCase()) {

            }
            .negativeButton(text = getString(R.string.dialog_cancel).toUpperCase()) {

            }
            .cancelable(false)
            .show {
                val myItems = listOf("Hello", "World")
                listItemsMultiChoice(initialSelection = initSelection,items = languages.map { it.name as CharSequence }) { _, selectedIndex, text ->
                    Log.i(TAG, "Choosen Item: $text ($selectedIndex)")
                    val familyLanguages = FamilyLanguages()
                    text.forEachIndexed { index, lang ->
                        familyLanguages.languages?.add(lang.toString())
                        familyLanguages.indexes?.add(selectedIndex[index])
                    }
                    Util.writeStringSharedPreference(Constants.SHAR_LANG_SELECTED,gson.toJson(familyLanguages),requireContext())
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}