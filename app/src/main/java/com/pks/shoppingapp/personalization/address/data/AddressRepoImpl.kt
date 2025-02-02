package com.pks.shoppingapp.personalization.address.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.address.domain.model.AddressModel
import com.pks.shoppingapp.personalization.address.domain.repo.AddressRepo
import com.pks.shoppingapp.personalization.address.presentation.AddressState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AddressRepoImpl @Inject constructor(private val db: FirebaseFirestore, private val auth: FirebaseAuth):AddressRepo {
    override suspend fun getAddresses(): Flow<ResultState<AddressState>> = callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser?.uid
        db.collection("Users").document(uid?: "default").collection("Address").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(AddressModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(AddressState(userAddresses = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun addAddresses(address:AddressModel): Flow<ResultState<AddressState>> =   callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Users").document(auth.currentUser!!.uid).collection("Address").document().set(address).addOnSuccessListener {
            trySend(ResultState.Success(AddressState(isLoading = false )))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            close()
        }

    }
}