package com.tjoeun.serverapp_daily10minute_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tjoeun.serverapp_daily10minute_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailEdt
import kotlinx.android.synthetic.main.activity_main.pwEdt
import kotlinx.android.synthetic.main.activity_main.signUpBtn
import kotlinx.android.synthetic.main.activity_sing_up.*
import org.json.JSONObject

class SingUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        
        checkEmailBtn.setOnClickListener { 
            
//             입력한 이메일 => 서버에 중복 여부 확인
            
            val inputEmail = emailEdt.text.toString()
            
//            서버에 -> /email_check 로 중복 확인 요청 -> ServerUtil 함수 추가 필요

            ServerUtil.GetRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }


            })

        }

        signUpBtn.setOnClickListener {
//            이메일/비번/닉네임 => 서버 회원 가입 기능에 전송

            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()
            val nick = nickNameEdt.text.toString()
            
//             서버 - 회원가입 기능에 전송 : serverUtil에 회원가입 함수 필요
            
            ServerUtil.putRequestSingUP(email, pw, nick, object :ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    
//                    회원 가입 결과에 따른 UI 반영 필요
//                    code : 200이면 가입 성공 -> 토스트 + 회원가입 화면 종료
//                    그외 값 : 서버가 주는 실패 사유를 -> 토스트

                    val code = json.getInt("code")

                    runOnUiThread {
                        if(code == 200){
//                            가입한 사람의 이름을 추출해서, 환영 메세지
//                            json > data { } > user { } > "nick_name" String 추출

                            val dataObj = json.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val userName = userObj.getString("nick_name")

                            Toast.makeText(mContext, "${userName}님 환영합니다.", Toast.LENGTH_SHORT).show()

                            finish()
                        }
                        else {
//                            서버가 주는 message 스트링을 토스트로 출려
                            val message = json.getString("message")

                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }

                    }

                }


            })

        }
         
    }

    override fun setValues() {

    }
}