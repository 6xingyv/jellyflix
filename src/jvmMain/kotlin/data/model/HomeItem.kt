package data.model

import org.jellyfin.sdk.model.UUID

sealed class HomeItem(id: UUID) {
    data class Libraries(val section: HomeSection) : HomeItem(section.id)

    data class Section(val homeSection: HomeSection) : HomeItem(homeSection.id)

    data class ViewItem(val view: View) : HomeItem(view.id)
}