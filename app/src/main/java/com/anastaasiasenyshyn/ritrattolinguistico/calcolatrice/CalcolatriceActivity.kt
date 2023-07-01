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

    private fun initPanelNumbers() {
        binding.btnNum7.setOnClickListener { tasto7 ->
            displayStatus.append("7")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum8.setOnClickListener { tasto8 ->
            displayStatus.append("8")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum9.setOnClickListener { tasto9 ->
            displayStatus.append("9")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum4.setOnClickListener { tasto4 ->
            displayStatus.append("4")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum5.setOnClickListener { tasto5 ->
            displayStatus.append("5")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum6.setOnClickListener { tasto6 ->
            displayStatus.append("6")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum1.setOnClickListener { tasto1 ->
            displayStatus.append("1")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum2.setOnClickListener { tasto2 ->
            displayStatus.append("2")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnNum3.setOnClickListener { tasto3 ->
            displayStatus.append("3")
            binding.tvDisplay.setText(displayStatus.toString())
        }


        binding.btnNum0.setOnClickListener { tasto0 ->
            displayStatus.append("0")
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnVirgola.setOnClickListener { tastoVirgola ->
            displayStatus.append(",")
            binding.tvDisplay.setText(displayStatus.toString())
        }
    }

    private fun initPanelOperations() {

        binding.btnMeno.setOnClickListener { tastoMeno ->
            displayStatus.append("-")
            binding.tvDisplay.setText(displayStatus.toString())

        }

        binding.btnPi.setOnClickListener { tastoPiù ->
            displayStatus.append("+")
            binding.tvDisplay.setText(displayStatus.toString())

        }

        binding.btnUguale.setOnClickListener { tastoUguale ->
            displayStatus.append("=")
            binding.tvDisplay.setText(displayStatus.toString())

        }

    }
}
