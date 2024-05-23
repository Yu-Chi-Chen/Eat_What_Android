package com.polarisyu.eat_what_android.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.polarisyu.eat_what_android.R

@Composable
fun TileText(text:String){
    Text(text=text, fontSize = 30.sp, color = colorResource(id = R.color.primary_color),fontWeight=FontWeight.Bold)
}