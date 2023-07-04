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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
//import com.example.primera_view.ARG_PARAM1
//import com.example.primera_view.ARG_PARAM2
import com.example.primera_view.R
import com.example.primera_view.data.entities.MarvelChars
import com.example.primera_view.databinding.FragmentFirstBinding
import com.example.primera_view.logic.jikanLogic.JikanAnimeLogic
import com.example.primera_view.logic.jikanLogic.MarvelLogic
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
        private lateinit var lmanager: LinearLayoutManager
        private var rvAdapter: MarvelAdapter = MarvelAdapter { sendMarvelItem(it) }

        private lateinit var marvelCharchItems: MutableList<MarvelChars>


        override fun onCreateView (
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstancesState: Bundle?): View? {

            binding = FragmentFirstBinding.inflate(layoutInflater,
                container,false)

            lmanager = LinearLayoutManager(
                requireActivity(),LinearLayoutManager.VERTICAL,
                false)

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
        chargeDataRV("cap")

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV("cap")
            binding.rvSwipe.isRefreshing = false
        }

        binding.rvMarvelChars.addOnScrollListener(
            object : RecyclerView.OnScrollListener(){

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if(dx>0) {
                        val v = lmanager.childCount
                        val p = lmanager.findFirstVisibleItemPosition()
                        val t = lmanager.itemCount

                        if ((v + p) >= t) {
                            lifecycleScope.launch((Dispatchers.IO)) {
                                /*val newItems = MarvelLogic().getMarvelChars(
                                    "spider",
                                    20
                                )*/
                                val newItems = JikanAnimeLogic().getAllAnimes()
                                withContext(Dispatchers.Main) {
                                    rvAdapter.updateListItems(newItems)
                                }

                            }
//                        chargeDataRV("spider")
                        }
                    }

                }
        })
        binding.txtFilter.addTextChangedListener {filterText ->

            marvelCharchItems.filter { items ->
                items.name.contains(filterText.toString())
            }
            rvAdapter.replaceListAdapter(marvelCharchItems)
        }

    }
    fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }
    fun corrotine() {
        lifecycleScope.launch(Dispatchers.Main) {
            var  name = "Bayron"
            name = withContext(Dispatchers.IO){
                name = "Jairo"
                return@withContext name
            }

            binding.cardView.radius
        }
    }
    fun chargeDataRV(search: String) {
        lifecycleScope.launch(Dispatchers.IO) {

           // var jikanCharsItems:  MarvelLogic().getMarvelChars(name = search , limit = 20)

            rvAdapter.items = JikanAnimeLogic().getAllAnimes()
//            val rvAdapter = MarvelAdapter(
                //ListsItems().returnMarvelChars(),
//                )JikanAnimeLogic().getAllAnimes(,
//                MarvelLogic().getMarvelChars(name = search , limit = 20)

//            ){sendMarvelItem(it)}

            withContext(Dispatchers.Main){
                //val rvMarvel =
                with (binding.rvMarvelChars) {
                    this.adapter = rvAdapter
                    this.layoutManager = lmanager
                }
            }
        }

    }

}


