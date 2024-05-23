package com.polarisyu.eat_what_android.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.polarisyu.eat_what_android.R

@Composable
fun TextInputField(
    text: String,
    onTextChange: (String) -> Unit,
){
    val primaryColor = colorResource(id = R.color.primary_color)
    val errorMessage = R.string.addFoodCard_errorMsg_lackOfInput

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = primaryColor,
            unfocusedBorderColor = primaryColor,

        ),

    )
}