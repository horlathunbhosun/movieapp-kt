package com.olatunbosun.moviedatabaseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.olatunbosun.moviedatabaseapp.ui.theme.MovieDatabaseAppTheme


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olatunbosun.moviedatabaseapp.screen.MovieDetailsScreen
import com.olatunbosun.moviedatabaseapp.screen.MovieListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MovieApp()

//            MovieDatabaseAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MovieDatabaseAppTheme {
//        Greeting("Android")
//    }
//}

//@Composable
//fun MovieApp() {
//    val navController: NavHostController = rememberNavController()
//    NavHost(navController = navController, startDestination = "home") {
//        composable("home") { MovieListScreen(navController) }
//        composable("details/{movieTitle}") { backStackEntry ->
//            val movieTitle = backStackEntry.arguments?.getString("movieTitle")
//            MovieDetailsScreen(movieTitle)
//        }
//    }
//}

// App Entry Point
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Magenta),
                title = { Text("Movie Database", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    ) }

            )
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = "home", Modifier.padding(padding)) {
            composable("home") { MovieListScreen(navController) }
            composable("details/{movieTitle}") { backStackEntry ->
                val movieTitle = backStackEntry.arguments?.getString("movieTitle")
                MovieDetailsScreen(movieTitle, navController)
            }
        }
    }
}