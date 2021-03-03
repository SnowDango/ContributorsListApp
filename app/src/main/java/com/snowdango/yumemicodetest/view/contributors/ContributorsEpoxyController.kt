package com.snowdango.yumemicodetest.view.contributors

import com.airbnb.epoxy.Typed2EpoxyController
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import com.snowdango.yumemicodetest.epoxyContributorListItem

class ContributorsEpoxyController: Typed2EpoxyController<List<ContributorsResponse>,Boolean>() {

    override fun buildModels(contributors: List<ContributorsResponse>?,boolean: Boolean) {
        contributors?.forEach { contributor ->
            epoxyContributorListItem {
                id(contributor.nodeId)
                contributor(contributor)
            }
        }
    }
}