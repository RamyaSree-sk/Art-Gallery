package uk.ac.tees.mad.artgallery.ui.homeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.theme.Purple40
import uk.ac.tees.mad.artgallery.ui.theme.dosisFam
import uk.ac.tees.mad.artgallery.ui.theme.neueFam
import uk.ac.tees.mad.artgallery.ui.theme.poppinsFam
import uk.ac.tees.mad.artgallery.ui.theme.quicksandFam


@Composable
fun ProfileScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    innerpadding: PaddingValues
) {
    val width = LocalConfiguration.current.screenWidthDp.dp * 0.6f
    val currentUser by authViewModel.currentUser.collectAsState()

    // Fetch current user information
    LaunchedEffect(key1 = Unit) {
        Log.i("The current user:", currentUser.toString())
        authViewModel.fetchCurrentUser()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerpadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp),
            text = "Profile",
            fontSize = 35.sp
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(width)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            onClick = { /* Handle update picture click */ }
        ) {
            Text(
                text = "Add new/Update picture",
                fontFamily = quicksandFam,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.8f),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            shape = RoundedCornerShape(35.dp)
        ) {
            currentUser?.fullname?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    fontFamily = dosisFam,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.8f),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            shape = RoundedCornerShape(35.dp)
        ) {
            currentUser?.email?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    fontFamily = dosisFam,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
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
                contentColor = Color.Gray
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(
                text = "Logout!",
                fontFamily = poppinsFam,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
