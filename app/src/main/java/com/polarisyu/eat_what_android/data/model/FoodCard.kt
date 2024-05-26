package com.polarisyu.eat_what_android.data.model

data class FoodCard(
    val name:String,
    val price: Double,
    val shopInfo: String? = null,
    val imageUrl: String? = null,
    val tags: List<String>
)
