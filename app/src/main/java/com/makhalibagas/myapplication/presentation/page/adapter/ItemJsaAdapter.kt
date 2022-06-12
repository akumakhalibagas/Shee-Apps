package com.makhalibagas.myapplication.presentation.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.databinding.ItemJsaBinding

class ItemJsaAdapter : RecyclerView.Adapter<ItemJsaAdapter.ViewHolder>() {

    val listData = ArrayList<JsaItem>()
    var onItemClick: ((JsaItem) -> Unit)? = null

    fun setData(newListData: List<JsaItem>) {
        val previousContentSize = listData.size
        listData.clear()
        listData.addAll(newListData)
        notifyItemRangeRemoved(0, previousContentSize)
        notifyItemRangeInserted(0, newListData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemJsaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(private val binding: ItemJsaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: JsaItem) {
            binding.apply {
                tvNo.text = layoutPosition.toString()
                tvPekerja.text = data.pekerja
                tvDepartemen.text = data.perusahaan
                tvTahap.text = data.tahap
                tvPotensi.text = data.bahaya
                tvUpaya.text = data.pengendalian
                tvTanggungJwb.text = data.tanggungJawab
            }
        }
    }
}

