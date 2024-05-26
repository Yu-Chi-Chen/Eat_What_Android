package com.polarisyu.eat_what_android.ui.taglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polarisyu.eat_what_android.R
import com.polarisyu.eat_what_android.ui.common.NormalButton
import com.polarisyu.eat_what_android.ui.common.PrimaryButton
import com.polarisyu.eat_what_android.ui.common.TagItem
import com.polarisyu.eat_what_android.ui.common.TileText

@Composable
fun TagsList(viewModel:TagsListViewModel, navController: NavController){
    val tags = viewModel.tags.collectAsState().value
    val selectedTag = viewModel.selectedTag.collectAsState().value

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
                    viewModel.selectTag(
                        it
                    )
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
                text = stringResource(id = R.string.btn_add),
                modifier= Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .height(50.dp),
                action = {/**/}
            )
        }
    }
}