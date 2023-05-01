package com.anastaasiasenyshyn.ritrattolinguistico.slider

import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.FragmentPagerBinding

import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SliderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SliderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentPagerBinding
    private var viewPager: ViewPager2? = null

    val handler : Handler = Handler(Looper.getMainLooper())

    var counter = 0

    var currentPagerPosition : Int = 0

    var sliderId : String? = null
    var autostartSlideShow : Boolean? = null

    interface SliderActions{
        fun onSliderExit(pagerId : String)
    }

    data class SliderPolicy (
        val autostartSlideShow : Boolean?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readValue(Boolean::class.java.classLoader) as? Boolean) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(autostartSlideShow)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SliderPolicy> {
            override fun createFromParcel(parcel: Parcel): SliderPolicy {
                return SliderPolicy(parcel)
            }

            override fun newArray(size: Int): Array<SliderPolicy?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class SliderItem(
        val sliderId : String? = null,
        val sliderText: String? = null,
        val sliderImageId: Int? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(sliderId)
            parcel.writeString(sliderText)
            parcel.writeValue(sliderImageId)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SliderItem> {
            override fun createFromParcel(parcel: Parcel): SliderItem {
                return SliderItem(parcel)
            }

            override fun newArray(size: Int): Array<SliderItem?> {
                return arrayOfNulls(size)
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(val frag: Fragment, val sliderItems: List<SliderItem>?) :
        FragmentStateAdapter(frag) {
        override fun getItemCount(): Int = sliderItems?.size!!

        override fun createFragment(position: Int): Fragment {
            val frag = SlideFragment()
            val sliderItem = sliderItems?.get(position)
            val bundle = Bundle()
            bundle.putString(SLIDER_TEXT, sliderItem?.sliderText)
            bundle.putInt(SLIDER_IMG_ID, sliderItem?.sliderImageId!!)
            bundle.putInt(SLIDER_NUM_ITEM, sliderItems?.size!!)
            bundle.putInt(SLIDER_POSITION, position)
            frag.arguments = bundle
            Log.i(TAG,"createFragment $position")

            return frag
        }
    }

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

        binding = FragmentPagerBinding.inflate(layoutInflater)
        viewPager = binding.pgrPager

        val sliderItems : List<SliderItem> = arguments?.getParcelableArrayList(SLIDERS)!!
        val silderPolicy : SliderPolicy? = arguments?.getParcelable(SLIDER_POLICY)
        autostartSlideShow = (silderPolicy?.autostartSlideShow) ?: true
        if (sliderItems.isNotEmpty()){
            sliderId = sliderItems[0].sliderId
        }
        val pagerAdapter = ScreenSlidePagerAdapter(this,sliderItems)
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val btn_msg = if (position == sliderItems.size -1) "FINE" else "AVANTI"
                currentPagerPosition = position
                binding.nextSlideBtn.text = btn_msg
            }
        })

        TabLayoutMediator(
            binding.pgrTabs, viewPager!!
        ) { tab, position -> // Styling each tab here
            //tab.text = "Tab $position"
        }.attach()

        if (autostartSlideShow == true) {
            nextItem(sliderItems.size)
        }

        binding.nextSlideBtn.setOnClickListener{
            if (currentPagerPosition == sliderItems.size -1){
                if ((requireActivity() is SliderActions)){
                    (requireActivity() as SliderActions).onSliderExit(sliderId!!)
                }
            }else{
                handler.removeCallbacksAndMessages(null)
                binding.pgrTabs.getTabAt(currentPagerPosition + 1)?.select()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    fun nextItem(size: Int) {
        handler.postDelayed(object : Runnable {
            override fun run() {
                counter++
                viewPager?.currentItem = counter
                if (counter < size) {
                    nextItem(size)
                } else {
                    //TODO
                }
            }
        }, 3000)
    }

    companion object {

        const val TAG = "PagerFragment"

        const val SLIDERS = "SLIDERS"
        const val SLIDER_POLICY = "SLIDER_POLICY"
        const val SLIDER_TEXT = "SLIDER_TEXT"
        const val SLIDER_IMG_ID = "SLIDER_IMG_ID"
        const val SLIDER_NUM_ITEM = "SLIDER_ITEMS"
        const val SLIDER_POSITION = "SLIDER_POSITION"
        const val SLIDER_ID = "SLIDER_ID"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String?, param2: String?) =
            SliderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}