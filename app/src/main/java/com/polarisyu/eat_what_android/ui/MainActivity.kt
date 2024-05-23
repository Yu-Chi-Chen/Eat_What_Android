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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.polarisyu.eat_what_android.ui.addfoodcard.AddFoodCard
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
        composable("home"){
            HomePage (onNavigate = { route ->
                navController.navigate(route)
            })
        }
        composable("RandomChoose"){ RandomChooseScreen()}
        composable("SelectByTags"){ SelectByTagsScreen()}
        composable("AddFoodList"){ FoodListAddPage()}
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
fun FoodListAddPage() {
    // FoodListAddPage
    AddFoodCard {

    }
}

@Composable
fun TagsPage(){

}

@Composable
fun FoodCardListPage(){}