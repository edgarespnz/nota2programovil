package pe.edu.ulima.ui.app.uis

import android.media.Image
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid


import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import pe.edu.ulima.ui.RandomImageItem
import pe.edu.ulima.ui.RandomImage
import pe.edu.ulima.ui.app.viewmodels.InstagramCloneViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel

import pe.edu.ulima.ui.theme.Orange200
import java.io.IOException
import kotlin.math.min

@Preview
@Composable
public fun InstagramCloneScreenPreview(){
    InstagramCloneScreen(
        goToEditProfileScreen = {},
        InstagramCloneViewModel(),
        rememberNavController(),
        0
    )
}

@Composable
public fun InstagramCloneScreen(
    goToEditProfileScreen: () -> Unit,
    viewModel: InstagramCloneViewModel,
    navController: NavHostController,
    profileId: Int
){

    //arreglo mutable para almacenar el fetch de imagenes
    val imagesList = remember { mutableStateListOf<String>() }

    //variables de modificacion
    val nombreUsuario = "Luisito Ramirez"
    val numSeguidores = 52
    val numSeguidos = 55
    val numPub = 52


    //función fetch para lista de imágenes
    fun getImages() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.thedogapi.com/v1/images/search?limit=10")
            .build()


        //crear instancia de client y solicitud
        //metodo enqueue para ejecutar la solicitud en segundo plano
        //la respuesta se recibe de forma asíncrona


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Manejo de errores
                Log.e("API", "Error al hacer la solicitud a la API", e)
            }

            //verificar si la respuesta fue exitosa o mostrar un mensaje de error en su defecto
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val gson = Gson() //trabajar con el formato JSON
                    val images = gson.fromJson(responseBody, Array<RandomImageItem>::class.java)

                    // hacer algo con la lista de imágenes
                    images.forEach { value ->
                        imagesList.add(value.url)
                    }

                } else {
                    // mostrar un mensaje de error al usuario
                    Log.e("API", "Error al hacer la solicitud a la API: ${response.code}")
                }
            }
        })
    }

    // Ejecutar la función getImages() en un hilo de fondo al crear el @Composable
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            getImages()
        }
    }

    //cabecera con foto de perfil , #publicaciones #seguidos #seguidores y nombre.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp),
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = rememberImagePainter(data = "https://i0.wp.com/lamiradafotografia.es/wp-content/uploads/2014/07/foto-perfil-psicologo-180x180.jpg?resize=180%2C180"),
                        contentDescription = "Foto Perfil",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = nombreUsuario,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = Integer.toString(numPub),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Publicaciones",
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = Integer.toString(numSeguidos),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Seguidos",
                        textAlign = TextAlign.Center,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = Integer.toString(numSeguidores),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Seguidores",
                        textAlign = TextAlign.Center,
                    )
                }
            }

            //fila de botones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange200),
                    onClick = {
                        goToEditProfileScreen()
                    }
                ){
                    Text("Editar Perfil",
                    color =  if(isSystemInDarkTheme()) Color.White else Color.Black)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange200),
                    onClick = {
                        println("Presiono compartir perfil")
                    }
                ){
                    Text("Compartir Perfil",
                        color =  if(isSystemInDarkTheme()) Color.White else Color.Black)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange200),
                    onClick = {
                        println("Presiono agregar algo")
                    }
                ){
                    Icon(Icons.Default.Person,
                        contentDescription = "content description",
                        tint =  if(isSystemInDarkTheme()) Color.White else Color.Black)
                    Icon(Icons.Default.Add,
                        contentDescription = "content description",
                        tint =  if(isSystemInDarkTheme()) Color.White else Color.Black)
                }
            }

            //historias destacadas

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Historias destacadas",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "Guarda tus historias favoritas en el perfil",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    OutlinedButton(
                        modifier= Modifier.size(50.dp),  //avoid the oval shape
                        shape = CircleShape,
                        border= BorderStroke(3.dp, if(isSystemInDarkTheme()) Color.White else Color.Black),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            println("Crea nueva historia")
                        }
                    ){
                        Icon(
                            Icons.Default.Add,
                            modifier= Modifier.size(45.dp),
                            contentDescription = "content description",
                            tint =  if(isSystemInDarkTheme()) Color.White else Color.Black)
                    }

                    Text(
                        text = "Nueva",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 12.sp
                    )
                }

            }

            //Grid de imagenes
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //todo : grid de imagenes 3x3
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                    ){
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            contentPadding = PaddingValues(all=8.dp),
                            modifier = Modifier.fillMaxSize()
                        ){
                            items(min(imagesList.size, 9)) { i ->
                                Image(
                                    painter = rememberImagePainter(data = imagesList[i]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .fillMaxWidth()
                                        .padding(1.dp)
                                )
                            }
                        }
                    }
                }

            }
        }


    }
}