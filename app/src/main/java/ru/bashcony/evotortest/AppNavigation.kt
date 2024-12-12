package ru.bashcony.evotortest

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.bashcony.evotortest.presentation.cart.CartContent
import ru.bashcony.evotortest.presentation.common.EvotorNavHost
import ru.bashcony.evotortest.presentation.profile.ProfileContent
import ru.bashcony.evotortest.presentation.registration.RegistrationContent

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
    )
}

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    EvotorNavHost(
        navController = navController,
        startDestination = Screen.Profile.route,
    ) {
        composable(Screen.Profile.route) {
            ProfileContent(
                onNavigationUp = { navController.navigateUp() },
                navigate = { navController.navigate(it.route) },
            )
        }

        composable(Screen.Registration.route) {
            RegistrationContent(
                onNavigationUp = { navController.navigateUp() },
            )
        }

        composable(Screen.Cart.route) {
            CartContent(
                onNavigationUp = { navController.navigateUp() },
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Profile : Screen(route = "profile")
    data object Registration : Screen(route = "registration")
    data object Cart : Screen(route = "cart")
}