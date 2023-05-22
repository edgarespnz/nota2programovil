package pe.edu.ulima.ui.login.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.activities.AppActivity
import pe.edu.ulima.services.UserService

class CreateAccountViewModel: ViewModel() {
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

    private val _contrasenia = MutableLiveData<String>("")
    var contrasenia: LiveData<String> = _contrasenia
    fun updateContrasenia(it: String){
        _contrasenia.postValue(it)
    }

    private val _contrasenia2 = MutableLiveData<String>("")
    var contrasenia2: LiveData<String> = _contrasenia2
    fun updateContrasenia2(it: String){
        _contrasenia2.postValue(it)
    }

    private val _mensaje = MutableLiveData<String>("")
    var mensaje: LiveData<String> = _mensaje
    fun updateMensaje(it: String){
        _mensaje.postValue(it)
    }

    fun validar(context: Context){
        val id: Int = UserService.validate(usuario.value!!, contrasenia.value!!)
        if(id == 0){
            updateMensaje("Error: Usuario y contraseña no válidos")
        }else{
            updateMensaje("Todo OK")
            Handler().postDelayed({
                updateMensaje("")
                val appActivity =  Intent(context, AppActivity::class.java)
                appActivity.putExtra("user_id", id)
                context.startActivity(
                    appActivity
                )
            }, 1000)
        }
    }
}