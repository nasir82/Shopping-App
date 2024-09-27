package com.pks.shoppingapp.authentication.domain.model

data class UserData(
    var name:String="",
    var email:String="",
    var address:String = "",
    var password:String="",
    var phone:String=""
)
