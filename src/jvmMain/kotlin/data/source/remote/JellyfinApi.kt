package data.source.remote

import data.utils.Constants
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.extensions.*
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

class JellyfinApi(
    requestTimeout: Long = Constants.NETWORK_DEFAULT_REQUEST_TIMEOUT,
    connectTimeout: Long = Constants.NETWORK_DEFAULT_CONNECT_TIMEOUT,
    socketTimeout: Long = Constants.NETWORK_DEFAULT_SOCKET_TIMEOUT
) {
    val jellyfin = createJellyfin {
        deviceInfo = DeviceInfo("0","Jellyflix Desktop")
        clientInfo =
            ClientInfo(name = "Jellyflix", version = "1.0.0")
    }

    val api = jellyfin.createApi(
        httpClientOptions = HttpClientOptions(
            requestTimeout = requestTimeout,
            connectTimeout = connectTimeout,
            socketTimeout = socketTimeout
        )
    )

    var userId = api.userId

    val devicesApi = api.devicesApi
    val systemApi = api.systemApi
    val userApi = api.userApi
    val viewsApi = api.userViewsApi
    val itemsApi = api.itemsApi
    val userLibraryApi = api.userLibraryApi
    val showsApi = api.tvShowsApi
    val sessionApi = api.sessionApi
    val videosApi = api.videosApi
    val mediaInfoApi = api.mediaInfoApi
    val playStateApi = api.playStateApi
}