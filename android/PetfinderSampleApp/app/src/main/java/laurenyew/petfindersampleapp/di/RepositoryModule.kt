package laurenyew.petfindersampleapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import laurenyew.petfindersampleapp.repository.PetSearchRepository
import laurenyew.petfindersampleapp.repository.networking.commands.SearchPetsCommands
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providePetSearchRepository(searchPetsCommands: SearchPetsCommands): PetSearchRepository =
        PetSearchRepository(searchPetsCommands)
}