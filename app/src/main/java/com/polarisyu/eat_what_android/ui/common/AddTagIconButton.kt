package com.polarisyu.eat_what_android.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.polarisyu.eat_what_android.R

@Composable
fun AddTagIconButton(){
    val showDialog = remember { mutableStateOf(false) }
    Icon(
        imageVector = Icons.Default.AddBox,
        contentDescription = "Add Tag",
        tint = colorResource(id = R.color.primary_color),
        modifier = Modifier.clickable(onClick = {showDialog.value=true}).size(30.dp)
    )
}