package com.polarisyu.eat_what_android.data.dataRepository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.polarisyu.eat_what_android.data.model.Tag
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class TagsRepository {
    private val db = Firebase.firestore

    private fun tagToMap(tag: Tag): Map<String, Any?> {
        return mapOf(
            "name" to tag.name
        )
    }
    private fun mapToTag(document: DocumentSnapshot): Tag? {
        if(document.exists())
        return Tag(
            id = document.id,
            name = document.getString("name") ?: "",
        )
        return null
    }

    fun fetchTags(): Flow<List<Tag>> = callbackFlow {
        val listener = db.collection("tags")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.e("TagListVM", "Error fetching tags", e)
                    close(e)
                    return@addSnapshotListener
                }
                val tags = snapshot?.documents?.mapNotNull { document ->
                    mapToTag(document)
                } ?: listOf()
                trySend(tags).isSuccess
            }
        awaitClose { listener.remove() }
        Log.e("TagListVM", "Finish fetchTags fun")
    }

    fun fetchSelectedTagById(tagId: String): Flow<Tag?> = flow {
        val snapshot = db.collection("tags").document(tagId).get().await()
        emit(snapshot.toObject(Tag::class.java))
    }

    fun checkTagExists(tagName: String): Flow<Boolean> = callbackFlow {
        val query = db.collection("tags").whereEqualTo("name", tagName)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 发送查询结果，如果文档不为空，则表示标签已存在
                trySend(task.result?.isEmpty == false).isSuccess
            } else {
                // 查询失败，发送 false 或处理错误
                trySend(false).isSuccess
            }
        }
        awaitClose { }
    }

    fun addTag(tag: Tag) {
        val tagMap = tagToMap(tag)
        db.collection("tags")
            .add(tagMap)
            .addOnSuccessListener { documentReference ->
                // 如果需要，这里可以处理生成的 ID
                println("Tag added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

//下面的fun應該要改成傳參數是Tag
    fun updateTag(tagId: String, newTagName: String) {
        Firebase.firestore.collection("tags").document(tagId)
            .update("name", newTagName)
            .addOnSuccessListener { /* Success Handle */ }
            .addOnFailureListener { e -> /* Error Handle */ }
    }

    fun deleteTag(tagId: String) {
        Firebase.firestore.collection("tags").document(tagId)
            .delete()
            .addOnSuccessListener { /* Success Handle */ }
            .addOnFailureListener { e -> /* Error Handle */ }
    }


}