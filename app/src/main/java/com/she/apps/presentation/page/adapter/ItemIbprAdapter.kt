package com.she.apps.presentation.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.she.apps.data.source.remote.response.IbprItem
import com.she.apps.databinding.ItemIbprBinding
import com.she.apps.utils.changeDateFormat

class ItemIbprAdapter(val isHead: Boolean) : RecyclerView.Adapter<ItemIbprAdapter.ViewHolder>() {

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
                tvNo.text = "${layoutPosition+1}"
                tvDate.text = changeDateFormat(data.date.toString(), "dd MMMM yyyy")
                tvJam.text = changeDateFormat(data.date.toString(), "HH:mm")
                tvResiko.text = data.resiko
                tvKodeBahaya.text = data.kodeBahaya
                tvStatus.text = data.status
                tvTemuan.text = data.temuan
                tvBahaya.text = data.bahaya
                tvPengendalian.text = data.pengendalianResiko
                tvShift.text = data.shift
                tvSite.text = data.site
                tvDp.text = data.department
                root.setOnClickListener {
                    if (!isHead){
                        onItemClick!!.invoke(data)
                    }
                }
            }
        }
    }
}

