package com.pks.shoppingapp.personalization.orders.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel
import com.pks.shoppingapp.personalization.orders.domain.repo.OrderRepo
import com.pks.shoppingapp.personalization.orders.presentation.OrderState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class OrderRepoImp @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) :
    OrderRepo {
    override suspend fun getAOrders(): Flow<ResultState<OrderState>> = callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser?.uid
        db.collection("Users").document(uid ?: "default").collection("Orders").get()
            .addOnSuccessListener {
                val instances = it.map { obj ->
                    obj.toObject(OrderModel::class.java)
                }.toList()

                trySend(
                    ResultState.Success(OrderState(orders = instances))
                )
            }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message ?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun addOrder(order: OrderModel): Flow<ResultState<OrderState>> = callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Users").document(auth.currentUser!!.uid).collection("Orders").document()
            .set(order).addOnSuccessListener {
            trySend(ResultState.Success(OrderState(isLoading = false)))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose {
            close()
        }

    }
}