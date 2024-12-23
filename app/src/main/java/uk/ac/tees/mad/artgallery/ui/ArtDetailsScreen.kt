package uk.ac.tees.mad.artgallery.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record


@Composable
fun ArtDetailsScreen(
    artDetails: Record
){
    Column (
        modifier = Modifier
            .padding(10.dp,30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                FullArtDetail(artDetails)
            }
        }
    }
}

@Composable
fun FullArtDetail(
    artDetails: Record
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription ="App Icon"
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        modifier = Modifier
            .padding(10.dp),
        text = "Title: ${artDetails.title}",
        fontSize = 25.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp),
        text = "Department: ${artDetails.department}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Culture: ${artDetails.culture}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Description: ${artDetails.description}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Label Text: ${artDetails.labeltext}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Provenance: ${artDetails.provenance}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Dated: ${artDetails.dated}",
        fontSize = 16.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { },
        text = "Click to See more about it",
        fontSize = 16.sp
    )
}