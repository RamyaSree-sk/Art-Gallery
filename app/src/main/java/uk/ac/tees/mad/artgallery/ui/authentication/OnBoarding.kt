package uk.ac.tees.mad.artgallery.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.theme.color1
import uk.ac.tees.mad.artgallery.ui.theme.color2
import uk.ac.tees.mad.artgallery.ui.theme.color3
import uk.ac.tees.mad.artgallery.ui.theme.neueFam
import uk.ac.tees.mad.artgallery.ui.theme.poppinsFam

@Composable
fun OnBoardingScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        color1,
                        color2,
                        color3

                    )
                )
            ),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(1f))
            Card(
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.newicon),
                    contentDescription ="App Icon"
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Art Gallery",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                fontFamily = neueFam,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Let Your Imagination Take Flight.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = poppinsFam,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                onClick = {
                    navController.popBackStack()
                    navController.navigate("login_screen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB043E0),
                    contentColor = Color.White
                )
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Explore Now!",
                        fontSize = 20.sp,
                        fontFamily = poppinsFam,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = ""
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}