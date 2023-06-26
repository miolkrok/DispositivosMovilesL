package com.example.primera_view.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
//import com.example.primera_view.ARG_PARAM1
//import com.example.primera_view.ARG_PARAM2
import com.example.primera_view.R
import com.example.primera_view.data.entities.MarvelChars
import com.example.primera_view.databinding.FragmentFirstBinding
import com.example.primera_view.logic.jikanLogic.JikanAnimeLogic
import com.example.primera_view.logic.lists.ListsItems
import com.example.primera_view.ui.activities.DetailsMarvelItem
import com.example.primera_view.ui.activities.MainActivity
import com.example.primera_view.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

        private lateinit var binding: FragmentFirstBinding

        override fun onCreateView (
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstancesState: Bundle?): View? {

            binding = FragmentFirstBinding.inflate(layoutInflater,
                container,false)

            return binding.root
        }

    override fun onStart() {
        super.onStart()
        val names = arrayListOf<String>(
            "Carlos",
            "Juan",
            "Roberto",
            "Rosa",
            "Pepe",
            "Cristiano"
        )
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout,
            names
        )
        binding.spinner.adapter = adapter
        chargeDataRV()

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }

    }
    fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }
    fun chargeDataRV() {
        lifecycleScope.launch(Dispatchers.IO) {
            val rvAdapter = MarvelAdapter(
                //ListsItems().returnMarvelChars(),
                JikanAnimeLogic().getAllAnimes()
            ){sendMarvelItem(it)}

            withContext(Dispatchers.Main){
                //val rvMarvel =
                with (binding.rvMarvelChars) {

                    this.adapter = rvAdapter
                    this.layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL,
                        false)
                }
            }
        }

    }

}


