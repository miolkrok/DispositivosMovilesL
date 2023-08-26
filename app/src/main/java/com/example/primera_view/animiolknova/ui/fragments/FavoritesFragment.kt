package com.example.primera_view.animiolknova.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primera_view.R
import com.example.primera_view.animiolknova.data.connection.FavoriteDB
import com.example.primera_view.animiolknova.data.dao.FavoriteDAO
import com.example.primera_view.animiolknova.logic.favorites.FavoritesC
import com.example.primera_view.animiolknova.ui.adapters.FavoritesAdapter
import com.example.primera_view.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val adapter = FavoritesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        lifecycleScope.launch(Dispatchers.Main) {
            var historial = withContext(Dispatchers.IO) {
                FavoritesC().obtenerFavoritos()
            }
            val itemClickBorrar = @SuppressLint("NotifyDataSetChanged")
            fun(item: FavoriteDB) {
                lifecycleScope.launch(Dispatchers.Main) {
                    var tmp = withContext(Dispatchers.IO) {
                        FavoritesC().borrar(item.idMal)
                        adapter.list = FavoritesC().obtenerFavoritos()
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            val itemClickBuscar = fun(item: FavoriteDB) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("https://www.google.com/search?q=Online " + item.itemFavorites.anilist?.title?.romaji)
                startActivity(intent)
            }
            adapter.clickSearch = itemClickBuscar
            adapter.click = itemClickBorrar
            adapter.list = historial
            binding.rvFavoritos.adapter = adapter
            binding.rvFavoritos.layoutManager = LinearLayoutManager(
                activity?.baseContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }


}