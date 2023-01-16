package data.model

import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto

data class View(
    val id: UUID,
    val name: String?,
    var items: List<BaseItemDto>? = null,
    val type: String?
)