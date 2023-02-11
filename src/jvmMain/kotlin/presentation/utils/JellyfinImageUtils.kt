package presentation.utils

import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ImageType
import presentation.App

fun getJellyfinBackdropImage(item: BaseItemDto, isCropped: Boolean = false): String {
    val baseUrl = App().jellyfinRepository.getBaseUrl()
    var imageItemId = item.id
    var imageType = ImageType.PRIMARY

    if (!item.imageTags.isNullOrEmpty()) {
        when (item.type) {
            BaseItemKind.MOVIE -> {
                if (!item.backdropImageTags.isNullOrEmpty()) {
                    imageType = ImageType.BACKDROP
                }
            }

            else -> {
                if (!item.imageTags!!.keys.contains(ImageType.PRIMARY)) {
                    imageType = ImageType.BACKDROP
                }
            }
        }
    } else {
        if (item.type == BaseItemKind.EPISODE) {
            imageItemId = item.seriesId!!
            imageType = ImageType.BACKDROP
        }
    }
    return if (isCropped) {
        "$baseUrl/items/$imageItemId/Images/$imageType?fillHeight=360&fillWidth=640"
    } else {
        "$baseUrl/items/$imageItemId/Images/$imageType"
    }

}

fun getJellyfinPrimaryImage(item: BaseItemDto, isCropped: Boolean = false): String {
    val baseUrl = App().jellyfinRepository.getBaseUrl()
    val imageItemId = item.id
    val imageType = ImageType.PRIMARY
    return if (isCropped) {
        "$baseUrl/items/$imageItemId/Images/$imageType?fillHeight=320&fillWidth=480"
    } else {
        "$baseUrl/items/$imageItemId/Images/$imageType"
    }
}