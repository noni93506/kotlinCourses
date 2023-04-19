package com.example.fragments.modules




import com.example.fragments.viewModules.UserVM
import com.example.fragments.rep.UserRepository
import com.example.fragments.rep.UserRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module



val appModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    viewModelOf(::UserVM)
}





