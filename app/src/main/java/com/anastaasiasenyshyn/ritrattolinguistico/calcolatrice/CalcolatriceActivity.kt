package com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityCalcolatriceBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityMainBinding

class CalcolatriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalcolatriceBinding

    val displayStatus : StringBuffer = StringBuffer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcolatriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCalcolatrice()
    }

    private fun initCalcolatrice() {
        initPanelNumbers()
        initPanelOperations()
    }

    private fun initPanelOperations() {
        binding.btnNum7.setOnClickListener { tasto7 ->
            displayStatus.append("7")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum8.setOnClickListener { tasto7 ->
            displayStatus.append("8")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum9.setOnClickListener { tasto7 ->
            displayStatus.append("9")
            binding.tvDisplay.setText(displayStatus.toString())
        }
    }

    private fun initPanelNumbers() {
        //TODO("Not yet implemented")
    }
}