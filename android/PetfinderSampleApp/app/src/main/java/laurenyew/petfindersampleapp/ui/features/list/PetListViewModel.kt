package laurenyew.petfindersampleapp.ui.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import laurenyew.petfindersampleapp.repository.PetFavoriteRepository
import laurenyew.petfindersampleapp.repository.models.AnimalModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class PetListViewModel @Inject constructor(
    private val favoriteRepository: PetFavoriteRepository
) : ViewModel() {
    protected val _animals = MutableStateFlow<List<AnimalModel>>(emptyList())
    protected val _isLoading = MutableStateFlow(false)
    protected val _isError = MutableStateFlow(false)
    val animals: StateFlow<List<AnimalModel>> = _animals
    val isLoading: StateFlow<Boolean> = _isLoading
    val isError: StateFlow<Boolean> = _isError

    fun favorite(animalModel: AnimalModel) {
        Timber.d("Favorite: ${animalModel.id}")
        viewModelScope.launch {
            favoriteRepository.favorite(animalModel)

            val favoriteId = animalModel.id
            _animals.value.firstOrNull { it.id == favoriteId }?.isFavorite = true
        }
    }

    fun unfavorite(id: String) {
        Timber.d("Unfavorite: $id")
        viewModelScope.launch {
            favoriteRepository.unFavorite(id)

            val unfavoriteId = id
            _animals.value.firstOrNull { it.id == unfavoriteId }?.isFavorite = false
        }
    }

    fun openAnimalDetail(id: String) {
        Timber.d("Open Animal Detail $id")
    }
}