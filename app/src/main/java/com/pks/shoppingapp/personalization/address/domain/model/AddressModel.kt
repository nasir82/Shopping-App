package com.pks.shoppingapp.personalization.address.domain.model

data class AddressModel(
    val name:String="",
    val phoneNumber:String="",
    val street:String="",
    val postalCode:String="",
    val city:String="",
    val state:String="",
    val country:String=""
){

    override fun toString(): String {
        return "$street-$postalCode, $city, $state, $country"
    }
}
