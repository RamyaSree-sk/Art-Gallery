package uk.ac.tees.mad.artgallery.ui.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel
import uk.ac.tees.mad.artgallery.ui.theme.poppinsFam
import uk.ac.tees.mad.artgallery.ui.theme.quicksandFam
import uk.ac.tees.mad.artgallery.ui.theme.ubuntuFam


@Composable
fun ProfileScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    innerpadding: PaddingValues
) {
    val width = LocalConfiguration.current.screenWidthDp.dp * 0.6f
    val currentUser by authViewModel.currentUser.collectAsState()
    val drTheme by homeViewModel.darkTheme.collectAsState()

    //States for updating the user information.
    var showdialog by remember{mutableStateOf(false)}
    var updatedfullName by remember{ mutableStateOf(currentUser?.fullname ?: "") }
    var updatedEmail by remember{ mutableStateOf(currentUser?.email ?: "") }
    var updatedPassword by remember { mutableStateOf("") }
    var errormessage by remember { mutableStateOf("Password required for updating") }

    // Fetch current user information
    LaunchedEffect(key1 = Unit) {
        authViewModel.fetchCurrentUser()
    }
    //Syncing the current user along with updated fullname and email.
    LaunchedEffect(currentUser) {
        authViewModel.fetchCurrentUser()
        updatedfullName = currentUser?.fullname ?: ""
        updatedEmail = currentUser?.email ?: ""
    }


    if(showdialog){
        AlertDialog(
            onDismissRequest = {
                showdialog = false
            },
            title = {
                Text(text = "Edit profile")
            },
            text = {
                Column {
                    TextField(
                        value = updatedfullName,
                        onValueChange ={updatedfullName=it}
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    TextField(
                        value = updatedEmail,
                        onValueChange ={updatedEmail = it}
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    TextField(
                        value = updatedPassword,
                        onValueChange ={updatedPassword = it},
                        label = { Text(text = errormessage)},
                    )
                }
            },
            dismissButton = {
                Button(onClick = { showdialog = false }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (updatedPassword.isNotEmpty()){
                        authViewModel.updateCurrentUser(updatedfullName, updatedEmail, updatedPassword)
                        showdialog = false
                    }else{
                        errormessage = "Please enter password"
                    }
                }) {
                    Text(text = "Update")
                }
            }
        )
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
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(width)
        )

        Spacer(modifier = Modifier.weight(0.5f))
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            onClick = { /*Updating the user profile*/ }
        ) {
            Text(
                text = "Add new/Update picture",
                fontFamily = quicksandFam,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            onClick = { showdialog = true }
        ) {
            Text(
                text = "Edit details",
                fontFamily = quicksandFam,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            shape = RoundedCornerShape(35.dp)
        ) {
            currentUser?.fullname?.let {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = it,
                    fontSize = 18.sp,
                    fontFamily = ubuntuFam,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            shape = RoundedCornerShape(35.dp)
        ) {
            currentUser?.email?.let { email ->
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    maxLines = 1,
                    text = email,
                    fontSize = 18.sp,
                    fontFamily = ubuntuFam,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))

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

        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            onClick = {
                homeViewModel.changeTheme()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Gray
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {

            if(drTheme){
                Text(
                    text = "Change to light Theme",
                    fontFamily = poppinsFam,
                    fontSize = 15.sp
                )
            }else{
                Text(
                    text = "Change to dark Theme",
                    fontFamily = poppinsFam,
                    fontSize = 15.sp
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
