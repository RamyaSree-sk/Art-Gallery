package uk.ac.tees.mad.artgallery.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.theme.poppinsFam
import uk.ac.tees.mad.artgallery.ui.theme.ubuntuFam


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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FullArtDetail(
    artDetails: Record
){
    Card(
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f/3f),
            model = artDetails.primaryimageurl,
            failure = placeholder(R.drawable.failure),
            contentDescription ="Art Image"
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
    Text(
        modifier = Modifier
            .padding(10.dp),
        text = artDetails.title,
        fontFamily = poppinsFam,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp),
        text = artDetails.department,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFam,
        fontSize = 20.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Culture: ${artDetails.culture}",
        fontFamily = ubuntuFam,
        fontSize = 18.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Description: ${artDetails.description}",
        fontFamily = ubuntuFam,
        fontSize = 18.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Label Text: ${artDetails.labeltext}",
        fontFamily = ubuntuFam,
        fontSize = 18.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Provenance: ${artDetails.provenance}",
        fontFamily = ubuntuFam,
        fontSize = 18.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = "Dated: ${artDetails.dated}",
        fontFamily = ubuntuFam,
        fontSize = 18.sp
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { },
        text = "Click to See more about it",
        fontFamily = ubuntuFam,
        fontSize = 16.sp
    )
}