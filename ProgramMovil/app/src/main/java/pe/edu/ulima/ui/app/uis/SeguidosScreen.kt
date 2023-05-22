package pe.edu.ulima.ui.app.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.models.Imagen
import pe.edu.ulima.models.Usuario
import pe.edu.ulima.ui.app.viewmodels.SeguidorViewModel
import pe.edu.ulima.ui.app.viewmodels.SeguidosViewModel

@Preview
@Composable
public fun SeguidosScreenPreview(){
    SeguidosScreen(
        SeguidosViewModel(),
        rememberNavController()
    )
}

@Composable
public fun SeguidosScreen(
    viewModel: SeguidosViewModel,
    navController: NavHostController,
){
    Text("Seguidos")
    Text(viewModel.usuario.toString())
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
        content = {
            items(viewModel.usuarios!!.size) { i ->
                val usuario: Usuario = viewModel.usuarios!![i]
                Image(
                    painter = rememberImagePainter(data = usuario.imagen),
                    contentDescription = "XD",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            //Log.d("POKEMON_SCREEN", pokemon.id.toString())
                            navController.navigate("/pokemon?user_id=${usuario.id}")
                        },
                )
                Text(
                    usuario.nombre
                )
                Text(
                    usuario.usuario
                )
                Text(
                    usuario.correo
                )
                Divider()
            }
        }
    )
}