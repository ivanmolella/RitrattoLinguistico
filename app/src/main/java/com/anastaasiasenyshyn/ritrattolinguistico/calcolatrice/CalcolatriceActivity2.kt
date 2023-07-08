package com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityCalcolatriceBinding

class CalcolatriceStatus2(var displayTV : EditText) {

    private val displayStatus : StringBuffer = StringBuffer()

    private var operation : String? = null
    private var operando1 : Float? = null
    private var operando2 : Float? = null

    fun appendNumberOrComma(value : String?){
        displayStatus.append(value)
    }

    fun evaluateOperandoAndDisplay() {
        displayTV.setText(getDisplay())
        if (operation == null){
            operando1=getDisplay()?.toFloat()
            Log.i(CalcolatriceActivity.TAG,"operando1 valorizzato: $operando1")
        }else {
            operando2=getDisplay()?.toFloat()
            Log.i(CalcolatriceActivity.TAG,"operando2 valorizzato: $operando2")
        }
    }

    fun evaluateOperation(): Float {
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

    fun getDisplay() : String? {
        return displayStatus.toString()
    }

    fun deleteLast(){
        displayStatus.deleteCharAt(displayStatus.length-1)
    }

    fun clearDisplayStatus() {
        displayStatus.delete(0,displayStatus.length)
    }

    fun clearStatus() {
       operation=null
        operando1=null
        operando2=null
    }

    fun setOperation(op: String) {
        operation=op
    }

}

class CalcolatriceActivity2 : AppCompatActivity() {

    companion object {
        val TAG = "CalcolatriceActivity"
    }

    lateinit var `calStatus` : CalcolatriceStatus2

    private lateinit var binding: ActivityCalcolatriceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcolatriceBinding.inflate(layoutInflater)
        calStatus = CalcolatriceStatus2(binding.tvDisplay)
        setContentView(binding.root)

        initCalcolatrice()
    }

    private fun initCalcolatrice() {
        initPanelNumbers()
        initPanelOperations()
    }

    private fun initPanelNumbers() {
        binding.btnNum7.setOnClickListener { tasto7 ->
            `calStatus`.appendNumberOrComma("7")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum8.setOnClickListener { tasto8 ->
            calStatus.appendNumberOrComma("8")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum9.setOnClickListener { tasto9 ->
            calStatus.appendNumberOrComma("9")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum4.setOnClickListener { tasto4 ->
            calStatus.appendNumberOrComma("4")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum5.setOnClickListener { tasto5 ->
            calStatus.appendNumberOrComma("5")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum6.setOnClickListener { tasto6 ->
            calStatus.appendNumberOrComma("6")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum1.setOnClickListener { tasto1 ->
            calStatus.appendNumberOrComma("1")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum2.setOnClickListener { tasto2 ->
            calStatus.appendNumberOrComma("2")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum3.setOnClickListener { tasto3 ->
            calStatus.appendNumberOrComma("3")
            calStatus.evaluateOperandoAndDisplay()
        }


        binding.btnNum0.setOnClickListener { tasto0 ->
            calStatus.appendNumberOrComma("0")
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnVirgola.setOnClickListener { tastoVirgola ->
            calStatus.appendNumberOrComma(".")
            calStatus.evaluateOperandoAndDisplay()
        }
    }

    private fun initPanelOperations() {

        binding.btnCancel.setOnClickListener { tastoCancel ->
            clearDisplay(false)
            binding.tvDisplay.setText(calStatus.getDisplay().toString())
        }

        binding.btnUndo.setOnClickListener { tastoUndo ->
            Log.i(TAG,"-- Undo called")
            calStatus.deleteLast()
            calStatus.evaluateOperandoAndDisplay()
        }

        binding.btnMeno.setOnClickListener { tastoMeno ->
            Log.i(TAG,"-- Subtraction Operation called")
            calStatus.setOperation("-")
            clearDisplay(true)
        }

        binding.btnPi.setOnClickListener { tastoPiù ->
            calStatus.appendNumberOrComma("+")
            binding.tvDisplay.setText(calStatus.getDisplay().toString())

        }

        binding.btnUguale.setOnClickListener { tastoUguale ->
            val result : Float = calStatus.evaluateOperation()
            clearDisplay(false)
            calStatus.appendNumberOrComma(result.toString())
            calStatus.evaluateOperandoAndDisplay()
        }
    }

    fun clearDisplay(clearOnly : Boolean){
        Log.i(TAG,"-- clearDisplay called")
        calStatus.clearDisplayStatus()
        binding.tvDisplay.setText(calStatus.getDisplay().toString())
        if (clearOnly == false){
            calStatus.clearStatus()
        }
    }
}
