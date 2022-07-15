package com.example.cloudfunctionsmessage.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudfunctionsmessage.databinding.ItemsBinding
import com.example.cloudfunctionsmessage.model.TitleModel

class ListAdapter(private var articlesList : ArrayList<TitleModel>) : RecyclerView.Adapter<com.example.cloudfunctionsmessage.list.ListAdapter.MyViewHolder>() {

    private lateinit var listerner: OnItemClickListerner

    fun setOnItemClickListerner(listerner: OnItemClickListerner) {
        this.listerner = listerner
    }

    fun addNewData(list: ArrayList<TitleModel>) {
        articlesList = list
        notifyDataSetChanged()
    }

    class MyViewHolder(
        private val itemsBinding : ItemsBinding,
        private val listerner: OnItemClickListerner
    ) : RecyclerView.ViewHolder(itemsBinding.root) {
        fun bind(list : TitleModel) {
            with (itemsBinding) {
                articles.text = list.title
                author.text = list.author

                items.setOnClickListener {
                    listerner.onItemClick(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val items = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(items, listerner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articlesList[position])
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}