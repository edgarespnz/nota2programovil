package pe.edu.ulima.ui.login.uis

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.ulima.R
import pe.edu.ulima.ui.login.viewmodels.EditProfileViewModel
import pe.edu.ulima.ui.login.viewmodels.LoginViewModel
import pe.edu.ulima.ui.theme.Gray200
import pe.edu.ulima.ui.theme.Gray400
import pe.edu.ulima.ui.theme.Green200
import pe.edu.ulima.ui.theme.Orange200

@Preview
@Composable
public fun EditProfileScreenPreview(){
    EditProfileScreen(
        EditProfileViewModel(),
    )
}

@Composable
public fun EditProfileScreen(
    viewModel: EditProfileViewModel,
){
    val context = LocalContext.current
    // viewmodel
    val nombre : String by viewModel.name.observeAsState(initial = "")
    val usuario : String by viewModel.usuario.observeAsState(initial = "")
    val correo : String by viewModel.correo.observeAsState(initial = "")
    val mensaje: String by viewModel.mensaje.observeAsState(initial = "")
    // close
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = {
                Log.d("LOGIN_SCREEN", "test message")
                val activity = context as Activity
                activity.finish()
            },
            modifier = Modifier.align(Alignment.TopEnd).padding(10.dp)
        ){
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Person Icon",
            )
        }
    }
    // container
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Editar Perfil",
                textAlign = TextAlign.Center,
            )
            Image(
                painter = rememberImagePainter(data = "https://i0.wp.com/lamiradafotografia.es/wp-content/uploads/2014/07/foto-perfil-psicologo-180x180.jpg?resize=180%2C180"),
                contentDescription = "Foto Perfil",
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            if(mensaje.contains("Error")){
                Text(
                    text = mensaje.split(":")[1],
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            }else{
                Text(
                    text = mensaje,
                    textAlign = TextAlign.Center,
                    color = Color.Green
                )
            }

            // txtName
            TextField(
                value = nombre,
                onValueChange = {
                    viewModel.updateName(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nombre")
                },
                placeholder = {
                    Text(text= "Luisito Ramirez")
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )

            // txtUsuario
            TextField(
                value = usuario,
                onValueChange = {
                    viewModel.updateUsuario(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Usuario")
                },
                placeholder = {
                    Text(text= "lramirez")
                },
                singleLine = false,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )

            // txtCorreo
            TextField(
                value = correo,
                onValueChange = {
                    viewModel.updateCorreo(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Correo")
                },
                placeholder = {
                    Text(text= "lramirez@ulima.edu.pe")
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )

            // boton Actualizar Datos
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp/*, start = 40.dp, end = 40.dp*/), // start -> izquierda, end -> derecha
                onClick = {
                    viewModel.validar(context)
                }
            ){
                Text("Actualizar Datos".uppercase())
            }

            // boton Cambiar Contraseña
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp/*, start = 40.dp, end = 40.dp*/), // start -> izquierda, end -> derecha
                onClick = {
                    viewModel.updateMensaje("Se actualizó la contraseña")
                }
            ){
                Text("Cambiar Contraseña".uppercase())
            }


        }
    }
}