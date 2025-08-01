package com.open_mobile_kit.android.codebase.data.remote

/**
 * A standardized model for paginated API requests.
 *
 * @property page The page number to retrieve.
 * @property limit The number of items per page.
 * @property query An optional search query.
 */
data class RequestPagination(
    val page: Int = 1,
    val limit: Int = 20,
    val query: String? = null
)
