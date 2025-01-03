package uk.ac.tees.mad.artgallery.ui.homeScreen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import uk.ac.tees.mad.artgallery.ArtDetailsActivity
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel
import uk.ac.tees.mad.artgallery.ui.theme.quicksandFam


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    innerpadding: PaddingValues
){

    val artDetails by homeViewModel.artDetail.collectAsState()

    val text by homeViewModel.text.collectAsState()
    val active by homeViewModel.isSearching.collectAsState()
    val searchResult by homeViewModel.searchResult.collectAsState()


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerpadding)
    ){
        DockedSearchBar(
            query = text,
            onQueryChange = homeViewModel::updateSearchQuery,
            onSearch = homeViewModel::updateSearchQuery,
            active = active,
            onActiveChange = {
                homeViewModel.togggleSearch()
            },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .align(Alignment.CenterHorizontally),
            placeholder = { Text(text = "Search by division or department")},
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search the art!!")
            },
            trailingIcon = {
                if(active){
                    Icon(modifier = Modifier
                        .clickable {
                            if (text.isNotEmpty()){
                                homeViewModel.updateSearchQuery("")
                            }else{
                                homeViewModel.togggleSearch()
                            }
                        },
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close button")
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if(text.isNotEmpty()){
                    items(searchResult){record->
                        ArtDetailListItem(record = record)
                    }
                }
            }
        }
        if (!active){
            LazyColumn(
                modifier = Modifier
                    .padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(artDetails.size){ind->
                    ArtDetailListItem(record = artDetails[ind])
                    if (ind >= artDetails.size-3 && homeViewModel.isNetworkAvailable()){
                        homeViewModel.fetchRecordfromApi()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
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
                GlideImage(
                    modifier = Modifier
                        .padding(5.dp)
                        .aspectRatio(1.5f/1.5f),
                    model = record.primaryimageurl,
                    failure = placeholder(R.drawable.failure),
                    contentDescription ="Art Image"
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