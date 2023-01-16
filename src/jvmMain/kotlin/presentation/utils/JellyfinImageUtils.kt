package presentation.utils

import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import presentation.App

fun getJellyfinBaseItemImage(episode:BaseItemDto): String? {
    if (episode == null) return null

    val baseUrl = App().jellyfinRepository.getBaseUrl()
    var imageItemId = episode.id
    var imageType = ImageType.PRIMARY

    if (!episode.imageTags.isNullOrEmpty()) { // TODO: Downloadmetadata currently does not store imagetags, so it always uses the backdrop
        when (episode.type) {
            BaseItemKind.MOVIE -> {
                if (!episode.backdropImageTags.isNullOrEmpty()) {
                    imageType = ImageType.BACKDROP
                }
            }
            else -> {
                if (!episode.imageTags!!.keys.contains(ImageType.PRIMARY)) {
                    imageType = ImageType.BACKDROP
                }
            }
        }
    } else {
        if (episode.type == BaseItemKind.EPISODE) {
            imageItemId = episode.seriesId!!
            imageType = ImageType.BACKDROP
        }
    }

    return "$baseUrl/items/$imageItemId/Images/$imageType"
}