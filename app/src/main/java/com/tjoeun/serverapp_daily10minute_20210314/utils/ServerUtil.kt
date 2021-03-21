package com.tjoeun.serverapp_daily10minute_20210314.utils

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    API를 호출해주는 함수들을 모아두기 위한 클래스. (코드 정리 차원)

//    화면(액티비티의)입장에서, 서버에 다녀오면 할 행동을 적는 행동 지침
//    행동 지침을 전달하는 방식 : Interface

    interface JsonResponseHandler {
        fun onResponse(json : JSONObject)

    }

//     ServerUtil.함수() 처럼, 클래스 이름. 만 해도 바로 사용하게 도와주는 코드
//    JAVA - static개념에 대응되는 코드

    companion object {

        //     호스트 주소를 편하게 입력/관리하기 위한 변수.
        val HOST_URL = "http://15.164.153.174"

//        함수 작성 - 로그인 기능 담당 함수

        fun postRequestLogin(id : String, pw : String, handler: JsonResponseHandler? ) {

//            실제 기능 수행 주소 ex. 로그인 - http://15.164.153.174/user
//            HOST_ULR/user => 최종 주소 완성

            val urlString = "${HOST_URL}/user"

//            POST 메쏘드 - formBody에 데이터 첨부
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            API 요청(Request)을 어디로 어떻게 할건지 종합하는 변수
            val request = Request.Builder()
                .url(urlString) // 어디로 가는지 ?
                .post(formData) // POST 방식 - 필요데이터(formData) 들고 가도록
                .build()

//            startActivity 처러 -> 실제로 Request를 수행하는 코드
//            클라이언트로써 동작하도록 도와주는 라이브러리 : OkHttp

            val client = OkHttpClient()

//            클라이언트가 실제 리퀘스트 수행.(newCall)
//            서버에 다녀와서, 서버가 하는말 (응답-Response/CallBack)을 처리하는 코드도 같이 작성

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체를 실패
//                    데이터 소진, 서버가 터짐 등등의 사유로 아예 연결 실패

//                    반대 - 로그인 비번 틀림(실패), 회원가입(이메일중복 실패) => 연결은 성공, 결과만 실패
//                    여기서 실행되지않는다.

                }

                override fun onResponse(call: Call, response: Response) {
//                    서버의 응답을 받아낸 경우
//                    응답(Response) > 내부의 본문(Body)만 활용 > String 형태로 저장

//                    toString() X, string() 활용
                    val bodyString = response.body!!.string()

//                    bodyString은, 인코딩 된 상태라 읽기가 어렵다 (한글깨짐)
//                    bodyString을 > JSONObject으로 변환 시키면 > 읽을수 있게됨

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버 응답 본문", jsonObj.toString())

//                    실제 : 응답 처리 코드는 => 화면에서 작성하도록 미루자
//                    화면에 적힌 행동 방침을 그대로 실행

                    handler?.onResponse(jsonObj)
                }


            })

        }

//        회원 가입 기능 담당 함수

        fun putRequestSingUP(email : String, pw : String, nickname : String, handler: JsonResponseHandler?){

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답내용", jsonObj.toString())

                    handler?.onResponse((jsonObj))

                }

            })

        }

//        이메일 중복여부 체크 함수

        fun GetRequestEmailCheck(email : String, handler: JsonResponseHandler?){

//            어디로? + 어떤 데이터 ? => URL 적을때 같이 완성되어야한다.

//            주소가 복잡해짐. => 복잡한 가공을 도와주는 클래스 활용 => URLBuilder

//            http://15~~/email_check 의 뒤에, 파라미터를 쉽게 첨부하도록 도와주는 변수
            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

//            필요한 파라미터를 url에 붙이자
            urlBuilder.addEncodedQueryParameter("email", email)

//            필요한 파라미터가 다 붙었으면, 최종 형태 String으로 완성.

            val urlString = urlBuilder.build().toString()

//            요청 정보 종합

            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()

//            실제 호출 client 변수

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)

                }


            })

        }

//        프로젝트 목록 받아오는 함수
        fun GetRequestProjectList( handler: JsonResponseHandler?){

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

            val urlString = urlBuilder.build().toString()


            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())
                    handler?.onResponse(jsonObj)

                }


            })

        }

    }

}