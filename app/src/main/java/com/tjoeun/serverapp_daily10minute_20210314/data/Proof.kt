package com.tjoeun.serverapp_daily10minute_20210314.data

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Proof {

    var id = 0
    var content = ""

//    인증글의 하위정보로 > 누가 작성했는지 User를 담아두자
    lateinit var writter : User

//    이 인증길이 언제 적혔는지? Calendar 형태로 보관하자
//    나중에 상황에따라 SimpleDataFormat으로 가공해서 사용하기 위함
    val proofDateTime = Calendar.getInstance()


    companion object {

        fun getProofFromJson(jsonObj : JSONObject) : Proof{

            val proof = Proof()

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

//            게시글의 작성자 ?
//            User 형태 => 적당한 JSON을 넣으면 => User로 바꿔주는 기능
//            만들어 둔걸 활용하자

//            User 정보를 갖고있는 JSON 추출

            val userObj = jsonObj.getJSONObject("user")

            proof.writter = User.getUserDataFromJson(userObj)

//            이글이 언제 적혔는지? 서버가 주는 정보를 반영
//            서버가 주는 String => (SimpleDateFormat) 분석 => Calendar로 변환

            val proofTimeStr = jsonObj.getString("proof_time")

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            proof.proofDateTime.time = sdf.parse(proofTimeStr)


            return proof

        }

    }

}