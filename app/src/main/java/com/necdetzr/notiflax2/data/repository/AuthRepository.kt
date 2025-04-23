package com.necdetzr.notiflax2.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    //Catching Firebase Auth
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    //Login function returning Result of Firebase User
    suspend fun login(email:String,password:String):Result<FirebaseUser?>{

        try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            return Result.success(result.user)

        }catch (e:Exception){
            return Result.failure(e)
        }
    }
    //Register function returning Result of Firebase User

    suspend fun register(email: String,password: String):Result<FirebaseUser?>{
        try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            return Result.success(result.user)
    }catch (e:Exception){
        return Result.failure(e)

        }
    }
    //Log Out function returning Unit
    fun logOut(){
        auth.signOut()

    }

}