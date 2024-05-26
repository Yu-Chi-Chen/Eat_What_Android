package com.polarisyu.eat_what_android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polarisyu.eat_what_android.ui.addfoodcard.AddFoodCard
import com.polarisyu.eat_what_android.ui.addfoodcard.AddFoodCardViewModel
import com.polarisyu.eat_what_android.ui.home.HomePage
import com.polarisyu.eat_what_android.ui.theme.Eat_What_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Eat_What_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("Home"){
            HomePage (onNavigate = { route ->
                navController.navigate(route)
            })
        }
        composable("RandomChoose"){ RandomChooseScreen()}
        composable("SelectByTags"){ SelectByTagsScreen()}
        composable("AddFoodList"){
            val viewModel = ViewModelProvider(it).get(AddFoodCardViewModel::class.java)
            AddFoodCard(viewModel = viewModel,navController = navController)
        }
        composable("ViewAllTags"){ TagsPage()}
        composable("ViewAllFoodCards"){ FoodCardListPage()}
    }

}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Eat_What_AndroidTheme {
        App()
    }
}

@Composable
fun RandomChooseScreen(){
    //RandomChoose
}

@Composable
fun SelectByTagsScreen(){
    //RandomChoose
}


@Composable
fun TagsPage(){

}

@Composable
fun FoodCardListPage(){}