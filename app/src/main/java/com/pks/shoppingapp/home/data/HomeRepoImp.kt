package com.pks.shoppingapp.home.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.domain.model.CategoryModel
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.domain.repo.HomeRepo
import com.pks.shoppingapp.home.presentation.CategoryState
import com.pks.shoppingapp.home.presentation.ProductState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val db:FirebaseFirestore):HomeRepo {
    override suspend fun getCategories(): Flow<ResultState<CategoryState>> = callbackFlow {

        Log.d("inside category collector", "collectng category")
        trySend(ResultState.Loading)

        db.collection("Category").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(CategoryModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(CategoryState(categories = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun getProducts(): Flow<ResultState<ProductState>> = callbackFlow {
        trySend(ResultState.Loading)

        db.collection("Products").get().addOnSuccessListener {
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
}