package hu.bme.aut.mob_soft_lab.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.mob_soft_lab.network.BackendService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideBackendService(app: Application): BackendService {
        return BackendService()
    }
}