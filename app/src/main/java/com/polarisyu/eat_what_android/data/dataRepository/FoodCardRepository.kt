package com.polarisyu.eat_what_android.data.dataRepository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.polarisyu.eat_what_android.data.model.FoodCard

class FoodCardRepository {
    private val db = Firebase.firestore

    fun addFoodCard(foodCard: FoodCard, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        val foodCardMap = hashMapOf(
            "name" to foodCard.name,
            "price" to foodCard.price,
            "shopInfo" to foodCard.shopInfo,
            "imageUrl" to foodCard.imageUrl,
            "tags" to foodCard.tags
        )

        db.collection("foodCards").add(foodCardMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
    }
}
