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
import pe.edu.ulima.ui.login.uis.CreateAccountScreen
import pe.edu.ulima.ui.login.uis.EditProfileScreen
import pe.edu.ulima.ui.login.viewmodels.EditProfileViewModel

@Composable
fun AppNavigation(
    instagramCloneViewModel: InstagramCloneViewModel
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = "/profile"
    ){
        composable(
            route = "/profile",
            arguments = listOf(
            )
        ){
           InstagramCloneScreen(
               goToEditProfileScreen = {
                   navController.navigate("/edit_profile")
               },
               viewModel = InstagramCloneViewModel(),
               navController,
               0

           )
        }

        composable(
            route = "/edit_profile",
            arguments = listOf()
        ){ entry ->
            EditProfileScreen(
               viewModel = EditProfileViewModel()
            )
        }

    }
}