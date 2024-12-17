package uk.ac.tees.mad.artgallery.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "This is the Home Screen!!",
                fontSize = 20.sp
            )
        }
    }
}