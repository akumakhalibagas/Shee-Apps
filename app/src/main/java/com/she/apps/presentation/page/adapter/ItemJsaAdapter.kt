package com.she.apps.presentation.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.she.apps.data.source.remote.response.JsaItem
import com.she.apps.databinding.ItemJsaBinding

class ItemJsaAdapter(val isHead: Boolean) : RecyclerView.Adapter<ItemJsaAdapter.ViewHolder>() {

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
                tvNo.text = "${layoutPosition+1}"
                tvPekerja.text = data.pekerja
                tvDepartemen.text = data.department
                tvPerusahaan.text = data.perusahaan
                tvPotensi.text = data.bahaya
                tvUpaya.text = data.pengendalian
                tvTanggungJwb.text = data.tanggungJawab
                tvBahaya.text = data.bahaya
                tvSupervisor.text = data.supervisor
                root.setOnClickListener {
                    if (!isHead){
                        onItemClick!!.invoke(data)
                    }
                }
            }
        }
    }
}

