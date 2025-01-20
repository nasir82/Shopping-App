package com.pks.shoppingapp.home.domain.model


data class CategoryModel(
    var id:String = "",
    var image:String = "",
    var name:String = "",
    var parentId:String = "",
    var isFeatured:Boolean = false,
)