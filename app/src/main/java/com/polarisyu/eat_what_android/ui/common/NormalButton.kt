package com.polarisyu.eat_what_android.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.polarisyu.eat_what_android.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonElevation

@Composable
fun NormalButton(
    text: String,
    action:() -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
){
    Button(
        shape = RoundedCornerShape(10.dp),
        onClick =  action ,
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.primary_color)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = colorResource(id = R.color.primary_color),
        )
    ) {
        Text(text)
    }
}