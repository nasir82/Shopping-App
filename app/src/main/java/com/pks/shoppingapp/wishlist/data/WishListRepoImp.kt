package com.pks.shoppingapp.wishlist.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.domain.repo.WishListRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class WishListRepoImp @Inject constructor(private val db:FirebaseFirestore,private val auth:FirebaseAuth):WishListRepo {
    override suspend fun wishList(): Flow<ResultState<ProductState>>  = callbackFlow {
        trySend(ResultState.Loading)
        val uid = auth.currentUser!!.uid
        db.collection("Wishlist").document(uid).collection("Collection").get().addOnSuccessListener {
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

    override suspend fun addToWishList(productModel: ProductModel): Flow<ResultState<WishListUploadState>> =callbackFlow {
        Log.d("upload","Entry taken place in function")
        trySend(ResultState.Loading)
        db.collection("Wishlist").document(auth.currentUser!!.uid).collection("Collection").document(productModel.id).set(productModel).addOnSuccessListener {
            trySend(ResultState.Success(WishListUploadState(isLoaded = true )))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            Log.d("uploaded","Closed")
            close()
        }

    }

}

data class WishListUploadState(
    val isLoading:Boolean = false,
    val isLoaded:Boolean = false,
    val error:String = ""
)