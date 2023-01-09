package com.example.movcomplmac

class BEntrenador(
    var id:Int,
    var nombre:String,
    var email:String
    ) {
    override fun toString(): String {
        return "${nombre} - ${email}"
    }


}