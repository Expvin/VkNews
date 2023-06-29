package com.expv1n.vknews.di

import androidx.lifecycle.ViewModel
import com.expv1n.vknews.presentation.comments.CommentsViewModel
import com.expv1n.vknews.presentation.main.MainViewModel
import com.expv1n.vknews.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

}