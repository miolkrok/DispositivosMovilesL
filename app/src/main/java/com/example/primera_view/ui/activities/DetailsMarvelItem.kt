package com.example.primera_view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primera_view.logic.data.MarvelChars
import com.example.primera_view.databinding.ActivityDetailsMarvelItemBinding
import com.squareup.picasso.Picasso

class DetailsMarvelItem : AppCompatActivity() {
    private lateinit var binding :ActivityDetailsMarvelItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
//        var name : String? = ""
//        intent.extras?.let {
//            name = it.getString("name")
//
//        }
//        if(!name.isNullOrEmpty()){
//            binding.txtName.text = name
//        }
        val item = intent.getParcelableExtra<MarvelChars>("name")
        if (item != null) {
            binding.txtName. text = item.name
            binding.txtComic. text = item.comic
            Picasso.get().load(item.image).into(binding.imgMarvel)
        }
    }

}