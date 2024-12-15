package uk.ac.tees.mad.artgallery.ui.authentication

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
import androidx.compose.material.icons.outlined.Person2
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.artgallery.ui.theme.blueColor
import uk.ac.tees.mad.artgallery.ui.theme.lightPurple


@Preview
@Composable
fun SignUpScreen(){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Blue,
                        blueColor,
                        lightPurple

                    )
                )
            ),
        contentAlignment = Alignment.Center
    ){
        Card (
            modifier = Modifier.padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
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
                    value = fullName,
                    onValueChange = {
                        fullName = it
                    },
                    leadingIcon = { Icon(
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
                    leadingIcon = { Icon(
                        imageVector = Icons.Outlined.Person2,
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
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    leadingIcon = { Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "usernameIcon"
                    )
                    },
                    label = { Text(text = "Username") },
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
                    visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = { Icon(
                        imageVector = Icons.Filled.Key,
                        contentDescription = "Password icon"
                    )
                    },
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
                        unfocusedContainerColor = Color.Unspecified,
                        focusedContainerColor = lightPurple,
                        focusedLabelColor = Color.Black,
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
                    Text(
                        text = "Already Registered?"
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Login here!",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .clickable {

                            }
                    )
                }

                Spacer(modifier = Modifier.size((20.dp)))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "SignUp",
                        fontSize = 15.sp)
                }
            }
        }
    }
}