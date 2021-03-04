package com.snowdango.yumemicodetest.view.contributors

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.snowdango.yumemicodetest.R
import com.snowdango.yumemicodetest.view.userinfo.UserInfoActivity
import com.snowdango.yumemicodetest.viewmodel.contributors.ContributorListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class ContributorListActivity : AppCompatActivity() {

    private val viewModel: ContributorListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)

        val contributorEpoxyController = ContributorsEpoxyController(object: ContributorsEpoxyController.ClickAction{
            override fun onClick(login: String) {
                val intent = Intent(this@ContributorListActivity,UserInfoActivity::class.java)
                intent.putExtra("USER_LOGIN",login)
                startActivity(intent)
            }
        })
        findViewById<EpoxyRecyclerView>(R.id.epoxyContributors).also {
            it.adapter = contributorEpoxyController.adapter
            it.layoutManager = LinearLayoutManager(applicationContext).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
        contributorEpoxyController.setFilterDuplicates(true)
        showContributorsWithPermissionCheck(contributorEpoxyController)
    }

    @NeedsPermission(Manifest.permission.INTERNET)
    fun showContributors(contributorsEpoxyController: ContributorsEpoxyController){
        val observer = viewModel.orderContributorList()
        observer.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                contributorsEpoxyController.setData(it,true)
            },{
                Toast.makeText(applicationContext,"api failed re try!",Toast.LENGTH_LONG).show()
            },{})
    }
}