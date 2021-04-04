package com.tjoeun.serverapp_daily10minute_20210314

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_view_proof.*
import java.util.*

class ViewProofActivity : BaseActivity() {

//    인증 확인하려는 날짜를 Calendar 형태로 저장해주자
    val mProofDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        날짜 선택 버튼이 눌리면 => 달력을 띄우자

        selectDataBtn.setOnClickListener {

//            1. 날짜가 선택이 되면 => 어떤 행동을 할지 가이드북 변수

            val dateSetListner = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    연 / 월 / 일을 가지고 처리할 코드 작성


                }


            }

//            2. 그 가이드 북을 들고, 실제로 날짜를 선택하러 가기. (날짜 선택용 팝업창 띄우기)

            val datePickerDialog = DatePickerDialog(mContext, dateSetListner,
                2021, Calendar.APRIL, 4)

            datePickerDialog.show()

        }

    }

    override fun setValues() {

//        mProofDate에는 기본적으로 현재 일시가 기록되어있다.
//        인증 확인 날짜에 현재 일자를 반영해보자

//        Calendar 변수의 get 기능 활용 예시
        dateTxt.text = "${mProofDate.get(Calendar.YEAR)}-${mProofDate.get(Calendar.MONTH)}-${mProofDate.get(Calendar.DAY_OF_MONTH)}"

    }
}