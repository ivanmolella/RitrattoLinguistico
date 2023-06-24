package com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityCalcolatriceBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityMainBinding

class CalcolatriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalcolatriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcolatriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}