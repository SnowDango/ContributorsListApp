package com.snowdango.yumemicodetest.view.userinfo

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import com.snowdango.yumemicodetest.epoxyUserListItem

class UserInfoEpoxyController: TypedEpoxyController<UserInfoResponse>() {

    override fun buildModels(data: UserInfoResponse?) {
        if(data != null){
            Log.d("SSSAAA","data input $data")
            data.twitterUsername?.let {
                epoxyUserListItem {
                    id("twitterTag")
                    text("twitter:")
                }
                epoxyUserListItem {
                    id("twitter")
                    text(it)
                    spanSizeOverride { _, _, _ -> 3 }
                }
            }
            data.email?.let {
                epoxyUserListItem {
                    id("emailTag")
                    text("email:")
                }
                epoxyUserListItem {
                    id("email")
                    text(it)
                    spanSizeOverride { _, _, _ -> 3 }
                }
            }
            data.company?.let {
                epoxyUserListItem {
                    id("companyTag")
                    text("company:")
                }
                epoxyUserListItem {
                    id("company")
                    text(it)
                    spanSizeOverride { _, _, _ -> 3 }
                }
            }
            if(data.blog != ""){
                epoxyUserListItem {
                    id("blogTag")
                    text("blog:")
                }
                epoxyUserListItem {
                    id("blog")
                    text(data.blog)
                    spanSizeOverride { _, _, _ -> 3 }
                }
            }
            data.location?.let {
                epoxyUserListItem {
                    id("locationTag")
                    text("location:")
                }
                epoxyUserListItem {
                    id("location")
                    text(it)
                    spanSizeOverride { _, _, _ -> 3 }
                }
            }

            epoxyUserListItem {
                id("repTag")
                text("repository:")
            }
            epoxyUserListItem {
                id("rep")
                text(data.publicRepos.toString())
                spanSizeOverride { _, _, _ -> 3 }
            }

            epoxyUserListItem {
                id("gistTag")
                text("gists:")
            }
            epoxyUserListItem {
                id("gist")
                text(data.publicGists.toString())
                spanSizeOverride { _, _, _ -> 3 }
            }
            //
            epoxyUserListItem {
                id("updateTag")
                text("update:")
            }
            epoxyUserListItem {
                id("update")
                text(data.updatedAt)
                spanSizeOverride { _, _, _ -> 3 }
            }
            epoxyUserListItem {
                id("createTag")
                text("create:")
            }
            epoxyUserListItem {
                id("create")
                text(data.createdAt)
                spanSizeOverride { _, _, _ -> 3 }
            }
        }else{
            epoxyUserListItem {
                id("no data")
                text("no data")
                spanSizeOverride { _, _, _ -> 4 }
            }
        }
    }
}