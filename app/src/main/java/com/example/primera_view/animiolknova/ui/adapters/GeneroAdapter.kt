package com.moncayo.pilco.anisham.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moncayo.pilco.anisham.R
import com.moncayo.pilco.anisham.databinding.ItemGeneroBinding


class GeneroAdapter : RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {
    var dataList: List<String> = emptyList()

    class GeneroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemGeneroBinding = ItemGeneroBinding.bind(view)
        fun render(item: String) {
            binding.tvGenero.text = item.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GeneroViewHolder(inflater.inflate(R.layout.item_genero, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        holder.render(dataList[position])
    }
}