package com.polarisyu.eat_what_android.ui.addfoodcard

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.polarisyu.eat_what_android.data.dataRepository.FoodCardRepository
import com.polarisyu.eat_what_android.data.model.FoodCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AddFoodCardViewModel: ViewModel() {
    private val foodCardRepository = FoodCardRepository()
    private val storageRef = FirebaseStorage.getInstance().reference
    private val _tagsAvailable = MutableStateFlow<Boolean?>(null)
    val tagsAvailable: StateFlow<Boolean?> = _tagsAvailable.asStateFlow()
    private val _imageURL = MutableStateFlow<String?>(null)
    val imageURL: StateFlow<String?> = _imageURL.asStateFlow()

    //Check has user defined tags
    init {
        checkTagsAvailable()
    }
    private fun checkTagsAvailable() {
        viewModelScope.launch {
            val tags = loadTagsFromFirestore()
            _tagsAvailable.value = tags.isNotEmpty()
        }
    }
    private suspend fun loadTagsFromFirestore(): List<String> {
        return listOf()  // 返回空列表表示没有标签
    }

    //Handle Image Upload Logic
    fun handleImageSelected(uri: Uri?) {
        viewModelScope.launch {
            val url = uri?.let { uploadImage(it) }
            url?.let {
                _imageURL.value = it  // 更新图片 URL 的状态
            }
        }
    }
    private suspend fun uploadImage(uri: Uri): String? {
        return try {
            withContext(Dispatchers.IO) {
                val path = "uploaded_images/${System.currentTimeMillis()}.jpg"
                val imageRef = storageRef.child(path)
                imageRef.putFile(uri).await()
                imageRef.downloadUrl.await().toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun submitFoodCard( foodCard: FoodCard){
        viewModelScope.launch {
            try {
                val updatedFoodCard = foodCard.copy(imageUrl = _imageURL.value)
                foodCardRepository.addFoodCard(updatedFoodCard,
                    onSuccess = {/*Handle*/},
                    onError = {e -> /*Handle*/})
            }catch (e:Exception){
                //Handle
                println("Error in adding food card: $e")
            }
        }
    }
}