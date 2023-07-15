package com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.anastaasiasenyshyn.ritrattolinguistico.calcolatrice.CalcolatriceActivityIvan.Companion.TAG
import com.anastaasiasenyshyn.ritrattolinguistico.databinding.ActivityCalcolatriceBinding

class CalcolatriceStatus3(var displayTV : EditText) {

    val displayStatus : StringBuffer = StringBuffer()
    var operation : String? = null
        set(value) {
            // assign value to field variable, like `field = value`
            field = value
        }

    var operando1 : Float? = null
    var operando2 : Float? = null

    fun appendNumberOrComma(value : String?){
        displayStatus.append(value)
    }

    fun evaluateOperandoAndDisplay() {
        displayTV.setText(displayStatus.toString())
        if (operation == null){
            operando1=displayStatus.toString().toFloat()
            Log.i(TAG,"operando1 valorizzato: $operando1")
        }else {
            operando2=displayStatus.toString().toFloat()
            Log.i(TAG,"operando2 valorizzato: $operando2")
        }
    }

    fun getDisplay() : String? {
        return displayStatus.toString()
    }

    fun deleteLast(){
        displayStatus.deleteCharAt(displayStatus.length-1)
    }

    fun setOp(op: String) {
        operation=op
    }

    fun clearDisplayStatus() {
        displayStatus.delete(0,displayStatus.length)
    }

    fun clearStatus() {
        operation=null
        operando1=null
        operando2=null
    }

    fun evaluateOperation(): Float {
        var result : Float? = 0.0f
        when(operation) {
            "-" -> {
                if (operando1 != null && operando2 != null) {
                    result = operando1!! - operando2!!
                }
            }
            "+" -> {
                if (operando1 != null && operando2 != null){
                    result = operando1!! + operando2!!
                }
            }
            "*" -> {
                if (operando1 != null && operando2 != null){
                    result = operando1!! * operando2!!
                }
            }

            "/" -> {
                if (operando1 != null && operando2 != null){
                    result = operando1!! / operando2!!
                }
            }

            "%" -> {
                if (operando1 != null && operando2 != null){
                    result = operando1!! * operando2!! / 100
                }
            }
        }

        return result!!
    }


}

class CalcolatriceActivityIvan : AppCompatActivity() {

    companion object {
        val TAG = "CalcolatriceActivityIvan"
    }

    private lateinit var binding: ActivityCalcolatriceBinding

    lateinit var calcolatriceStatus : CalcolatriceStatus3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcolatriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calcolatriceStatus= CalcolatriceStatus3(binding.tvDisplay)

        initCalcolatrice()
    }

    private fun initCalcolatrice() {
        initPanelNumbers()
        initPanelOperations()
    }

    private fun initPanelNumbers() {
        binding.btnNum7.setOnClickListener { tasto7 ->
            calcolatriceStatus.appendNumberOrComma("7")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum8.setOnClickListener { tasto8 ->
            calcolatriceStatus.appendNumberOrComma("8")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum9.setOnClickListener { tasto9 ->
            calcolatriceStatus.appendNumberOrComma("9")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum4.setOnClickListener { tasto4 ->
            calcolatriceStatus.appendNumberOrComma("4")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum5.setOnClickListener { tasto5 ->
            calcolatriceStatus.appendNumberOrComma("5")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum6.setOnClickListener { tasto6 ->
            calcolatriceStatus.appendNumberOrComma("6")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum1.setOnClickListener { tasto1 ->
            calcolatriceStatus.appendNumberOrComma("1")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum2.setOnClickListener { tasto2 ->
            calcolatriceStatus.appendNumberOrComma("2")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnNum3.setOnClickListener { tasto3 ->
            calcolatriceStatus.appendNumberOrComma("3")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }


        binding.btnNum0.setOnClickListener { tasto0 ->
            calcolatriceStatus.appendNumberOrComma("0")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnVirgola.setOnClickListener { tastoVirgola ->
            calcolatriceStatus.appendNumberOrComma(".")
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }
    }


    private fun initPanelOperations() {

        binding.btnCancel.setOnClickListener { tastoCancel ->
            clearDisplay(false)
            binding.tvDisplay.setText(calcolatriceStatus.getDisplay())
        }

        binding.btnUndo.setOnClickListener { tastoUndo ->
            Log.i(TAG,"-- Undo called")
            calcolatriceStatus.deleteLast()
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }

        binding.btnMeno.setOnClickListener { tastoMeno ->
            Log.i(TAG,"-- Subtraction Operation called")
            calcolatriceStatus.setOp("-")
            clearDisplay(true)
        }

        binding.btnPi.setOnClickListener { tastoPi ->
            Log.i(TAG,"-- Subtraction Operation called")
            calcolatriceStatus.setOp("+")
            clearDisplay(true)

        }

        binding.btnDiv.setOnClickListener {tastoDiv ->
            Log.i(TAG,"-- Subtraction Operation called")
            calcolatriceStatus.setOp("/")
            clearDisplay(true)
        }

        binding.btnMul.setOnClickListener {tastoMul ->
            Log.i(TAG,"-- Subtraction Operation called")
            calcolatriceStatus.setOp("*")
            clearDisplay(true)
        }

        binding.btnPercento.setOnClickListener {tastoPercento ->
            Log.i(TAG,"-- Subtraction Operation called")
            calcolatriceStatus.setOp("%")
            clearDisplay(true)
        }

        binding.btnUguale.setOnClickListener { tastoUguale ->
            val result : Float = calcolatriceStatus.evaluateOperation()
            clearDisplay(false)
            calcolatriceStatus.appendNumberOrComma(result.toString())
            calcolatriceStatus.evaluateOperandoAndDisplay()
        }


    }

    fun clearDisplay(clearOnly : Boolean){
        Log.i(CalcolatriceActivity2.TAG,"-- clearDisplay called")
        calcolatriceStatus.clearDisplayStatus()
        binding.tvDisplay.setText(calcolatriceStatus.getDisplay().toString())
        if (clearOnly == false){
            calcolatriceStatus.clearStatus()
        }
    }
}
