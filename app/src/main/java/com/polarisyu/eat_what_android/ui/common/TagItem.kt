package com.polarisyu.eat_what_android.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.polarisyu.eat_what_android.R
import com.polarisyu.eat_what_android.data.model.Tag

@Composable
fun TagItem(tag: Tag, isSelected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
            .background(
                color = if (isSelected) colorResource(id = R.color.primary_color).copy(alpha = 0.8f) else colorResource(id = R.color.primary_color).copy(alpha = 0.2f),
            )
    ) {
        Text(
            text = tag.name,
            color = if (isSelected) Color.White else Color.Black,
        )

    }
}