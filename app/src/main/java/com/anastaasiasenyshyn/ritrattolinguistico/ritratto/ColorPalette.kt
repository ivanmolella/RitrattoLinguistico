package com.anastaasiasenyshyn.ritrattolinguistico.ritratto

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util
import com.google.gson.Gson

class ColorItem {

    var color1 : Int = 0
    var color2 : Int = 0
    var color3 : Int = 0

    lateinit var color1Name : String
    lateinit var color2Name : String
    lateinit var color3Name : String

    constructor(color1: Int, color2: Int,color3: Int,color1Name : String,color2Name : String,color3Name : String) {
       this.color1=color1
       this.color2=color2
       this.color3=color3

        this.color1Name=color1Name
        this.color2Name=color2Name
        this.color3Name=color3Name
    }

    constructor() {}

    override fun toString(): String {
        return "ColorItem(color1=$color1, color2=$color2, color3=$color3, color1Name=$color1Name, color2Name=$color2Name, color3Name=$color3Name)"
    }

}

class ColorLanguage {
    var color : Int? = null
    var colorName : String? = null
    var colorLang : String? = null
}

class ColorPaletteAdapter(
    private val mContext: Context,
    private var modelList: List<ColorItem>?,
    private var colorSelectionListener : ColorSelectionListener

) : RecyclerView.Adapter<ColorPaletteAdapter.ViewHolder>() {

    companion object {
        const val TAG = "ColorPaletteAdapter"
    }

    fun updateList(modelList: List<ColorItem>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ColorPaletteAdapter.ViewHolder {

        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.color_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Here you can fill your row view
        if (holder is ColorPaletteAdapter.ViewHolder) {
            val colorItem : ColorItem = modelList!![position]
            holder.view1?.setBackgroundResource(colorItem.color1!!)
            holder.view2?.setBackgroundResource(colorItem.color2!!)
            holder.view3?.setBackgroundResource(colorItem.color3!!)

            holder.viewName1?.text = getColorName(colorItem,1)
            holder.viewName2?.text = getColorName(colorItem,2)
            holder.viewName3?.text = getColorName(colorItem,3)

            holder.view1?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color1,colorItem.color1Name)
            }

            holder.view2?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color2,colorItem.color2Name)
            }

            holder.view3?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color3,colorItem.color3Name)
            }

            setPenBtn(holder.viewName1,holder.btnPen1,colorItem,1)
            setPenBtn(holder.viewName2,holder.btnPen2,colorItem,2)
            setPenBtn(holder.viewName3,holder.btnPen3,colorItem,3)

            Log.i(TAG,"colorItem: $colorItem")
        }
    }

    private fun getColorName(colorItem: ColorItem, i: Int): String? {
        var color : Int? = null
        var colorName : String? = null

        when(i){
            1 -> {
                color = colorItem.color1
                colorName = colorItem.color1Name
            }
            2 -> {
                color = colorItem.color2
                colorName = colorItem.color2Name

            }
            3 -> {
                color = colorItem.color3
                colorName = colorItem.color3Name
            }
        }
        val json = Util.readStringSharedPreference(color.toString(),mContext)
        if (json != null){
            val gson = Gson()
            val colorLanguage = gson.fromJson(json, ColorLanguage::class.java)
            colorName = colorLanguage.colorLang
        }

        return colorName

    }

    private fun setPenBtn(viewName: TextView?, btnPen: ImageView?, colorItem: ColorItem,colorIndex : Int) {

        var color : Int? = null
        var colorName : String? = null

        when(colorIndex){
            1 -> {
                color = colorItem.color1
                colorName = colorItem.color1Name
            }

            2 -> {
                color = colorItem.color2
                colorName = colorItem.color2Name
            }

            3 -> {
                color = colorItem.color3
                colorName = colorItem.color3Name
            }
        }

        val listener = View.OnClickListener {
            MaterialDialog(mContext)
                .title(R.string.assign_color_title)
                .message(res = R.string.assign_language_to_color)
                .positiveButton(text = mContext.getString(R.string.dialog_done).toUpperCase()) {

                }
                .negativeButton(text = mContext.getString(R.string.dialog_cancel).toUpperCase()) {

                }
                .cancelable(true)
                .show {
                    input { _, text ->
                        Log.i(TAG,"Text: $text")
                        val colorClassJson = Util.readStringSharedPreference(color.toString(),mContext)
                        var colorLanguage : ColorLanguage? = null
                        val gson = Gson()
                        if (colorClassJson == null){
                            colorLanguage = ColorLanguage()
                            colorLanguage.colorName = colorName
                            colorLanguage.color = color
                        }else{
                            colorLanguage = gson.fromJson(colorClassJson, ColorLanguage::class.java)
                        }
                        colorLanguage?.colorLang = text.toString()
                        val json = gson.toJson(colorLanguage)
                        Util.writeStringSharedPreference(color.toString(),json,mContext)
                        notifyDataSetChanged()
                    }
                }
        }
        viewName?.setOnClickListener(listener)
        btnPen?.setOnClickListener(listener)

    }


    override fun getItemCount(): Int {
        return modelList!!.size
    }


    private fun getItem(position: Int): ColorItem {
        return modelList!![position]
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view1 : View? = null
        var view2 : View? = null
        var view3 : View? = null
        var viewName1 : TextView? = null
        var viewName2 : TextView? = null
        var viewName3 : TextView? = null
        var btnPen1 : ImageView? = null
        var btnPen2 : ImageView? = null
        var btnPen3 : ImageView? = null

        init {
            view1 = itemView.findViewById(R.id.color1)
            view2 = itemView.findViewById(R.id.color2)
            view3 = itemView.findViewById(R.id.color3)

            viewName1 = itemView.findViewById(R.id.color1Name)
            viewName2 = itemView.findViewById(R.id.color2Name)
            viewName3 = itemView.findViewById(R.id.color3Name)

            btnPen1 = itemView.findViewById(R.id.btnPen1)
            btnPen2 = itemView.findViewById(R.id.btnPen2)
            btnPen3 = itemView.findViewById(R.id.btnPen3)

        }
    }

}