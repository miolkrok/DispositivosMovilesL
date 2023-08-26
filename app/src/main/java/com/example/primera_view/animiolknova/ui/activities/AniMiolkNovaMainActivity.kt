package com.example.primera_view.animiolknova.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primera_view.R
import com.example.primera_view.animiolknova.ui.fragments.FavoritesFragment
import com.example.primera_view.animiolknova.ui.fragments.SearchFragment
import com.example.primera_view.databinding.ActivityAniMiolkNovaMainBinding
import com.example.primera_view.ui.fragments.FirstFragment
import com.example.primera_view.ui.fragments.FragmentSecond
import com.example.primera_view.ui.fragments.FragmentThird
import com.example.primera_view.ui.utilities.FragmentsManager

class AniMiolkNovaMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAniMiolkNovaMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniMiolkNovaMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClass()
    }

    private fun initClass(){

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inicio -> {
                    // Respond to navigation item 1 click
                    FragmentsManager().replaceFragment(
                        supportFragmentManager,
                        binding.frameContainer.id,
                        SearchFragment()
                    )

                    true
                }
                R.id.fav -> {

                    FragmentsManager().addFragment(
                        supportFragmentManager,
                        binding.frameContainer.id,
                        FavoritesFragment()
                    )
                    true
                }
                else -> false
            }
        }
    }
}