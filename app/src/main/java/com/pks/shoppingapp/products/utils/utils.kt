package com.pks.shoppingapp.products.utils

fun stockStatus(stock:Int):String{
    return  if(stock>0) "Available" else "Out of stock"
}
