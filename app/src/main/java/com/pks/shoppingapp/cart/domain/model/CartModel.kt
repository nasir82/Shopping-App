package com.pks.shoppingapp.cart.domain.model


/**
 * no need to keep the product details but need the product id
 * when the product is variable type your should pass the selected variation
 * then use the selected variation
 */
data class CartModel(
    val id:String="",
    val productId:String = "",
    val variationId:String = "",
    val image:String = "",
    val price:Double = 0.0,
    val title:String = "",
    val brandName:String = "",
    val selectedVariationAttributes:Map<String,Any> = mutableMapOf(),
    var quantity:Int = 1
)



