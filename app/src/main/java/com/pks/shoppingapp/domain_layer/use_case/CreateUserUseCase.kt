//package com.pks.shoppingapp.domain_layer.use_case
//
//import com.pks.shoppingapp.common.ResultState
//import com.pks.shoppingapp.domain_layer.model.UserData
//import com.pks.shoppingapp.domain_layer.repo.ShoppingRepo
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class CreateUserUseCase @Inject constructor(private val repo:ShoppingRepo) {
//    fun createUser(userData: UserData): Flow<ResultState<String>> {
//       return repo.registerUserWithEmailAndPassword(userData)
//    }
//
//    fun singIn(email:String,password:String):Flow<ResultState<String>>{
//        return repo.signInWithEmailAndPassword(email=email,password=password)
//    }
//}