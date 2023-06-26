package com.example.primera_view.logic.lists

import com.example.primera_view.data.entities.LoginUser
import com.example.primera_view.data.entities.MarvelChars

class ListsItems {

    fun returnItems(): List<LoginUser>  {
        var items = listOf<LoginUser>(
            LoginUser("1","1"),
            LoginUser("2","2"),
            LoginUser("3","3"),
            LoginUser("4","4"),
            LoginUser("5","5")

            )
        return items

    }

    fun returnMarvelChars(): List<MarvelChars>{
        val  items = listOf(
        MarvelChars(
            1,
            "wolverine",
            "wolverine",
            "https://comicvine.gamespot.com/a/uploads/scale_small/5/57023/7469590-wolverinerb.jpg"
        ),
        MarvelChars(
            2,
            "Spiderman",
            "The Amazing Spiderman",
            "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8126579-amazing_spider-man_vol_5_54_stormbreakers_variant_textless.jpg"
        ),
        MarvelChars(
            3,
            "Deapool",
            "Deapool",
            "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8926324-large-2680196.jpg"
        ),
        MarvelChars(
            4,
            "Black Widow",
            "The Avengers",
            "https://comicvine.gamespot.com/a/uploads/scale_small/1/14487/7835285-b7f54158-13c3-4646-bbee-033a765c570d.jpeg"
        ),
        MarvelChars(
            5,
            "Iron Man",
            "Iron Man",
            "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8654427-ezgif-1-2f113089e4.jpg"
        ),
        )
        return items
    }

}