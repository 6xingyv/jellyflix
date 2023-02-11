package data.repository

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import data.model.SortBy
import data.source.remote.JellyfinApi
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.SortOrder

class ItemsPagingSource(
    private val jellyfinApi: JellyfinApi,
    private val parentId: UUID?,
    private val includeTypes: List<BaseItemKind>?,
    private val recursive: Boolean,
    private val sortBy: SortBy,
    private val sortOrder: SortOrder
) : PagingSource<Int, BaseItemDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseItemDto> {
        val position = params.key ?: 0

        return try {
            val response = jellyfinApi.itemsApi.getItems(
                jellyfinApi.userId!!,
                parentId = parentId,
                includeItemTypes = includeTypes,
                recursive = recursive,
                sortBy = listOf(sortBy.SortString),
                sortOrder = listOf(sortOrder),
                startIndex = position,
                limit = params.loadSize
            ).content.items.orEmpty()
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (response.isEmpty()) null else position + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BaseItemDto>): Int {
        return 0
    }
}