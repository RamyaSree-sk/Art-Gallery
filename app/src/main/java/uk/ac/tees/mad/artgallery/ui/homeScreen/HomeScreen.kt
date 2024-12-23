package uk.ac.tees.mad.artgallery.ui.homeScreen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uk.ac.tees.mad.artgallery.ArtDetailsActivity
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel
import uk.ac.tees.mad.artgallery.ui.theme.quicksandFam


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    innerpadding: PaddingValues
){

    val artDetails by homeViewModel.artDetail.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(innerpadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(artDetails){record->
            ArtDetailListItem(record = record)
        }
    }
}

@Composable
fun ArtDetailListItem(
    record: Record
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card (
            modifier = Modifier
                .padding(10.dp),
            colors = CardDefaults.cardColors(),
            onClick = {
                val intent = Intent(context, ArtDetailsActivity::class.java).apply {
                    putExtra("art_detail", record)
                }
                context.startActivity(intent)
            }
        ){
            Card(
                modifier = Modifier
                    .padding(10.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription ="App Icon"
                )
            }
            Text(
                text = "Division: ${record.division}",
                fontSize = 20.sp,
                fontFamily = quicksandFam,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(20.dp,10.dp)
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = "Department: ${record.department}",
                fontSize = 16.sp,
                fontFamily = quicksandFam,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(20.dp, 10.dp)
            )
        }
    }
}