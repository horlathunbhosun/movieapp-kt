package com.olatunbosun.moviedatabaseapp.screen

import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.olatunbosun.moviedatabaseapp.model.Movie
import com.olatunbosun.moviedatabaseapp.service.createMovieService
import kotlinx.coroutines.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.util.logging.Logger



@Composable
fun MovieListScreen(navController: NavController) {
    val token  = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0Y2Y1YzMxY2MwNTc1OGFiYTFmODRkZTY2YzQ3YzBiYyIsIm5iZiI6MTczNTA2NjM2My43ODQ5OTk4LCJzdWIiOiI2NzZiMDJmYmU4ZmE4NjYxYWY2NGNiYzgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.lJ_fx50SR8DhffEfpZ_EgEGYdBiSfvlY3OQdeexD2mc"
    val movieService = remember { createMovieService(token) }
    val coroutineScope = rememberCoroutineScope()
    var movies by remember { mutableStateOf(emptyList<Movie>()) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = movieService.getPopularMovies()
                movies = response.results
            } catch (e: Exception) {
                errorMessage = "Error fetching movies: ${e.message}"
            }
        }
    }

//    Column(modifier = Modifier.fillMaxSize()) {
//        if (errorMessage.isNotEmpty()) {
//            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
//        } else {
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                items(movies.size) { index ->
//                    val movie = movies[index]
//                    MovieItem(movie) {
//                        navController.navigate("details/${movie.id}")
//                    }
//                }
//            }
//        }
//    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    MovieItem(movie) {
                        navController.navigate("details/${movie.id}")
                    }
                }
            }
        }
    }
}







@Composable
//fun MovieItem(movie: Movie, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onClick() },
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(modifier = Modifier.padding(8.dp)) {
//            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
//            Logger.getLogger("MovieItem").info("Loading image: $imageUrl")
//            Image(
//                painter = rememberAsyncImagePainter(imageUrl),
//                contentDescription = movie.title,
//                modifier = Modifier.size(100.dp),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
//                Text(text = movie.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
//                Text(text = "Rating: ${movie.voteAverage}", style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//}


fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rating: ${movie.voteAverage}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
