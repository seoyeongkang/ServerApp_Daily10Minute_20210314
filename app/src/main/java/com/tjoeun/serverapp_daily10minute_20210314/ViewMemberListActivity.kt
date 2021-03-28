package com.tjoeun.serverapp_daily10minute_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.serverapp_daily10minute_20210314.adapters.ProjectMemberAdapter
import com.tjoeun.serverapp_daily10minute_20210314.data.Project
import com.tjoeun.serverapp_daily10minute_20210314.data.User
import com.tjoeun.serverapp_daily10minute_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_member_list.*
import org.json.JSONObject

class ViewMemberListActivity : BaseActivity() {

    lateinit var mProject : Project

    val mMemberList = ArrayList<User>()

    lateinit var mMemberAdapter : ProjectMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_member_list)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

//        서버에서 참여인원이 누구누구 있는지 요청 => 파싱 => mMemberList 에 추가(add)
        ServerUtil.getRequestProjectMembers(mContext, mProject.id, object  : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val ongoingUsersArr = projectObj.getJSONArray("ongoing_users")

                for(index in 0 until ongoingUsersArr.length()){

                    val userObj = ongoingUsersArr.getJSONObject(index)
                    val userData = User.getUserDataFromJson(userObj)

                    mMemberList.add(userData)

                }

//                이 코드는 비동기로 돌아가는 영역
//                하단의 어댑터 연결 코드가 => 상단이 있는 멤버데이터 추가보다, 먼저 완료될수도 있다.
//                멤버 목록이 다 추가되면 => 어댑터 새로고침 시켜주자.

                runOnUiThread{
                    mMemberAdapter.notifyDataSetChanged()
                }
            }

        })

        mMemberAdapter = ProjectMemberAdapter(mContext, R.layout.member_list_item, mMemberList)
        memberListView.adapter = mMemberAdapter

    }

}