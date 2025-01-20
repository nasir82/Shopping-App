package com.pks.shoppingapp.products.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.domain.model.ProductVariationsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(): ViewModel() {

    private val _productModel = MutableStateFlow(ProductModel())
    val productModel = _productModel.asStateFlow()
    private val _selectedImage = MutableStateFlow(_productModel.value.thumbnail)
    val selectedImage  =  _selectedImage.asStateFlow()
    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images = _images.asStateFlow()


    private val _selectedAttributes=MutableStateFlow<MutableMap<String, String>>(mutableMapOf())
    val newSl:MutableMap<String,String> = mutableMapOf()
    val selectedAttributes = _selectedAttributes.asStateFlow()
    private val _stockStatus = MutableStateFlow("")
    val stockStatus = _stockStatus.asStateFlow()

    private val _selectedVariation = MutableStateFlow(ProductVariationsModel())
    val selectedVariation = _selectedVariation.asStateFlow()


    /**
     * maintain an selected attribute list use selectedAttributes.values
     * and change/set the border color of attribute accordingly
     * if a variation is found this variation image change to selected image
     *
     */



    fun getAllImages(): List<String>{
        val productImages:MutableSet<String> = mutableSetOf()
        productImages.add(productModel.value.thumbnail)
        if(productModel.value.images.isEmpty()){
            productImages.addAll(productModel.value.images)
        }
        if(productModel.value.productVariation.isNotEmpty()){
            productModel.value.productVariation.forEach {
                productImages.add(it.image)
            }
        }


        return productImages.toList()
    }

    fun onImageSelect(imageLink:String){
        _selectedImage.value = imageLink
    }

    fun onSelectedAttribute(product:ProductModel, attributeName:String, attributeValue:String){
        Log.d("Set value", "Prev: ${selectedAttributes.value[attributeName]}")

        if(_selectedAttributes.value[attributeName]==attributeValue){
            _selectedAttributes.value.remove(attributeName)
            Log.d("Set value", "removed")
        }
//        _selectedAttributes.update {
//            it.toMutableMap().apply {
//                attributeName to attributeValue
//            }
//        }

        // Create a new map with the updated value
        val updatedAttributes = _selectedAttributes.value.toMutableMap().apply {
            this[attributeName] = attributeValue
        }

        // Assign the new map to trigger state change
        _selectedAttributes.value = updatedAttributes

        Log.d("Set value", "Updated: ${selectedAttributes.value[attributeName]}")
        val selectedVariation = product.productVariation.firstOrNull() {
            variation->
            isSameAttribute(selectedAttributes.value, variation.attributeValues)
        }

        if(selectedVariation != null){
            _selectedImage.value  = selectedVariation.image
            this._selectedVariation.value = selectedVariation
            getProductVariationStockStatus()
        }else{
            if(selectedAttributes.value.size == product.productVariation.size)
            _stockStatus.value = "out of stock"
        }


    }

    fun isSameAttribute( selectedAttribute:Map<String,String>,varAttribute:Map<String,String>):Boolean{
        if (selectedAttribute.size != varAttribute.size) return false
        varAttribute.keys.forEach {
            key->
            if(varAttribute[key] != selectedAttribute[key]) return false
        }
        return  true
    }

    fun getProductVariationStockStatus(){
        _stockStatus.value = if( selectedVariation.value.stock > 0) "in stock" else "out of stock"
    }
    fun getVariationPrice():String {
        return if(selectedVariation.value.salePrice > 0)
         selectedVariation.value.salePrice.toString()
        else  selectedVariation.value.price
        .toString();
    }

    fun resetSelectedAttribute() {
        selectedAttributes.value.clear()
        _stockStatus.value = if(productModel.value.stock>0) "In stock" else "out of stock"
        _selectedVariation.value = ProductVariationsModel()
    }
    fun setSelectedImage(image:String){
        _selectedImage.value = image
    }
    fun setProduct(product: ProductModel){
        _productModel.value = product
        _selectedImage.value = product.thumbnail
    }
}