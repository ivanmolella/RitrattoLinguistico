package com.anastaasiasenyshyn.ritrattolinguistico.giardino

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anastaasiasenyshyn.ritrattolinguistico.R
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.Mapping
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util

class WordListItem {

    var word: String? = null

    constructor(message: String) {
        this.word = message
    }

    constructor() {}
}


class WordListAdapter(
    private val mContext: Context,
    private var modelList: List<WordListItem>?,
    private var currentGiardinoPage: Int,
    private var wordMapping: Mapping,
    private var onButtonClick : (Int,String,String) -> (Unit)
) : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    companion object {
        const val TAG = "WordListAdapter"
    }

    fun updateList(modelList: List<WordListItem>) {
        this.modelList = modelList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): WordListAdapter.ViewHolder {

        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.word_list_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Here you can fill your row view
        if (holder is WordListAdapter.ViewHolder) {
            val item = modelList!![position]
            val wordTranslation : String? = findWordTranslation(item,currentGiardinoPage,wordMapping)
            var displayedItem = ""
            if (wordTranslation != null){
                displayedItem=String.format(mContext.getString(R.string.translated_word_is),"${wordMapping.itemItalianName?.capitalize()}","${item.word?.capitalize()}","${wordTranslation.capitalize()}")
                setBtnAsModify(holder,item)
            }else {
                displayedItem=String.format(mContext.getString(R.string.write_translation),"${wordMapping.itemItalianName?.capitalize()}","${item.word?.capitalize()}")
                setBtnAsWrite(holder,item)
            }
            Log.i(TAG,"displayedItem: $displayedItem")
            holder.word?.text = displayedItem
            holder.btnWrite?.setOnClickListener {
                onButtonClick(currentGiardinoPage,wordMapping.itemItalianName!!,Util.trim(item.word!!))
            }
        }
    }

    private fun setBtnAsModify(holder: ViewHolder, item: WordListItem) {
        holder.btnText?.text = mContext.getString(R.string.btn_modify)
    }

    private fun setBtnAsWrite(holder: ViewHolder, item: WordListItem) {
        holder.btnText?.text = mContext.getString(R.string.btn_write)
    }

    private fun findWordTranslation(
        item: WordListItem,
        currentGiardinoPage: Int,
        wordMapping: Mapping
    ): String? {
        val savedTranslatedWordKey = Util.getTranslatedWordKey(currentGiardinoPage,wordMapping.itemItalianName!!,item.word!!)
        Log.i(TAG, "Translated key (read): $savedTranslatedWordKey")

        return Util.readStringSharedPreference(savedTranslatedWordKey, mContext)
    }


    override fun getItemCount(): Int {
        return modelList!!.size
    }


    private fun getItem(position: Int): WordListItem {
        return modelList!![position]
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var word : TextView?=null
        var btnWrite : View?=null
        var btnText : TextView?=null
        init {
            word = itemView.findViewById(R.id.word)
            btnWrite = itemView.findViewById(R.id.btn_write_translation)
            btnText = itemView.findViewById(R.id.btn_write_text)
        }
    }

}