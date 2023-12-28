package com.anastaasiasenyshyn.ritrattolinguistico

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.anastaasiasenyshyn.ritrattolinguistico.Constants.Companion.ID_SLIDER_RITRATTO_LINGUISTICO
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityMainBinding
import com.anastaasiasenyshyn.ritrattolinguistico.giardino.GiardinoLinguisticoFragment
import com.anastaasiasenyshyn.ritrattolinguistico.ritratto.RitrattoLinguisticoFragment
import com.anastaasiasenyshyn.ritrattolinguistico.slider.SliderActions
import com.anastaasiasenyshyn.ritrattolinguistico.slider.StartAppSliderFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util

class MainActivity : AppCompatActivity(), SliderActions {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val handler : Handler = Handler(Looper.getMainLooper())

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        val curFrag = navHostFragment!!.childFragmentManager.fragments[0]
        Log.i(GiardinoLinguisticoFragment.TAG,"onBackPressed curFrag: $curFrag")
        if (curFrag is IOnBackPressed){
            if (!curFrag.onBackPressed()){
                super.onBackPressed()
            }
        }else{
            super.onBackPressed()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        Log.i(TAG,"This is the Main activity")
        Log.i(TAG,"appInfo called in 10 seconds...")

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onSliderExit(sliderId : String) {
        Log.i(TAG,"onSliderExit called! ($sliderId)")
        when(sliderId){
            ID_SLIDER_RITRATTO_LINGUISTICO -> {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
                val frag = navHostFragment?.childFragmentManager?.fragments?.get(0) as RitrattoLinguisticoFragment
                frag.showRitratto()
                Log.i(TAG,"onSliderExit, Found Fragment: ${frag}")
                Util.writeBooleanSharedPreference(Constants.SHAR_SLIDE_RITRATTO_DONE,false, this)
            }
            else -> {

            }
        }

    }

    override fun onSliderRequest() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        val frag = navHostFragment?.childFragmentManager?.fragments?.get(0) as RitrattoLinguisticoFragment
        frag.showSlider()
    }
}