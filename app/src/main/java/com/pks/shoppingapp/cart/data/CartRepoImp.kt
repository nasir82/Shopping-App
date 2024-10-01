package com.pks.shoppingapp.cart.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.cart.domain.repo.CartRepo
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CartRepoImp @Inject constructor(private val db: FirebaseFirestore, private val auth: FirebaseAuth):
    CartRepo {
    override suspend fun getCarts(): Flow<ResultState<ProductState>> = callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser!!.uid
        db.collection("Carts").document(uid).collection("Cart").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(ProductModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(ProductState(products = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun addToCart(productModel: ProductModel): Flow<ResultState<WishListUploadState>> =
        callbackFlow {
        trySend(ResultState.Loading)
        db.collection("Carts").document(auth.currentUser!!.uid).collection("Cart").document(productModel.id).set(productModel).addOnSuccessListener {
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