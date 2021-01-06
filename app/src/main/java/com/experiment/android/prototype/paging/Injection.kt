package com.experiment.android.prototype.paging

import androidx.lifecycle.ViewModelProvider
import com.experiment.android.prototype.paging.api.GithubService
import com.experiment.android.prototype.paging.data.GithubRepository
import com.experiment.android.prototype.paging.ui.ViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}
