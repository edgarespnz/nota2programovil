package pe.edu.ulima.navigations

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.ui.app.uis.*
import pe.edu.ulima.ui.app.viewmodels.*

@Composable
fun AppNavigation(
    pokemonScreenModel: PokemonViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel,
    profileViewModel: ProfileViewModel,
    seguidoresViewModel: SeguidorViewModel,
    seguidosViewModel: SeguidosViewModel,
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val pokemonIdParam = navBackStackEntry?.arguments?.getInt("pokemon_id")
    val userId = navBackStackEntry?.arguments?.getInt("user_id")

    NavHost(
        navController = navController,
        startDestination = "/pokemon"
    ){
        // vista para mostrar el listado de pokemones
        composable(
            route = "/pokemon",
            arguments = listOf(
            )
        ){

           PokemonScreen(
               viewModel = pokemonScreenModel,
               navController,
               0
           )
        }
        // vista perifles
        composable(
            route = "/pokemon?user_id={user_id}",
            arguments = listOf(
                navArgument("user_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            PokemonScreen(
                viewModel = pokemonScreenModel,
                navController,
                userId!!
            )
        }
        // editar pokemon
        composable(
            route = "/pokemon/detalle?pokemon_id={pokemon_id}",
            arguments = listOf(
                navArgument("pokemon_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            Log.d("APP_NAVIGATION", pokemonIdParam.toString())
            pokemonDetailViewModel.getPokemon(pokemonIdParam!!)
            PokemonDetailScreen(
                viewModel = pokemonDetailViewModel
            )
        }
        // crear pokemon
        composable(
            route = "/pokemon/new",
            arguments = listOf(
            )
        ){
            pokemonDetailViewModel.unsetPokemon()
            PokemonDetailScreen(
                viewModel = pokemonDetailViewModel
            )
        }
        // profile
        composable(
            route = "/profile/?user_id={user_id}",
            arguments = listOf(
                navArgument("user_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            Log.d("APP_NAVIGATION", pokemonIdParam.toString())
            profileViewModel.setUsuario(userId!!)
            ProfileScreen(
                viewModel = profileViewModel
            )
        }
        // seguidos
        composable(
            route = "/seguidos?user_id={user_id}",
            arguments = listOf(
                navArgument("user_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            seguidosViewModel.setUsuarios(userId!!)
            SeguidosScreen(
                viewModel = seguidosViewModel,
                navController
            )
        }
        // seguidores
        composable(
            route = "/seguidores?user_id={user_id}",
            arguments = listOf(
                navArgument("user_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            seguidoresViewModel.setUsuarios(userId!!)
            Log.d("XD", "+++++++++++++++++++++++++++++++++++++++")
            SeguidoresScreen(
                viewModel = seguidoresViewModel,
                navController,
            )
        }
    }
}