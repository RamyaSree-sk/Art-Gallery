package uk.ac.tees.mad.artgallery.firebaseauth.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.artgallery.firebaseauth.model.User
import uk.ac.tees.mad.artgallery.firebaseauth.state.AuthState
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class AuthViewModel: ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _showSplash = MutableStateFlow(true)
    val showSplash = _showSplash.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()


    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        checkIfLoggedIn()
    }


    private fun checkIfLoggedIn(){
        val loggedInUser = auth.currentUser
        if(loggedInUser!=null){
            _isLoggedIn.value = true
        }else{
            _isLoggedIn.value = false
            _authState.value = AuthState.Idle
        }
        _showSplash.value = false
    }


    fun signUp(fullname: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { res ->
                    if (res.isSuccessful) {
                        val user = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(fullname)
                            .build()

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                val userData = hashMapOf(
                                    "fullname" to fullname,
                                    "email" to email,
                                    "profileImageUrl" to null
                                )
                                val userId = user.uid
                                firestore.collection("users")
                                    .document(userId)
                                    .set(userData)
                                    .addOnSuccessListener {
                                        _authState.value = AuthState.Success
                                    }
                                    .addOnFailureListener { error ->
                                        _authState.value = AuthState.Failure(error.message ?: "User not Saved")
                                    }
                            } else {
                                _authState.value = AuthState.Failure("Profile Update Failed")
                            }
                        }
                    } else {
                        _authState.value = AuthState.Failure(res.exception?.message ?: "Error in Signing Up")
                    }
                }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            _authState.value=AuthState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{res->
                    if(res.isSuccessful){
                        _authState.value=AuthState.Success
                    }else{
                        _authState.value = AuthState.Failure(res.exception?.message ?: "Login Failed!!")
                    }
                }
        }
    }

    fun logout(){
        auth.signOut()
        _isLoggedIn.value = false
        _currentUser.value = null
        _authState.value = AuthState.Idle
    }

    fun fetchCurrentUser() {
        val user = auth.currentUser
        user?.let {
            val userId = user.uid
            firestore.collection("users")
                .document(userId).get()
                .addOnSuccessListener { obUser ->
                    if (obUser.exists()) {
                        val fullname = obUser.getString("fullname") ?: ""
                        val email = obUser.getString("email") ?: ""
                        val profileImageUrl = obUser.getString("profileImageUrl") ?: ""
                        val updatedUser = User(fullname, email, profileImageUrl)
                        _currentUser.value = updatedUser
                    }
                    else{
                        Log.i("Authentication: ", "Firebase authentication cached failed in the if else")
                    }
                }
                .addOnFailureListener {
                    _authState.value = AuthState.Failure(it.message ?: "Error getting the user")
                    Log.i("Authentication: ", "Firebase authentication cached Failure")
                }
        }
    }

    fun updateCurrentUser(fullname: String, email: String, password: String) {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            val userData = hashMapOf(
                "fullname" to fullname,
                "email" to email
            )
            val credential = EmailAuthProvider.getCredential(user.email!!, password)
            user.reauthenticate(credential)
                .addOnCompleteListener {
                    firestore.collection("users")
                        .document(userId)
                        .update(userData as Map<String, Any>)
                }
                .addOnSuccessListener {
                    user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(fullname).build())
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                user.updateEmail(email).addOnCompleteListener {
                                    fetchCurrentUser()
                                }
                            }
                        }
                }
                .addOnFailureListener {
                    _authState.value = AuthState.Failure("Failed to update user in Firestore")
                }
        } else {
            _authState.value = AuthState.Failure("User not logged in")
        }
    }

    fun uploadProfileImage(imageUri: Uri) {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val storage = Firebase.storage.reference
        val imageRef = storage.child("users/$currentUserUid/profile.jpg")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()

                    val userData = hashMapOf("profileImageUrl" to downloadUrl)
                    Firebase.firestore.collection("users")
                        .document(currentUserUid)
                        .update(userData as Map<String, Any>)
                        .addOnSuccessListener {
                            fetchCurrentUser()
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore Error", "Failed to update profile image in Firestore", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Storage Error", "Failed to upload profile image", e)
            }
    }

}