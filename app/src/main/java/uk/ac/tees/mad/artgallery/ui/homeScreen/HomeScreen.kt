package uk.ac.tees.mad.artgallery.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){

    val artDetails by homeViewModel.artDetail.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = artDetails.toString(),
                fontSize = 20.sp
            )
        }
    }
}