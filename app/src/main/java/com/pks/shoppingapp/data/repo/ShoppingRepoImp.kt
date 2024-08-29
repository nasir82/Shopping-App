package com.pks.shoppingapp.data.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.domain_layer.model.UserData
import com.pks.shoppingapp.domain_layer.repo.ShoppingRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ShoppingRepoImp @Inject constructor(
    var auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : ShoppingRepo {
    override fun registerUserWithEmailAndPassword(
        userData: UserData
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        auth.createUserWithEmailAndPassword(userData.email!!, userData.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser!!.uid
                    firestore.collection("Users").document(uid).set(userData)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                trySend(ResultState.Success(data = "User Created"))
                            } else {
                                trySend(ResultState.Error(message = it.exception!!.message.toString()))
                            }
                        }

                } else {
//                Log.d("error",it.exception!!.message.toString())
                    trySend(ResultState.Error(message = task.exception!!.message.toString()))
                }
            }
        awaitClose {
            close()
        }
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                trySend(ResultState.Success(data = "User Login"))
            } else {
                Log.d("error", it.exception!!.message.toString())
                trySend(ResultState.Error(message = it.exception!!.message.toString()))
            }
        }
        awaitClose {
            close()
        }
    }

    override fun getUserDataByUid(id: String): Flow<ResultState<UserData>> = callbackFlow {
        trySend(ResultState.Loading)
        firestore.collection("Users").document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val data = it.result.toObject(UserData::class.java)
                if (data != null) {

                    trySend(ResultState.Success(data))
                }
            } else {
                trySend(ResultState.Error(message = it.exception?.localizedMessage.toString()))
            }
        }
        awaitClose {
            close()
        }
    }


}