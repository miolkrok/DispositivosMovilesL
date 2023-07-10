package com.example.primera_view.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.primera_view.ARG_PARAM1
//import com.example.primera_view.ARG_PARAM2
import com.example.primera_view.R
import com.example.primera_view.logic.data.MarvelChars
import com.example.primera_view.databinding.FragmentFirstBinding
import com.example.primera_view.logic.jikanLogic.MarvelLogic
import com.example.primera_view.ui.activities.DetailsMarvelItem
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
        private lateinit var  gManager : GridLayoutManager
        private var rvAdapter: MarvelAdapter =
            MarvelAdapter { sendMarvelItem(it) }

        private var marvelCharsItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()


        override fun onCreateView (
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstancesState: Bundle?): View? {

            binding = FragmentFirstBinding.inflate(layoutInflater,
                container,false)

            lmanager = LinearLayoutManager(
                requireActivity(),LinearLayoutManager.HORIZONTAL,
                false)

            gManager = GridLayoutManager(requireActivity(), 2)
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
                                val newItems = MarvelLogic().getAllMarvelChars(
                                    0,
                                    99
                                )
//                                val newItems = JikanAnimeLogic().getAllAnimes()
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

            val newItems = marvelCharsItems.filter { items ->
                items.name.contains(filterText.toString().lowercase())
            }
            rvAdapter.replaceListAdapter(newItems)

        }

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

        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems =
                withContext(Dispatchers.IO) {
                return@withContext (MarvelLogic().getMarvelChars(
                    0,
                    99))
            }
            rvAdapter.items = marvelCharsItems
            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }

//        lifecycleScope.launch(Dispatchers.IO) {

//            var marvelCharsItems = MarvelLogic().getMarvelChars(
//                "spider", 20)
//
           // var jikanCharsItems:  MarvelLogic().getMarvelChars(name = search , limit = 20)

//            rvAdapter.items = JikanAnimeLogic().getAllAnimes()
//            val rvAdapter = MarvelAdapter(
//                ListsItems().returnMarvelChars(),
//                )JikanAnimeLogic().getAllAnimes(,
//                MarvelLogic().getMarvelChars(name = search , limit = 20))

//            ){sendMarvelItem(it)}


                //val rvMarvel =


        }

    }

    fun chargeDataCh(search:String){
        lifecycleScope.launch(Dispatchers.IO){
            rvAdapter.items = MarvelLogic().getAllMarvelChars(0,99)
            withContext(Dispatchers.Main){
                with(binding.rvMarvelChars){
                    this.adapter = rvAdapter
                    this.layoutManager = gManager
                }
            }
        }
    }

    fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

}


