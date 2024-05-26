package com.polarisyu.eat_what_android.ui.taglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polarisyu.eat_what_android.data.dataRepository.TagsRepository
import com.polarisyu.eat_what_android.data.model.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TagsListViewModel(): ViewModel() {
    private val tagsRepository = TagsRepository()
    val tags = tagsRepository.fetchTags().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _selectedTag = MutableStateFlow<Tag?>(null)
    val selectedTag: StateFlow<Tag?> = _selectedTag.asStateFlow()

    fun selectTag(tagId: String) {
        if (_selectedTag.value?.id != tagId) {
            viewModelScope.launch {
                tagsRepository.fetchSelectedTagById(tagId).collect { tag ->
                    _selectedTag.value = tag
                }
            }
        }
    }

    fun addNewTag(tagName: String) {
        viewModelScope.launch {
            val newTag = Tag(name = tagName)
            tagsRepository.addTag(newTag)
        }
    }
    fun updateTag(tagId: String, newName: String) {
        viewModelScope.launch {
            tagsRepository.updateTag(tagId, newName)
        }
    }
    fun deleteTag(tagId: String) {
        viewModelScope.launch {
            tagsRepository.deleteTag(tagId)
        }
    }

    //cancel ui selected
    fun clearSelectedTag() {
        _selectedTag.value = null
    }

}