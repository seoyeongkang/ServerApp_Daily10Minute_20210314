package com.tjoeun.serverapp_daily10minute_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailEdt
import kotlinx.android.synthetic.main.activity_main.pwEdt
import kotlinx.android.synthetic.main.activity_main.signUpBtn
import kotlinx.android.synthetic.main.activity_sing_up.*

class SingUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {
//            이메일/비번/닉네임 => 서버 회원 가입 기능에 전송

            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()
            val nick = nickNameEdt.text.toString()
            
//             서버 - 회원가입 기능에 전송 : serverUtil에 회원가입 함수 필요

        }
         
    }

    override fun setValues() {

    }
}