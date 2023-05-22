package pe.edu.ulima.ui.app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.models.Imagen
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.services.ImagenService
import pe.edu.ulima.services.PokemonService
import pe.edu.ulima.services.SeguidorService

class PokemonViewModel: ViewModel(){
    private val _pokemons = mutableStateOf<List<Pokemon>?>(
        listOf()
    )
    val pokemons get() = _pokemons.value
    fun setPokemons() {
        _pokemons.value = PokemonService.fetchAll()
    }

    private val _imagenes = mutableStateOf<List<Imagen>?>(
        listOf()
    )
    val imagenes get() = _imagenes.value
    fun setImagenes(userId: Int) {
        _imagenes.value = ImagenService.fetchByUserId(userId)
    }

    private val _selectedId = mutableStateOf<Int?>(
        null
    )
    val selectedId get() = _selectedId.value
    fun setSelectedId(selectedId: Int) {
        _selectedId.value = selectedId
    }

    private val _seguidores = MutableLiveData<Int>(
        0
    )
    val seguidores get() = _seguidores
    fun setSeguidores(userId: Int) {
        _seguidores.postValue(SeguidorService.countByUserId(userId))
    }

    private val _seguidos = MutableLiveData<Int>(
        0
    )
    val seguidos get() = _seguidos
    fun setSeguidos(userId: Int) {
        _seguidos.postValue(SeguidorService.countSeguidosBySeId(userId))
    }
}