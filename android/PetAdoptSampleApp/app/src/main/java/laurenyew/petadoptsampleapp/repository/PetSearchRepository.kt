package laurenyew.petadoptsampleapp.repository

import laurenyew.petadoptsampleapp.repository.networking.commands.SearchPetsCommands
import laurenyew.petadoptsampleapp.repository.responses.SearchPetsRepoResponse
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

//TODO: Filters on pet search --> location, type, gender, etc. Save in Room DB (filter name?)
@Singleton
class PetSearchRepository @Inject constructor(
    private val searchPetCommand: SearchPetsCommands
) {
    suspend fun getNearbyDogs(location: String): SearchPetsRepoResponse =
        try {
            val animals = searchPetCommand.searchForNearbyDogs(location)
            SearchPetsRepoResponse.Success(animals)
        } catch (e: Exception) {
            Timber.e(e)
            SearchPetsRepoResponse.Error(e)
        }
}