package com.necdetzr.notiflax2.services

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


//! DIDN'T USED BECAUSE OF FIREBASE AUTH, USING AUTH_REPOSITORY.
class AuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signUp(email: String, password: String): Result<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user?.uid ?: "User not found")
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun signIn(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user?.uid ?: "User not found")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}