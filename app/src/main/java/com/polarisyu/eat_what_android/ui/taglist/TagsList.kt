package com.polarisyu.eat_what_android.ui.taglist

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polarisyu.eat_what_android.R
import com.polarisyu.eat_what_android.ui.common.NormalButton
import com.polarisyu.eat_what_android.ui.common.PrimaryButton
import com.polarisyu.eat_what_android.ui.common.TagItem
import com.polarisyu.eat_what_android.ui.common.TagsSelectDialog
import com.polarisyu.eat_what_android.ui.common.TileText

@Composable
fun TagsList(viewModel:TagsListViewModel, navController: NavController){
    val tags = viewModel.tags.collectAsState().value
    val selectedTag = viewModel.selectedTag.collectAsState().value
    val isPopUp = viewModel.showDialog.collectAsState()
    var tagName by rememberSaveable { mutableStateOf("") }
    val isEdit = selectedTag?.id?.isNotEmpty() ?: false

    if(isPopUp.value){
        TagsSelectDialog(
            isPopUp = isPopUp.value,
            isEdit = isEdit,
            title = stringResource(if(isEdit) R.string.tagsList_popUp_editTitle else R.string.tagsList_popUp_addTitle),
            description = stringResource(if(isEdit) R.string.tagsList_popUp_editDescription else R.string.tagsList_popUp_addDescription),
            inputText = tagName,
            onInputTextChange = {newText -> tagName = newText},
            onDismiss = {
                viewModel.toggleDialog(show = false)
                if (isEdit) {
                    selectedTag?.id?.let { id ->
                        viewModel.updateTag(id, tagName)
                    }
                } else {
                    viewModel.addNewTag(tagName)
                }
                viewModel.clearSelectedTag()

            }
        )
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        val contentHeight = maxHeight - 60.dp
        Column (
            modifier = Modifier
                .align(Alignment.TopStart)
                .heightIn(max = contentHeight)
        ){
            Spacer(modifier = Modifier.height(30.dp))
            TileText(text = stringResource(id = R.string.tagsList_title))
            tags.forEach { tag ->
                TagItem(tag,
                    isSelected = selectedTag?.id == tag.id,
                    onClick = { tag.id?.let {
                    viewModel.selectTag(it)
                } })
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(60.dp)
        ){
            NormalButton(
                text = stringResource(id = R.string.btn_back),
                modifier= Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .height(50.dp),
                action = { navController.navigate("Home") }
            )
            PrimaryButton(
                text = stringResource(if (selectedTag != null) R.string.btn_edit else R.string.btn_add),
                modifier= Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .height(50.dp),
                action = {viewModel.toggleDialog(show = true)}
            )
        }
    }
}