package com.anastaasiasenyshyn.ritrattolinguistico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityDemoBinding
import com.anastaasiasenyshyn.ritrattolinguistico.slider.SliderActions

import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSlideFragment
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment.Companion.SLIDERS
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment.Companion.SLIDER_POLICY
import com.anastaasiasenyshyn.ritrattolinguistico.util.AnimationUtil
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util

class DemoActivity : AppCompatActivity(), SliderActions {

    companion object {
        const val TAG = "DemoActivity"
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            val frag = StartAppSlideFragment()
            val bundle = Bundle()
            bundle.putString("PAGE",position.toString())
            frag.arguments = bundle
            return frag
        }
    }

    private lateinit var binding: ActivityDemoBinding
    private var viewPager: ViewPager2? = null

    private var pagerFragment : StartAppSliderFragment? = null

    val handler : Handler = Handler(Looper.getMainLooper())
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragment()

    }

    private fun initFragment() {

        pagerFragment = StartAppSliderFragment.newInstance(null, null)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.entry_point, pagerFragment!!)

        var sliderItems : MutableList<StartAppSliderFragment.SliderItem>? = mutableListOf(
            StartAppSliderFragment.SliderItem(
                Constants.ID_SLIDER_AFTER_SPLASH,
                getString(R.string.consapevolezza_linguistica),
                R.drawable.rl_1
            ),
            StartAppSliderFragment.SliderItem(Constants.ID_SLIDER_AFTER_SPLASH,getString(R.string.identita_linguistica), R.drawable.rl_2),
            StartAppSliderFragment.SliderItem(Constants.ID_SLIDER_AFTER_SPLASH,getString(R.string.lessico_plurilinguistico), R.drawable.rl_3nuovo)
        )
        val sliderPolicy : StartAppSliderFragment.SliderPolicy = StartAppSliderFragment.SliderPolicy(autostartSlideShow = true)
        val args = Bundle()
        args.putParcelableArrayList(SLIDERS, ArrayList(sliderItems))
        args.putParcelable(SLIDER_POLICY, sliderPolicy)

        pagerFragment?.arguments = args

        Util.commitIfActivityAlive(this, fragmentTransaction)
    }

    override fun onSliderExit(pagerId : String) {
        Log.i(TAG,"onSliderExit called! ($pagerId)")
        Util.writeBooleanSharedPreference(Constants.SHAR_SLIDE_MAIN_DONE,false,this)
        AnimationUtil.startIntentWithSlideInRightAnimation(this,
            Intent(this, MainActivity::class.java),null)
        finish()
    }

    override fun onSliderRequest() {
    }

}