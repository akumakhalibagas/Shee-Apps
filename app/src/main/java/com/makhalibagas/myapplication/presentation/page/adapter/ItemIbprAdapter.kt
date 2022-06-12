package com.makhalibagas.myapplication.presentation.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
import com.makhalibagas.myapplication.databinding.ItemIbprBinding
import com.makhalibagas.myapplication.utils.changeDateFormat

class ItemIbprAdapter : RecyclerView.Adapter<ItemIbprAdapter.ViewHolder>() {

    val listData = ArrayList<IbprItem>()
    var onItemClick: ((IbprItem) -> Unit)? = null

    fun setData(newListData: List<IbprItem>) {
        val previousContentSize = listData.size
        listData.clear()
        listData.addAll(newListData)
        notifyItemRangeRemoved(0, previousContentSize)
        notifyItemRangeInserted(0, newListData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemIbprBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(private val binding: ItemIbprBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: IbprItem) {
            binding.apply {
                tvNo.text = layoutPosition.toString()
                tvDate.text = changeDateFormat(data.date.toString(), "dd MMMM yyyy")
                tvJam.text = changeDateFormat(data.date.toString(), "HH:mm")
                tvResiko.text = data.resiko
                tvKodeBahaya.text = data.kodeBahaya
                tvStatus.text = data.status
                tvTemuan.text = data.temuan
                tvBahaya.text = data.kodeBahaya
                tvPengendalian.text = data.pengendalianResiko
            }
        }
    }
}

