package com.example.primera_view.login.validator

import com.example.primera_view.data.entities.LoginUser

class LoginValidator {

    fun checkLogin(name: String, password: String) : Boolean{
//        var ret = false
        val admin = LoginUser()
         return (admin.name == name && admin.pass == password)
//        return ret
    }
}