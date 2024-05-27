package com.polarisyu.eat_what_android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.polarisyu.eat_what_android.R
import com.polarisyu.eat_what_android.ui.common.NormalButton
import com.polarisyu.eat_what_android.ui.common.PrimaryButton

@Composable
fun HomePage(onNavigate: (String) -> Unit){
    Scaffold (
        content ={padding ->
            Column(modifier= Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier=Modifier.weight(1.5f))
                Image(painter = painterResource(id = R.drawable.eat_what), contentDescription = "HomePage Picture")

                PrimaryButton(text = stringResource(id = R.string.mainOption_lottery), action = { onNavigate("RandomChoose")})

                Spacer(modifier=Modifier.height(8.dp))
                PrimaryButton(text = stringResource(id = R.string.mainOption_selectTags), action = { onNavigate("SelectByTags") })

                Spacer(modifier=Modifier.height(8.dp))
                NormalButton(text = stringResource(id = R.string.mainOption_addFoodList), action = { onNavigate("AddFoodList") })

                Spacer(modifier=Modifier.height(8.dp))
                Row {
                    NormalButton(
                        text = stringResource(id = R.string.mainOption_viewAllTags),
                        modifier=Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                            .height(50.dp),
                        action = { onNavigate("ViewAllTags")})
                    NormalButton(
                        text = stringResource(id = R.string.mainOption_viewAllFoods),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .height(50.dp),
                        action = { onNavigate("ViewAllFoodCards")})
                }
                Spacer(modifier=Modifier.weight(1f))

            }
        }
    )
}