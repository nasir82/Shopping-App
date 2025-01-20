package com.pks.shoppingapp.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.domain.use_case.GetCategoriesUseCase
import com.pks.shoppingapp.home.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase, private val getCategoriesUseCase: GetCategoriesUseCase):ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()
    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    companion object{
        private val _productStateCopy = MutableStateFlow(ProductState())
        val productStateCopy = _productStateCopy.asStateFlow()

        private val _favoriteProductState = MutableStateFlow(ProductState())
        val favoriteProductState = _favoriteProductState.asStateFlow()

        fun fetchFavouriteProductState(favorites:Map<String,Boolean>){
            try {
                val products = productStateCopy.value.products
                _favoriteProductState.value = ProductState(isLoading = true)
                Log.d("Favourite Product no: ", favoriteProductState.value.toString())


                var favProducts = mutableListOf<ProductModel>()
                var hereList = products.filter {
                    favorites[it.id]==true
                }

                favProducts.addAll(hereList)

                _favoriteProductState.update {currentState->
                    currentState.copy(
                        isLoading = false,
                        products = favProducts,
                        error = ""
                    )

                }

                Log.d("Favourite Product no: ", favoriteProductState.value.products.size.toString())
                Log.d("Favourite Product no: ", favoriteProductState.value.toString())

            }catch (e:Exception){
                _favoriteProductState.value = ProductState(error = "Can't fetch favourite products")
            }
        }

    }

    init {
        getProduct()
        getCategories()
    }

    private fun getCategories() {
        _categoryState.value = CategoryState(isLoading = true)
        Log.d("inside category collector", "collectng category viewmodel")

        viewModelScope.launch {
            getCategoriesUseCase.getCategories().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _categoryState.value = CategoryState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _categoryState.value = CategoryState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        Log.d(
                            "inside category collector",
                            "collectng category success ${it.data.categories}"
                        )
                        _categoryState.value = CategoryState(categories = it.data.categories)
//                        Log.d(
//                            "inside category collector",
//                            "collectng category origin ${categoryState2.value.c}"
//                        )
                    }
                }
            }
        }
    }

    fun getProduct() {
        viewModelScope.launch {
            getProductsUseCase.getProducts().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _productState.value = ProductState(error = it.message)
                        _productStateCopy.value = ProductState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _productState.value = ProductState(isLoading = true)
                        _productStateCopy.value = ProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _productState.value = ProductState(products = it.data.products)
                        _productStateCopy.value = ProductState(products = it.data.products)
                    }
                }
            }
        }
    }

}