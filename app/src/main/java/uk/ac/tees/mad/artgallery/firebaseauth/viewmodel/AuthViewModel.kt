package uk.ac.tees.mad.artgallery.firebaseauth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.artgallery.firebaseauth.model.User
import uk.ac.tees.mad.artgallery.firebaseauth.state.AuthState

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
            fetchCurrentUser(loggedInUser.email!!)
        }else{
            _isLoggedIn.value = false
            _authState.value = AuthState.Idle
        }
        _showSplash.value = false
    }


    fun signUp(fullname: String, email: String, password: String){
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {res->
                    if(res.isSuccessful){
                        val user = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(fullname)
                            .build()

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener{
                            if(it.isSuccessful){
                                val userData = hashMapOf(
                                    "fullname" to fullname,
                                    "email" to email
                                )
                                val userId = user.uid
                                firestore.collection("users")
                                    .document(userId)
                                    .set(userData)
                                    .addOnSuccessListener {
                                        _authState.value = AuthState.Success
                                    }
                                    .addOnFailureListener { error->
                                        _authState.value = AuthState.Failure(error.message?: "User not Saved")
                                    }
                            }else{
                                _authState.value = AuthState.Failure("Profile Update Failed")
                            }
                        }
                    }else{
                        _authState.value = AuthState.Failure(res.exception?.message?:"Error in Signing Up")
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

    private fun fetchCurrentUser(email: String){
        firestore.collection("users")
            .document(email).get()
            .addOnSuccessListener { obUser->
                if(obUser.exists()){
                    val fullname = obUser.getString("fullname") ?:""
                    val user = User(fullname, email)
                    _currentUser.value = user
                }

            }
            .addOnFailureListener {
                _authState.value = AuthState.Failure(it.message?:"Error getting the user")
            }
    }

    fun logout(){
        auth.signOut()
        _currentUser.value = null
        _authState.value = AuthState.Idle
    }

}