package pe.edu.ulima.ui.app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.models.Usuario
import pe.edu.ulima.services.PokemonService
import pe.edu.ulima.services.SeguidorService
import pe.edu.ulima.services.UserService

class SeguidorViewModel: ViewModel() {
    private val _id = MutableLiveData<Int>(0)
    var id: LiveData<Int> = _id
    fun updateId(it: Int){
        _id.postValue(it)
    }

    private val _usuario = MutableLiveData<String>("")
    var usuario: LiveData<String> = _usuario
    fun updateUsuario(it: String){
        _usuario.postValue(it)
    }

    private val _usuarios = mutableStateOf<List<Usuario>?>(
        listOf()
    )
    val usuarios get() = _usuarios.value
    fun setUsuarios(userId: Int) {
        _usuario.value = UserService.fetchOne(userId).usuario
        _usuarios.value = SeguidorService.fetchSeguidores(userId)
    }
}