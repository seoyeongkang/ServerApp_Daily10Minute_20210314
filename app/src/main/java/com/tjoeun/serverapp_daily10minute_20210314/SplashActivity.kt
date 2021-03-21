package com.tjoeun.serverapp_daily10minute_20210314

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.tjoeun.serverapp_daily10minute_20210314.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }
    override fun setupEvents() {

    }

    override fun setValues() {

//        앱 실행후 3초가 지나면 => 무조건 메인으로 (예정)

//        앱 실행후 3초가지나면 => 토큰 있고, 자동고르인 OK => 메인화면. 하나라도 아니면 로그인화면

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

//            저장된 토큰 / 자동로그인 여부 확인

            val savedToken = ContextUtil.getToken(mContext)
            val isautoLogin = ContextUtil.getAutoLogin(mContext)

//            토큰이 있는가? 꺼낸 토큰이 빈칸이 아닌가?
//            자동로그인 하는가? Boolean값이 true 인가?
//            둘다 만족하면 => 로그인 처리

            if(savedToken != "" && isautoLogin) {

//                바로 메인화면으로 이동
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
            }
            else {
//                로그인 화면으로 이동
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
            }

            finish()

        }, 3000)

    }


}