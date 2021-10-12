package com.example.post_request_app_revisited



import com.google.gson.annotations.SerializedName


class Users {

    var data: List<UserDetails>? = null

    class UserDetails {
        @SerializedName("user_Id")
        var user_Id: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null

        constructor(name: String?, location: String?,user_Id:String?) {
            this.name = name
            this.location = location
            this.user_Id= user_Id
        }
    }
}