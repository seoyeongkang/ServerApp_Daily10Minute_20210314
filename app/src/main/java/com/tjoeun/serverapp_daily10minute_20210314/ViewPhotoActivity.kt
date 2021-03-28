package com.tjoeun.serverapp_daily10minute_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minute_20210314.adapters.PhotoViewPagerAdaper
import com.tjoeun.serverapp_daily10minute_20210314.data.User
import kotlinx.android.synthetic.main.activity_view_photo.*

class ViewPhotoActivity : BaseActivity() {

    lateinit var mUser : User

    lateinit var mPhotoAdapter : PhotoViewPagerAdaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_photo)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mUser = intent.getSerializableExtra("user") as User

        mPhotoAdapter = PhotoViewPagerAdaper(supportFragmentManager, mUser.profileImgUrls)
        profilePhotoViewPager.adapter = mPhotoAdapter

    }
}