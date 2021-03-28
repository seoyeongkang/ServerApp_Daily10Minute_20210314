package com.tjoeun.serverapp_daily10minute_20210314.data

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickNAme =""

    val profileImgUrls = ArrayList<String>()

    companion object {

        fun getUserDataFromJson(jsonObj : JSONObject) : User {

            val userData = User()

//            userData의 변수들에 값 대입



            return userData

        }


    }

}