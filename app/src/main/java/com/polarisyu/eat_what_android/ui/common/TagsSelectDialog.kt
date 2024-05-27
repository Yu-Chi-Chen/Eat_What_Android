package com.polarisyu.eat_what_android.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.polarisyu.eat_what_android.R

@Composable
fun TagsSelectDialog(
    isPopUp:Boolean,
    isEdit:Boolean,
    title:String,
    description:String,
    inputText: String,
    onInputTextChange: (String) -> Unit,
    onDismiss:()-> Unit,
){
    val btnString:String=if(isEdit)stringResource(R.string.btn_edit) else stringResource(R.string.btn_add);
    if(isPopUp){
        AlertDialog(
            onDismissRequest = {onDismiss},
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    val textShadowOffset = Offset(5.0f, 10.0f)
                    Text(
                        text=stringResource(id = R.string.btn_cancel),
                        style = TextStyle(
                            color = Color.Black,
                            background = Color.Transparent,
                            shadow = Shadow(color = Color.Gray, offset = textShadowOffset, blurRadius = 10f)
                        )
                    )
                }
            },
            confirmButton = {
                PrimaryButton(
                    text = btnString,
                    modifier = Modifier.widthIn(min=20.dp),
                    action = { /*串firebase新增或是修改*/ }
                )
            },
            title= {Text(title)},
            text = {
                Column {
                    Text(text=description, modifier = Modifier.padding(start = 5.dp))
                    TextInputField(
                        text=inputText,
                        onTextChange = onInputTextChange
                    )
                }
            },
            textContentColor = Color.Black,
            containerColor= Color.White,
            titleContentColor = Color.Black
        )
    }
}