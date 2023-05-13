package com.anastaasiasenyshyn.ritrattolinguistico.ritratto

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anastaasiasenyshyn.ritrattolinguistico.R

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

            holder.viewName1?.text = colorItem.color1Name
            holder.viewName2?.text = colorItem.color2Name
            holder.viewName3?.text = colorItem.color3Name

            holder.view1?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color1,colorItem.color1Name)
            }

            holder.view2?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color2,colorItem.color2Name)
            }

            holder.view3?.setOnClickListener {
                colorSelectionListener.onColorSelected(colorItem.color3,colorItem.color3Name)
            }

            Log.i(TAG,"colorItem: $colorItem")
        }
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

        init {
            view1 = itemView.findViewById(R.id.color1)
            view2 = itemView.findViewById(R.id.color2)
            view3 = itemView.findViewById(R.id.color3)

            viewName1 = itemView.findViewById(R.id.color1Name)
            viewName2 = itemView.findViewById(R.id.color2Name)
            viewName3 = itemView.findViewById(R.id.color3Name)
        }
    }

}