package dev.fumin.smba.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dev.fumin.smba.api.AuthApi

@Module
@InstallIn(ViewModelComponent::class)
object Module {

    @Provides
    fun authApi(): AuthApi = AuthApi

}
