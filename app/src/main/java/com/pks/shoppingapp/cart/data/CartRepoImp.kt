package com.pks.shoppingapp.cart.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.cart.domain.repo.CartRepo
import com.pks.shoppingapp.cart.presentation.CartState
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CartRepoImp @Inject constructor(private val db: FirebaseFirestore, private val auth: FirebaseAuth):
    CartRepo {
    override suspend fun getCarts(): Flow<ResultState<CartState>> = callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser?.uid
        db.collection("Carts").document(uid?: "default").collection("Cart").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(CartModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(CartState(products = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun addToCart(productModel: CartModel): Flow<ResultState<WishListUploadState>> =
        callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Carts").document(auth.currentUser!!.uid).collection("Cart").document(productModel.id).set(productModel).addOnSuccessListener {
            Log.d("isAvailable",productModel.toString())
            trySend(ResultState.Success(WishListUploadState(isLoaded = true )))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            close()
        }

    }
    override suspend fun updateCart(cartModel: CartModel): Flow<ResultState<WishListUploadState>> =
        callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Carts").document(auth.currentUser!!.uid).collection("Cart").document(cartModel.id).update(
            mapOf(
            "quantity" to cartModel.quantity
            )).addOnSuccessListener {
            Log.d("isAvailable in repo",cartModel.toString())
            trySend(ResultState.Success(WishListUploadState(isLoaded = true )))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            close()
        }

    }
    override suspend fun removeCart(id: String): Flow<ResultState<WishListUploadState>> =
        callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Carts").document(auth.currentUser!!.uid).collection("Cart").document(id).delete(
           ).addOnSuccessListener {
            Log.d("isAvailable in repo",id.toString())
            trySend(ResultState.Success(WishListUploadState(isLoaded = true )))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            close()
        }

    }

}