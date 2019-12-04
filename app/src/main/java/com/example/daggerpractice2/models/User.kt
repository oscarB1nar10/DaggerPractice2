package com.example.daggerpractice2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
     var id: Int? = null
    @SerializedName("username")
    @Expose
     var userName: String? = null
    @SerializedName("email")
    @Expose
     var email: String? = null
    @SerializedName("website")
    @Expose
     var webSite: String? = null
}