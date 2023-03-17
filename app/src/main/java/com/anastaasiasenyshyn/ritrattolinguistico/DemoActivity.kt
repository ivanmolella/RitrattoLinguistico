package com.anastaasiasenyshyn.ritrattolinguistico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityDemoBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityMainBinding
import com.anastaasiasenyshyn.ritrattolinguistico.demoslider.DemoFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.AnimationUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DemoActivity : AppCompatActivity() {

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            val frag = DemoFragment()
            val bundle = Bundle()
            bundle.putString("PAGE",position.toString())
            frag.arguments = bundle
            return frag
        }
    }

    private lateinit var binding: ActivityDemoBinding
    private var viewPager: ViewPager2? = null

    val handler : Handler = Handler(Looper.getMainLooper())
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.pager

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager?.adapter = pagerAdapter

        TabLayoutMediator(binding.tabs, viewPager!!
        ) { tab, position -> // Styling each tab here
            //tab.text = "Tab $position"
        }.attach()

        nextItem()
    }

    fun nextItem(){
        handler.postDelayed(object : Runnable {
            override fun run() {
                counter++
                viewPager?.currentItem = counter
                if (counter < 2){
                    nextItem()
                }else {
                    goToMainActivity()
                }
            }
        },3000)
    }

    private fun goToMainActivity() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                AnimationUtil.startIntentWithSlideInRightAnimation(this@DemoActivity,
                    Intent(this@DemoActivity, MainActivity::class.java),null)
            }
        },3000)
    }
}