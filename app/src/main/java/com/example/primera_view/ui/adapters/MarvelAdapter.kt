package com.example.primera_view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primera_view.R
import com.example.primera_view.data.entities.MarvelChars
import com.example.primera_view.databinding.MarvelCharactersBinding
import com.example.primera_view.logic.lists.ListsItems
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MarvelAdapter(
    private var items: List<MarvelChars>,
    private var fnClick : (MarvelChars) -> Unit
) : RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {
    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //aqui vamos a enlazar el codigo con la vista
        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)
        fun render(item: MarvelChars,fnClick : (MarvelChars) -> Unit){
            binding.imgMarvel.bringToFront()
            binding.txtName.text = item.name
            binding.txtComic.text = item.comic
            Picasso.get().load(item.image).into(binding.imgMarvel)

            binding.imgMarvel.setOnClickListener{
                fnClick(item)
               /* Snackbar.make(binding.imgMarvel,
                    item.name,
                    Snackbar.LENGTH_SHORT)
                    .show*/
            }

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
        holder.render(items[position], fnClick)
    }

    override fun getItemCount(): Int = items.size


}