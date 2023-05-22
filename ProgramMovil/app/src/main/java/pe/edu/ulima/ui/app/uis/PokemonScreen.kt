package pe.edu.ulima.ui.app.uis

import android.app.Activity
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.models.Imagen
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel
import pe.edu.ulima.ui.theme.Orange200
import pe.edu.ulima.ui.theme.TopBar

@Preview
@Composable
public fun PokemonScreenPreview(){
    PokemonScreen(
        PokemonViewModel(),
        rememberNavController(),
        0
    )
}

val LocalActivity = compositionLocalOf<ComponentActivity> { error("No Activity found") }

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun PokemonScreen(
    viewModel: PokemonViewModel,
    navController: NavHostController,
    profileId: Int
){
    val context = LocalContext.current
    val activity = context as Activity
    val intent = activity.intent
    Log.d("POKEMON_SCREEN", intent.getIntExtra("user_id", 0).toString())
    var userId = 0
    if (profileId == 0){
        userId = intent.getIntExtra("user_id", 0)
    }else{
        userId = profileId
    }
    val seguidores: Int by viewModel.seguidores.observeAsState(0)
    val seguidos: Int by viewModel.seguidos.observeAsState(0)
    //viewModel.setPokemons()
    viewModel.setImagenes(userId)
    viewModel.setSeguidores(userId)
    viewModel.setSeguidos(userId)
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = ModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
        ),
        sheetContent = {
            // Content of the Modal BottomSheet
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
            ) {
                Text("Modal BottomSheet Content")
                // isBottomSheetOpen = true
            }
        }
    ) {
        Column(
        ) {
            TopBar(
                showBottomSheetDialog = {
                    isBottomSheetOpen = true
                },
                navController,
                userId
            )
            Text(
                "Seguidores: ${seguidores}",
                modifier = Modifier.clickable {
                    Log.d("POKEMON_SCREEN", "SEGUIDORES CLICK")
                    navController.navigate("/seguidores?user_id=$userId")
                }
            )
            Text(
                "Seguidos: ${seguidos}",
                modifier = Modifier.clickable {
                    Log.d("POKEMON_SCREEN", "SEGUIDOS CLICK")
                    navController.navigate("/seguidos")
                }
            )
            Text(
                "Publicaciones: ${viewModel.imagenes!!.size}"
            )
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(5),
                content = {
                    /*
                    items(viewModel.pokemons!!.size) { i ->
                        val pokemon: Pokemon = viewModel.pokemons!![i]
                        Image(
                            painter = rememberImagePainter(data = pokemon.url),
                            contentDescription = pokemon.nombre,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(bottom = 10.dp)
                                .clickable {
                                    Log.d("POKEMON_SCREEN", pokemon.id.toString())
                                    navController.navigate("/pokemon/detalle?pokemon_id=${pokemon.id.toString()}")
                                },
                        )
                    }*/
                    items(viewModel.imagenes!!.size) { i ->
                        val imagen: Imagen = viewModel.imagenes!![i]
                        Image(
                            painter = rememberImagePainter(data = imagen.url),
                            contentDescription = "XD",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(bottom = 10.dp)
                                .clickable {
                                    //Log.d("POKEMON_SCREEN", pokemon.id.toString())
                                    //navController.navigate("/pokemon/detalle?pokemon_id=${pokemon.id.toString()}")
                                },
                        )
                    }
                }
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("/pokemon/new")
                      },
            content = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                ) },
            backgroundColor = Orange200,
            modifier = Modifier.padding(20.dp)
        )
    }

    if(userId != 0){
        println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        BackHandler {
            Log.d("POKEMON_SCREEN", "XDDDDDDDDDDDDDDDDDDDDDDDdd")
            finishAffinity(context as Activity)
        }
    }
}

