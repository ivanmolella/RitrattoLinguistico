package com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityCalcolatriceBinding
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityMainBinding

class CalcolatriceActivity : AppCompatActivity() {

    companion object {
        val TAG = "CalcolatriceActivity"
    }

    private lateinit var binding: ActivityCalcolatriceBinding

    val displayStatus : StringBuffer = StringBuffer()
    var operation : String? = null

    var operando1 : Float? = null
    var operando2 : Float? = null

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
            evaluateOperandoAndDisplay()
        }

        binding.btnNum8.setOnClickListener { tasto8 ->
            displayStatus.append("8")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum9.setOnClickListener { tasto9 ->
            displayStatus.append("9")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum4.setOnClickListener { tasto4 ->
            displayStatus.append("4")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum5.setOnClickListener { tasto5 ->
            displayStatus.append("5")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum6.setOnClickListener { tasto6 ->
            displayStatus.append("6")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum1.setOnClickListener { tasto1 ->
            displayStatus.append("1")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum2.setOnClickListener { tasto2 ->
            displayStatus.append("2")
            evaluateOperandoAndDisplay()
        }

        binding.btnNum3.setOnClickListener { tasto3 ->
            displayStatus.append("3")
            evaluateOperandoAndDisplay()
        }


        binding.btnNum0.setOnClickListener { tasto0 ->
            displayStatus.append("0")
            evaluateOperandoAndDisplay()
        }

        binding.btnVirgola.setOnClickListener { tastoVirgola ->
            displayStatus.append(".")
            evaluateOperandoAndDisplay()
        }
    }

    private fun evaluateOperandoAndDisplay() {
        binding.tvDisplay.setText(displayStatus.toString())
        if (operation == null){
            operando1=displayStatus.toString().toFloat()
            Log.i(TAG,"operando1 valorizzato: $operando1")
        }else {
            operando2=displayStatus.toString().toFloat()
            Log.i(TAG,"operando2 valorizzato: $operando2")
        }
    }

    private fun initPanelOperations() {

        binding.btnCancel.setOnClickListener { tastoCancel ->
            clearDisplay(false)
            binding.tvDisplay.setText(displayStatus.toString())
        }

        binding.btnUndo.setOnClickListener { tastoUndo ->
            Log.i(TAG,"-- Undo called")
            displayStatus.deleteCharAt(displayStatus.length-1)
            evaluateOperandoAndDisplay()
        }

        binding.btnMeno.setOnClickListener { tastoMeno ->
            Log.i(TAG,"-- Subtraction Operation called")
            operation="-"
            clearDisplay(true)
        }

        binding.btnPi.setOnClickListener { tastoPiù ->
            displayStatus.append("+")
            binding.tvDisplay.setText(displayStatus.toString())

        }

        binding.btnUguale.setOnClickListener { tastoUguale ->
            val result : Float = evaluateOperation()
            clearDisplay(false)
            displayStatus.append(result.toString())
            evaluateOperandoAndDisplay()
        }
    }

    private fun evaluateOperation(): Float {
        var result : Float? = 0.0f
        when(operation) {
            "-" -> {
                if (operando1 != null && operando2 != null) {
                    result = operando1!! - operando2!!
                }
            }
        }

        return result!!
    }

    fun clearDisplay(clearOnly : Boolean){
        Log.i(TAG,"-- clearDisplay called")
        displayStatus.delete(0,displayStatus.length)
        binding.tvDisplay.setText(displayStatus.toString())
        if (clearOnly == false){
            operando1 = null
            operando2 = null
            operation = null
        }
    }
}
