package com.example.primera_view.animiolknova.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primera_view.animiolknova.data.entities.tracemoe.SearchResponse
import com.example.primera_view.animiolknova.ui.adapters.AnimeAMNAdapter
import com.example.primera_view.animiolknova.ui.utilities.AniMiolkVariables
import com.example.primera_view.databinding.ActivityAniMiolkNovaAnimesBinding
import com.example.primera_view.animiolknova.logic.animelist.MyAnimeListC
import com.example.primera_view.animiolknova.logic.tracemoe.TraceC
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.primera_view.animiolknova.data.entities.tracemoe.Result

class AniMiolkNovaAnimesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAniMiolkNovaAnimesBinding

    private val adapter = AnimeAMNAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniMiolkNovaAnimesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        var json: String = ""
        var item: SearchResponse = SearchResponse()
        var json2: String = ""
        var item2: Result = Result()
        intent.extras.let {
            json = it?.getString("listaDatos").toString()
            if (json != "") {
                item = Gson().fromJson(
                    json, SearchResponse::class.java
                )
            }
        }
        loadAnimes(item)
    }

    private fun loadAnimes(data: SearchResponse) {
        lifecycleScope.launch(Dispatchers.Main) {

            //filtro de contenido de edad y que no se repitan
            var listAnimesPosibles = data.result!!.distinctBy { it.anilist?.id }
            if (!AniMiolkVariables.contenidoNSFW) {
                listAnimesPosibles =
                    listAnimesPosibles.filter { it.anilist?.isAdult == false }
            }
            val itemClick = fun(item: Result) {
                val job = lifecycleScope.launch {
                    binding.lpDetalleAnime.visibility = View.VISIBLE
                    //obtenemos la informacion de los animes en español
                    var tmp = TraceC().generarDetalles(item)

                    //verificamos si encontramos informacion en espaniol sino buscamos en ingles
                    if (tmp == null) {

                        //obtenemmos informacion en ingles
                        var data = MyAnimeListC().obtenerAnime(item.anilist?.idMal.toString())
                        if (data != null) {
                            tmp = MyAnimeListC().convertirMyanimeList(data)
                        }
                    }
                    val json = Gson().toJson(tmp)
                    val json2 = Gson().toJson(item)
                    val toShowInfo = Intent(
                        this@AniMiolkNovaAnimesActivity,
                        AniMiolkNovaDetalleActivity::class.java
                    )
                    toShowInfo.putExtra("item", json)
                    toShowInfo.putExtra("idDB", json2)
                    binding.lpDetalleAnime.visibility = View.GONE
                    startActivity(toShowInfo)
                }
            }

            val itemClickPlay = fun(item: Result) {
                val toShow = Intent(
                    this@AniMiolkNovaAnimesActivity,
                    ReproductorActivity::class.java
                )
                toShow.putExtra("url", item.video)
                startActivity(toShow)
            }
            val itemClickBuscar = fun(item: Result) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                    Uri.parse("https://www.google.com/search?q=Online " + item.anilist?.title?.romaji)
                startActivity(intent)
            }
            adapter.itemClick = itemClick
//            adapter.itemClickPlay = itemClickPlay
            adapter.itemClickBuscar = itemClickBuscar
            adapter.dataList = listAnimesPosibles
            binding.rvResultadoBusqueda.adapter = adapter
            binding.rvResultadoBusqueda.layoutManager = LinearLayoutManager(
                this@AniMiolkNovaAnimesActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}