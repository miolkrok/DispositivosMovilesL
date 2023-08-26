package com.example.primera_view.animiolknova.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primera_view.R
import com.squareup.picasso.Picasso
import com.example.primera_view.databinding.AnimeCharactersBinding
import com.example.primera_view.animiolknova.data.entities.tracemoe.Result

class AnimeAMNAdapter: RecyclerView.Adapter<AnimeAMNAdapter.AnimeViewHolder>() {

    var list: List<Result> = emptyList()
    lateinit var itemClick: (Result) -> Unit
    lateinit var itemClickPlay: (Result) -> Unit
    lateinit var itemClickBuscar: (Result) -> Unit

    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: AnimeCharactersBinding = AnimeCharactersBinding.bind(view)
        fun render(
            item: Result,
            itemClick: (Result) -> Unit,
            itemClickPlay: (Result) -> Unit,
            itemClickBuscar: (Result) -> Unit,
        ) {
            binding.btnBorrar.visibility = View.GONE
            binding.btnBuscar.visibility = View.GONE
            binding.tvNombre.text = item.anilist?.title?.romaji
            binding.tvEpisodio.text = item.episode.toString()
            binding.tvSimilitud.text = String.format("%.2f", item.similarity!! * 100) + " %"
            binding.ivPlay.visibility = View.VISIBLE
            Picasso.get().load(item.image).into(binding.ivFoto)
            binding.btnBuscar.setOnClickListener { itemClickBuscar(item) }
            binding.ivPlay.setOnClickListener { itemClickPlay(item) }
            itemView.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeViewHolder(inflater.inflate(R.layout.anime_characters, parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.render(list[position], itemClick, itemClickPlay, itemClickBuscar)
    }

    override fun getItemCount(): Int = list.size


}