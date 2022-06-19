package com.makhalibagas.myapplication.presentation.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.databinding.ItemGreenBinding
import com.makhalibagas.myapplication.utils.changeDateFormat

class ItemGreenAdapter(val isHead: Boolean) : RecyclerView.Adapter<ItemGreenAdapter.ViewHolder>() {

    val listData = ArrayList<GreenItem>()
    var onItemClick: ((GreenItem) -> Unit)? = null

    fun setData(newListData: List<GreenItem>) {
        val previousContentSize = listData.size
        listData.clear()
        listData.addAll(newListData)
        notifyItemRangeRemoved(0, previousContentSize)
        notifyItemRangeInserted(0, newListData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemGreenBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(private val binding: ItemGreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GreenItem) {
            binding.apply {
                tvNo.text = "${layoutPosition+1}"
                tvDate.text = changeDateFormat(data.date.toString(), "dd MMMM yyyy")
                tvJam.text = changeDateFormat(data.date.toString(), "HH:mm")
                tvKondisi.text = data.kondisi
                tvLokasi.text = data.lokasi
                tvSaran.text = data.saran
                tvDibicarakan.text = data.dibicarakan
                tvStatus.text = data.status
                tvCategory.text = data.kategori
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

