package com.experiment.android.prototype.paging.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.experiment.android.prototype.paging.data.GithubRepository
import com.experiment.android.prototype.paging.model.Repo
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [GithubRepository] to get the data.
 */
class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString

        // Note: If we're doing any operations on the Flow, like map or filter, make sure you call cachedIn after you execute these operations to ensure you don't need to trigger them again.
        val newResult: Flow<PagingData<Repo>> = repository.getSearchResultStream(queryString)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
