package com.experiment.android.prototype.paging.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.experiment.android.prototype.paging.api.GithubService
import com.experiment.android.prototype.paging.model.Repo
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository(private val service: GithubService) {

    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        Log.d("GithubRepository", "New query: $query")
        return Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),  // TODO: Look into if we can use enablePlaceholders for our shimmer effect
                pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
