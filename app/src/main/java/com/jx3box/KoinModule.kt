package com.jx3box

import com.jx3box.data.net.BoxService
import com.jx3box.data.net.RetrofitClient
import org.koin.dsl.module

/**
 * @author Carey
 * @date 2020/9/17
 */

val viewModelModule = module {
}


val repositoryModule = module {
    single { RetrofitClient.init(App.CONTEXT) }
    single { RetrofitClient.getService(BoxService::class.java, BoxService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
}

val appModule = listOf(viewModelModule, repositoryModule)