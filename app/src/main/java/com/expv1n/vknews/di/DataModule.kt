package com.expv1n.vknews.di

import android.content.Context
import com.expv1n.vknews.data.network.ApiFactory
import com.expv1n.vknews.data.network.ApiService
import com.expv1n.vknews.data.repository.NewsFeedRepositoryImp
import com.expv1n.vknews.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(repository: NewsFeedRepositoryImp): NewsFeedRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}