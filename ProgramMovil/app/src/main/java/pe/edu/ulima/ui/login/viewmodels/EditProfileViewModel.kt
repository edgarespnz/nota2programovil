package pe.edu.ulima.ui.login.viewmodels

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.edu.ulima.activities.AppActivity
import pe.edu.ulima.services.UserService

class EditProfileViewModel {

    private val _name = MutableLiveData<String>("")
    var name: LiveData<String> = _name
    fun updateName(it: String){
        _name.postValue(it)
    }

    private val _usuario = MutableLiveData<String>("")
    var usuario: LiveData<String> = _usuario
    fun updateUsuario(it: String){
        _usuario.postValue(it)
    }

    private val _correo = MutableLiveData<String>("")
    var correo: LiveData<String> = _correo
    fun updateCorreo(it: String){
        _correo.postValue(it)
    }

    private val _mensaje = MutableLiveData<String>("")
    var mensaje: LiveData<String> = _mensaje
    fun updateMensaje(it: String){
        _mensaje.postValue(it)
    }

    fun validar(context: Context) {
        val id: Int = UserService.validateUsernameAndEmail(usuario.value!!, correo.value!!)
        if(id != 0){
            updateMensaje("Error: El usuario o el correo ya existe")
        }
        else {
            updateMensaje("Perfil Actualizado!")
        }
    }
}