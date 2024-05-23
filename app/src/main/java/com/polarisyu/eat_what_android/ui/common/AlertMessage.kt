package com.polarisyu.eat_what_android.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.polarisyu.eat_what_android.R

@Composable
fun AlertMessage(
    showAlert:Boolean,
    onDismiss:()-> Unit,
){
    if(showAlert){
        AlertDialog(
            onDismissRequest = { onDismiss },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK")
                }
            },
            text={Text(stringResource(id = R.string.addFoodCard_errorMsg_lackOfInput))},
        )
    }

}