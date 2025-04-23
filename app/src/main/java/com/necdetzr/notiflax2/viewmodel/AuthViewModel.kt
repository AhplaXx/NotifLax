package com.necdetzr.notiflax2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.necdetzr.notiflax2.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository):ViewModel() {
    //Getting user as a private value because of encapsulation
    private val _user = MutableStateFlow<FirebaseUser?>(null)
    //Getting user as a public value because of sending to UI
    val user: MutableStateFlow<FirebaseUser?> = _user

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn : MutableStateFlow<Boolean> = _isLoggedIn
    //Login Function for make functional login button on UI.
    //!RETURNS ON_RESULT TO SEND RESULT TO UI
    fun login(email: String, password: String,onResult: (Result<String>)->Unit) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
            result.onSuccess {
                _user.value = it
                onResult(Result.success("Success"))
            }
            result.onFailure {
                _user.value = null
                onResult(Result.failure(it))
            }
        }

    }
    init {
        checkUserLoginStatus()
    }

    private fun checkUserLoginStatus(){
        _isLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
    }
    //Register Function for make functional login button on UI.
    //!RETURNS ON_RESULT TO SEND RESULT TO UI
    fun register(email: String, password: String,onResult: (Result<String>)->Unit) {
        viewModelScope.launch {
            //Result as success or failure
            val result = authRepository.register(email, password)
            result.onSuccess {
                _user.value = it
                onResult(Result.success("Success"))
            }
            result.onFailure {

                _user.value = null
                onResult(Result.failure(it))
            }
        }

    }
    fun logOut(){
        authRepository.logOut()
        _user.value = null

    }
}