package com.snowdango.yumemicodetest.view.userinfo

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.snowdango.yumemicodetest.R
import com.snowdango.yumemicodetest.databinding.ActivityUserBinding
import com.snowdango.yumemicodetest.viewmodel.userinfo.UserInfoViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class UserInfoActivity: AppCompatActivity() {

    private val viewModel: UserInfoViewModel by viewModel()
    private val binding: ActivityUserBinding by lazy { DataBindingUtil.setContentView(this,R.layout.activity_user) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val login = intent.getStringExtra("USER_LOGIN")

        val userInfoEpoxyController = UserInfoEpoxyController()
        binding.epoxyUserInfo.also {
            it.adapter = userInfoEpoxyController.adapter
            it.layoutManager = GridLayoutManager(applicationContext,4).apply {
                orientation = GridLayoutManager.VERTICAL
                spanSizeLookup = userInfoEpoxyController.spanSizeLookup
            }
        }
        userInfoEpoxyController.setFilterDuplicates(true)
        showUserInfoWithPermissionCheck(login,userInfoEpoxyController)
    }

    @NeedsPermission(Manifest.permission.INTERNET)
    fun showUserInfo(login: String?,userInfoEpoxyController: UserInfoEpoxyController){
       login?.let { loginString ->
           val observable  = viewModel.orderUserInfo(loginString)
           observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(
               {
                   userInfoEpoxyController.setData(it)
                   binding.userInfo = it
                   if (it.name == null || it.name == "") {
                       binding.name = it.login
                   } else {
                       binding.name = it.name
                   }
               },{
                   Toast.makeText(this,"miss user data",Toast.LENGTH_LONG).show()
               },{})
        }
    }
}