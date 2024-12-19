package uk.ac.tees.mad.artgallery.ui.homeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
){

    val width = LocalConfiguration.current.screenWidthDp.dp * 0.6f

    val currentUser by authViewModel.currentUser.collectAsState()

    LaunchedEffect(key1 = Unit) {
        Log.i("The current user:", currentUser.toString())
        authViewModel.fetchCurrentUser()
    }

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Profile",
                    fontSize = 30.sp
                )}
            )

        }
    ){innerpadding->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription ="Profile Picture",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(width)
                )
                Card (
                    elevation = CardDefaults.cardElevation(5.dp),
                    onClick = {}
                ){
                    Text(
                        text = "Add new/Update picture",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Card (
                    modifier = Modifier.fillMaxWidth(0.8f),
                    elevation = CardDefaults.elevatedCardElevation(10.dp),
                    shape = RoundedCornerShape(35.dp)

                ){
                    currentUser?.fullname?.let {
                        Text(
                            text = it,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))
                Card (
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    elevation = CardDefaults.elevatedCardElevation(10.dp),
                    shape = RoundedCornerShape(35.dp)

                ){
                    currentUser?.email?.let {
                        Text(
                            text = it,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    onClick = {
                        authViewModel.logout()
                        navController.navigate("auth_graph") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Purple40
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                ) {

                    Text(
                        text = "Logout!",
                        fontSize = 18.sp
                    )
                }

            }
        }
    }
}