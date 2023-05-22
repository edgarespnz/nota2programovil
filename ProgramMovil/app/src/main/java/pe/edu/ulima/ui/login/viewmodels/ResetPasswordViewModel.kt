package pe.edu.ulima.ui.login.viewmodels

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.activities.AppActivity
import pe.edu.ulima.services.UserService
import java.util.regex.Matcher
import java.util.regex.Pattern

class ResetPasswordScreenViewModel: ViewModel() {
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


    fun reset(){
        val pattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
        val email = correo.value
        val matcher: Matcher = pattern.matcher(email)
        val matchFound: Boolean = matcher.matches()

        val id: Int = UserService.validateEmail(correo.value!!)

        if( !matchFound){
            updateMensaje("Error: Ingrese un correo válido")
        }
        else if(correo.value.isNullOrEmpty()){
            updateMensaje("Error: Campo vacío")
        }
        else{
            if(id == 0){
                updateMensaje("Error: El correo no existe")
            }
            else{
                updateMensaje("Usuario válido, contraseña reestablecida")
            }
        }
    }
}