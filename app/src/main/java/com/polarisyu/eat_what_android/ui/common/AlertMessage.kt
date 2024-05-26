package com.polarisyu.eat_what_android.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.polarisyu.eat_what_android.R

@Composable
fun AlertMessage(
    showAlert:Boolean,
    btnText:String,
    title:String?=null,
    text:String?=null,
    onDismiss:()-> Unit,
){
    if(showAlert){
        AlertDialog(
            onDismissRequest = { onDismiss },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(btnText)
                }
            },
            title= {
                if (title != null) {
                    Text(title)
                }
            },
            text={
                if (text != null) {
                    Text(text)
                }else{
                    Text(text = stringResource(id = R.string.addFoodCard_errorMsg_lackOfInput))
                }
            },
            textContentColor = Color.Black,
            containerColor= Color.White,
            titleContentColor = Color.Black

        )
    }

}