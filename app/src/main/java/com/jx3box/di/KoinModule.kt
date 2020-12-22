/*
 *       Copyright (C) 2020.  jx3box.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jx3box.di

import com.jx3box.data.net.repository.*
import com.jx3box.mvvm.IndexViewModel
import com.jx3box.ui.article.ArticleViewModel
import com.jx3box.ui.login.LoginViewModel
import com.jx3box.ui.main.fragment.mine.MineViewModel
import com.jx3box.ui.message.MessageViewModel
import com.jx3box.ui.register.RegisterViewModel
import com.jx3box.ui.search.SearchViewModel
import com.jx3box.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Carey
 * @date 2020/9/17
 */

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MineViewModel() }
    viewModel { ArticleViewModel(get()) }
    viewModel { IndexViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { MessageViewModel(get()) }
}


val repositoryModule = module {
    single { AdvertRepository() }
    single { LoginRepository() }
    single { ArticleRepository() }
    single { IndexRepository() }
    single { SearchRepository() }
    single { RankRepository() }
    single { MessageRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)