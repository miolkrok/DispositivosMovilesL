package com.example.primera_view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primera_view.R
import com.example.primera_view.data.entities.MarvelChars
import com.example.primera_view.databinding.MarvelCharactersBinding
import com.example.primera_view.logic.lists.ListsItems

class MarvelAdapter(private val items: List<MarvelChars>) : RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {
    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)
        fun render(item: MarvelChars){
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapter.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            inflater.inflate(
                R.layout.marvel_characters
                ,parent,false
            )
        )

    }

    override fun onBindViewHolder(holder: MarvelAdapter.MarvelViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size


}