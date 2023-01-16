package data.model

import dev.icerock.moko.resources.StringResource
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto

data class HomeSection(
    val id: UUID,
    val name: StringResource,
    var items: List<BaseItemDto>
)
