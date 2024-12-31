package com.olatunbosun.moviedatabaseapp.screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.olatunbosun.moviedatabaseapp.model.Movie
import com.olatunbosun.moviedatabaseapp.service.createMovieService
import kotlinx.coroutines.launch
import java.util.logging.Logger



@Composable
fun MovieDetailsScreen(movieId: String?, navController: NavController) {
    val token  = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0Y2Y1YzMxY2MwNTc1OGFiYTFmODRkZTY2YzQ3YzBiYyIsIm5iZiI6MTczNTA2NjM2My43ODQ5OTk4LCJzdWIiOiI2NzZiMDJmYmU4ZmE4NjYxYWY2NGNiYzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.lJ_fx50SR8DhffEfpZ_EgEGYdBiSfvlY3OQdeexD2mc"

    val movieService = remember { createMovieService(token) }
    val coroutineScope = rememberCoroutineScope()
    var movie by remember { mutableStateOf<Movie?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(movieId) {
        coroutineScope.launch {
            try {
                movie = movieService.getMovieDetails(movieId!!)
            } catch (e: Exception) {
                errorMessage = "Error fetching movie details: ${e.message}"
            }
        }
    }

    if (movie != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie!!.backdropPath}"
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = movie!!.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = movie!!.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Release Date: ${movie!!.releaseDate}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rating: ${movie!!.voteAverage} (${movie!!.voteCount} votes)",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Overview",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie!!.overview,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigateUp() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Back to Movies")
            }
        }
    } else if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
    }
}



//@Composable
//fun MovieDetailsScreen(movieId: String?, navController: NavController) {
//    val token  = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0Y2Y1YzMxY2MwNTc1OGFiYTFmODRkZTY2YzQ3YzBiYyIsIm5iZiI6MTczNTA2NjM2My43ODQ5OTk4LCJzdWIiOiI2NzZiMDJmYmU4ZmE4NjYxYWY2NGNiYzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.lJ_fx50SR8DhffEfpZ_EgEGYdBiSfvlY3OQdeexD2mc"
//
//    val movieService = remember { createMovieService(token) }
//    val coroutineScope = rememberCoroutineScope()
//    var movie by remember { mutableStateOf<Movie?>(null) }
//    var errorMessage by remember { mutableStateOf("") }
//
//    LaunchedEffect(movieId) {
//        coroutineScope.launch {
//            try {
//                movie = movieService.getMovieDetails(movieId!!)
//            } catch (e: Exception) {
//                errorMessage = "Error fetching movie details: ${e.message}"
//            }
//        }
//    }
//
//    if (movie != null) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            val imageUrl = "https://image.tmdb.org/t/p/w500${movie!!.backdropPath}"
//
//            Logger.getLogger("MovieItem detail").info("Loading image: $imageUrl")
//
//            Image(
//                painter = rememberAsyncImagePainter(imageUrl),
//                contentDescription = movie!!.title,
//                modifier = Modifier.fillMaxWidth().height(200.dp),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = movie!!.title,
//                style = MaterialTheme.typography.headlineLarge,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Release Date: ${movie!!.releaseDate}",
//                style = MaterialTheme.typography.bodyLarge
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Rating: ${movie!!.voteAverage} (${movie!!.voteCount} votes)",
//                style = MaterialTheme.typography.bodyLarge
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = movie!!.overview,
//                style = MaterialTheme.typography.bodyLarge
//            )
//
//           Spacer(modifier = Modifier.height(16.dp))
//
//            Button(onClick = { navController.navigateUp() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                Text(text = "Back to Movies")
//            }
//        }
//    } else if (errorMessage.isNotEmpty()) {
//        Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
//    }
//}
