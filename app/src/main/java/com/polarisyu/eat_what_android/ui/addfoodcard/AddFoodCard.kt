package com.polarisyu.eat_what_android.ui.addfoodcard

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.polarisyu.eat_what_android.R
import com.polarisyu.eat_what_android.data.model.FoodCard
import com.polarisyu.eat_what_android.ui.common.AddTagIconButton
import com.polarisyu.eat_what_android.ui.common.AlertMessage
import com.polarisyu.eat_what_android.ui.common.NormalButton
import com.polarisyu.eat_what_android.ui.common.NormalText
import com.polarisyu.eat_what_android.ui.common.PrimaryButton
import com.polarisyu.eat_what_android.ui.common.TextInputField


@Composable
fun AddFoodCard(viewModel: AddFoodCardViewModel, navController: NavController){
    var foodName by rememberSaveable { mutableStateOf("") }
    var foodPrice by rememberSaveable { mutableStateOf("") }
    var storeInfo by rememberSaveable { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false)}

    fun isNaN(text: String):Boolean {
        return isNaN(foodPrice)
    }

    fun isEmpty():Boolean {
        return foodName.isEmpty() && foodPrice.isEmpty() && isNaN(foodPrice)
    }

    //Check has tag
    val tagsAvailable by viewModel.tagsAvailable.collectAsState()
    if(!tagsAvailable!!) {
        AlertMessage(showAlert = !tagsAvailable!!,
            btnText = stringResource(id = R.string.btn_goToAdd),
            title= stringResource(id = R.string.errMsg_lackOfTagTitle),
            text = stringResource(id = R.string.errMsg_lackOfTagText),
            onDismiss = {
            navController.navigate("ViewAllTags")
        })
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.handleImageSelected(uri)
    }
    val imageUrl = viewModel.imageURL.collectAsState().value

    if(showAlert){
        AlertMessage(showAlert = false, btnText = stringResource(id = R.string.btn_sure), onDismiss = {showAlert=false})
    }

    /*UI Layout*/
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
            Row {
                NormalText(stringResource(id = R.string.addFoodCard_foodName))
                Text("*", color = Color.Red)
            }
            TextInputField(
                text=foodName,
                onTextChange = {foodName=it}
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                NormalText(stringResource(id = R.string.addFoodCard_foodPrice))
                Text("*", color = Color.Red)
            }
            TextInputField(
                text=foodPrice,
                onTextChange = {foodPrice=it}
            )

            Spacer(modifier = Modifier.height(5.dp))
            NormalText(stringResource(id = R.string.addFoodCard_storeInfo))
            TextInputField(
                text=storeInfo,
                onTextChange = {storeInfo=it}
            )

            Spacer(modifier = Modifier.height(5.dp))
            NormalText(stringResource(id = R.string.addFoodCard_foodPhoto))
            PrimaryButton(text = stringResource(id = R.string.btn_addFoodCard_upload), action = {
                imagePickerLauncher.launch("image/*")
            })

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                NormalText(stringResource(id = R.string.addFoodCard_foodTags))
                Text("*", color = Color.Red)
                Spacer(Modifier.weight(1f))
                AddTagIconButton()

            }
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp,colorResource(id = R.color.primary_color)),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp)
            ) {

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
                action = {
                    if (!isEmpty()) {
                        val newFoodCard = FoodCard(
                            name = foodName,
                            price = foodPrice.toDouble(),
                            shopInfo = storeInfo,
                            imageUrl = imageUrl,
                            tags = listOf("Tag1", "Tag2")  // 这里示例使用静态标签，应由用户选择
                        )
                        viewModel.submitFoodCard(newFoodCard)
                    } else {
                        showAlert = true
                    }
                }
            )
        }
    }

}