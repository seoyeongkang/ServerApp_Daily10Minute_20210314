package com.tjoeun.serverapp_daily10minute_20210314

import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

//    다른 화면들에서도 커스텀 액션바에 달아둔 UI요소들을 사용해야할 경우가 있다.
//    멤버 변수로 UI 요소를 만들어 둬야, 물려받아서 사용 가능
//    만드는건 멤버변수 > 대입은 커스텀 액션바완성 이후에 가능(나중에가능)
    lateinit var backImg : ImageView

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

//    이 클랙스는 다른 모든 액티비티의 부모로 동작
//    이 클래스의 적히 변수/함수는 다른 모든 액티비티가 물려받게 된다.
//    모든 화면에 공통적인 기능을 만들때는 여기서 처리 하자.

//    액션바를 직접 그리기 위한 코드가 담긴 함수

    fun setCustomActionBar(){

//        1. 액션바가 어떤 모양으로 보이게 하고싶은지?
//          => 모양 : layout - xml 작성하자

//        2. 기본 액션바를 불러내서 => 커스텀 액션바로 교체 작업

//        기존에 달려있는 기본 액션바 불러내기(무조건 있다고 전제)
        val defaultActionBar = supportActionBar!!

//        기존 액션바의 모드 변경 => 커스텀 액션바 모드로 변경
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//        실제로 보여줄 커스텀 화면 지정 (my_custom_action_bar)
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

//        좌우 여백 제거해야 함.
//        여백을 없애려면 ?  => 액션바의 부모인 툴바 여백 제거(내부 공간 0으로 설정)
//        ToolBar : androidx 가 주는 걸로 선택
        var toolBar = defaultActionBar.customView.parent as Toolbar
        toolBar.setContentInsetsAbsolute(0,0)

//        뒤로 가기 그림을 찾아서 => 클릭되면 현재화면을 종료하는 이벤트 추가
        backImg = defaultActionBar.customView.findViewById(R.id.backImg)

        backImg.setOnClickListener {
            finish()
        }



    }

}