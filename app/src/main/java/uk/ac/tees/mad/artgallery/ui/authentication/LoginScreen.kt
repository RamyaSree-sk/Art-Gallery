package uk.ac.tees.mad.artgallery.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.firebaseauth.state.AuthState
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.theme.color1
import uk.ac.tees.mad.artgallery.ui.theme.color2
import uk.ac.tees.mad.artgallery.ui.theme.color3
import uk.ac.tees.mad.artgallery.ui.theme.lightPurple


@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
){

    val authState by authViewModel.authState.collectAsState()

    var errorMessage by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }


    when(authState){

        is AuthState.Loading->{

        }

        is AuthState.Success->{
            navController.navigate("on_boarding")
        }

        is AuthState.Failure->{
            errorMessage = (authState as AuthState.Failure).message
        }
        else->{}
    }


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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.6f))
            Image(
                painter = painterResource(id = R.drawable.newicon),
                contentDescription = "Image"
            )

            Spacer(modifier = Modifier.size(20.dp))

            Card (
                modifier = Modifier.padding(5.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ){
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                0.36f to Color(0xFFb188d6),
                                1.0f to Color(0xFFd490db)
                            )
                        )
                ){
                    Column (
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            leadingIcon = { Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "emailIcon"
                            )},
                            label = { Text(text = "email")},
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )

                        Spacer(modifier = Modifier.size(15.dp))
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            visualTransformation = if(passwordVisibility)VisualTransformation.None else PasswordVisualTransformation(),
                            leadingIcon = { Icon(
                                imageVector = Icons.Filled.Key,
                                contentDescription = "Password icon"
                            )},
                            trailingIcon = {
                                val showPassword = if (passwordVisibility)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff

                                IconButton(onClick = { passwordVisibility = !passwordVisibility}) {
                                    Icon(
                                        imageVector = showPassword,
                                        contentDescription = "Show Password")
                                }
                            },
                            label={
                                Text(text = "Password")
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange ={
                                    isChecked = it
                                }
                            )
                            Text(
                                text = "Remember me?",
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .clickable {
                                        isChecked = !isChecked
                                    }
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "Forgot Password?",
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .clickable {

                                    }
                            )
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Not have account?"
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = "Create one here!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .clickable {
                                        navController.popBackStack()
                                        navController.navigate("signup_screen")
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.size((20.dp)))
                        Button(onClick = {
                            authViewModel.login(email, password)
                        }) {
                            Text(text = "Login",
                                fontSize = 15.sp)
                        }
                    }
                }
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Unable to Login $errorMessage",
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}