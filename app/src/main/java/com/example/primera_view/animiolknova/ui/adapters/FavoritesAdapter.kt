package com.example.primera_view.animiolknova.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primera_view.R
import com.example.primera_view.animiolknova.data.connection.FavoriteDB
import com.example.primera_view.databinding.AnimeCharactersBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    var list: List<FavoriteDB> = emptyList()
    lateinit var click: (FavoriteDB) -> Unit
    lateinit var clickSearch: (FavoriteDB) -> Unit

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: AnimeCharactersBinding = AnimeCharactersBinding.bind(view)
        fun render(item: FavoriteDB,itemClick: (FavoriteDB) -> Unit,itemClickBuscar: (FavoriteDB) -> Unit) {
            binding.tvNombre.text = item.itemFavorites.anilist?.title?.romaji
            binding.tvEpisodio.text = item.itemFavorites.episode.toString()
            binding.tvSimilitud.text =
                String.format("%.2f", item.itemFavorites.similarity!! * 100) + " %"
            Picasso.get().load(item.itemFavorites.image).into(binding.ivFoto)
            binding.btnBorrar.setOnClickListener {
                itemClick(item)
            }
            binding.btnBuscar.setOnClickListener { itemClickBuscar(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(inflater.inflate(R.layout.anime_characters, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.render(list[position],click,clickSearch)

    }


}