package pe.edu.ulima.ui.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstagramCloneViewModel: ViewModel() {

    private val _publicaciones = MutableLiveData<String>("")
    var publicaciones: LiveData<String> = _publicaciones
    fun updatePublicaciones(it: String){
        _publicaciones.postValue(it)
    }

    private val _seguidores = MutableLiveData<String>("")
    var seguidores: LiveData<String> = _seguidores
    fun updateSeguidores(it: String){
        _seguidores.postValue(it)
    }

    private val _name = MutableLiveData<String>("")
    var name: LiveData<String> = _name
    fun updateName(it: String){
        _name.postValue(it)
    }
}