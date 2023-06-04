package com.example.foodapp.di

import android.app.Application
import androidx.room.Room
import com.example.foodapp.feature_food.data.data_source.CarDatabase
import com.example.foodapp.feature_food.data.repository.CarRepositoryImpl
import com.example.foodapp.feature_food.domain.repository.CarRepository
import com.example.foodapp.feature_food.domain.use_cases.AddCarUseCase
import com.example.foodapp.feature_food.domain.use_cases.CarUseCases
import com.example.foodapp.feature_food.domain.use_cases.DeleteCarUseCase
import com.example.foodapp.feature_food.domain.use_cases.GetCarUseCase
import com.example.foodapp.feature_food.domain.use_cases.GetCarsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarDatabase(app: Application): CarDatabase{
        return Room.databaseBuilder(
            app,
            CarDatabase::class.java,
            CarDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarRepository(db: CarDatabase): CarRepository{
        return CarRepositoryImpl(db.carDao)
    }

    @Provides
    @Singleton
    fun providCarUseCases(repository: CarRepository): CarUseCases{
        return CarUseCases(
            addCarUseCase = AddCarUseCase(repository = repository),
            deleteCarUseCase = DeleteCarUseCase(repository = repository),
            getCarsUseCase = GetCarsUseCase(repository = repository),
            getCarUseCase = GetCarUseCase(repository = repository),
        )
    }
}