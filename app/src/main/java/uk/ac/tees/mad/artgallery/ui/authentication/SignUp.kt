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
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.rpc.context.AttributeContext.Auth
import uk.ac.tees.mad.artgallery.R
import uk.ac.tees.mad.artgallery.firebaseauth.state.AuthState
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.theme.color1
import uk.ac.tees.mad.artgallery.ui.theme.color2
import uk.ac.tees.mad.artgallery.ui.theme.color3
import uk.ac.tees.mad.artgallery.ui.theme.lightPurple


@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel
){

    val authState by authViewModel.authState.collectAsState()

    var errorMessageSignUp by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    when(authState){
        is AuthState.Success->{
            navController.navigate("home_graph") {
                popUpTo("auth_graph") { inclusive = true }
            }
        }

        is AuthState.Failure->{
            errorMessageSignUp = (authState as AuthState.Failure).message
        }

        is AuthState.Loading->{

        }

        else -> {}

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
                ) {

                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            value = fullName,
                            onValueChange = {
                                fullName = it
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person2,
                                    contentDescription = "fullname"
                                )
                            },
                            label = { Text(text = "Full Name") },
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = lightPurple,
                                focusedLabelColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.size(15.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = "emailIcon"
                                )
                            },
                            label = { Text(text = "Email") },
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = lightPurple,
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
                            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Key,
                                    contentDescription = "Password icon"
                                )
                            },
                            trailingIcon = {
                                val showPassword = if (passwordVisibility)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff

                                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                    Icon(
                                        imageVector = showPassword,
                                        contentDescription = "Show Password"
                                    )
                                }
                            },
                            label = {
                                Text(text = "Password")
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Unspecified,
                                focusedContainerColor = lightPurple,
                                focusedLabelColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black
                            )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Already Registered?"
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Login here!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate("login_screen"){
                                            popUpTo("signup_screen"){inclusive=true}
                                        }
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.size((20.dp)))
                        Button(onClick = {
                            authViewModel.signUp(fullName, email, password)
                        }) {
                            Text(
                                text = "SignUp",
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            if (errorMessageSignUp.isNotEmpty()) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Unable to Sign up $errorMessageSignUp",
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}