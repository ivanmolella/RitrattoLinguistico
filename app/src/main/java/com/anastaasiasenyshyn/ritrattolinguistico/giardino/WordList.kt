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

    var message: String? = null

    constructor(message: String) {
        this.message = message
    }

    constructor() {}
}


class WordListAdapter(
    private val mContext: Context,
    private var modelList: List<WordListItem>?,
    private var currentGiardinoPage: Int,
    private var wordMapping: Mapping
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
                displayedItem=String.format(mContext.getString(R.string.translated_word_is),"${wordMapping.itemItalianName?.capitalize()}","${item.message?.capitalize()}","${wordTranslation.capitalize()}")
                setBtnAsModify(holder,item)
            }else {
                displayedItem=String.format(mContext.getString(R.string.write_translation),"${wordMapping.itemItalianName?.capitalize()}","${item.message?.capitalize()}")
                setBtnAsWrite(holder,item)
            }
            Log.i(TAG,"displayedItem: $displayedItem")
            holder.word?.text = displayedItem
        }
    }

    private fun setBtnAsModify(holder: WordListAdapter.ViewHolder, item: WordListItem) {

    }

    private fun setBtnAsWrite(holder: WordListAdapter.ViewHolder, item: WordListItem) {

    }

    private fun findWordTranslation(
        item: WordListItem,
        currentGiardinoPage: Int,
        wordMapping: Mapping
    ): String? {
        val savedTranslatedWordKey =
            "${currentGiardinoPage}_${wordMapping.itemItalianName}_${item.message}"
        Log.i(TAG, "findWordTranslation key: $savedTranslatedWordKey")

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
        init {
            word = itemView.findViewById(R.id.word)
        }
    }

}