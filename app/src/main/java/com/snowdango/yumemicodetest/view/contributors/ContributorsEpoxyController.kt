package com.snowdango.yumemicodetest.view.contributors

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import com.snowdango.yumemicodetest.epoxyContributorListItem

class ContributorsEpoxyController(
    private val clickAction: ClickAction
): Typed2EpoxyController<List<ContributorsResponse>,Boolean>() {

    override fun buildModels(contributors: List<ContributorsResponse>?,boolean: Boolean) {
        contributors?.forEach { contributor ->
            epoxyContributorListItem {
                id(contributor.nodeId)
                contributor(contributor)
                click(View.OnClickListener { clickAction.onClick(contributor.login) })
            }
        }
    }

    interface ClickAction{
        fun onClick(login: String)
    }
}