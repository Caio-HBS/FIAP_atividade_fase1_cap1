package br.com.fiap.carrerup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.carrerup.model.User
import br.com.fiap.carrerup.screens.MentorshipScreen
import br.com.fiap.carrerup.screens.SingleProfileScreen
import br.com.fiap.carrerup.ui.theme.CarrerUpTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarrerUpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "mentorship"
                    ) {
                        composable(
                            route = "mentorship",
                            enterTransition = { fadeIn(animationSpec = tween(500)) },
                            exitTransition = { fadeOut(animationSpec = tween(300)) }
                        ) {
                            MentorshipScreen(navController)
                        }

                        composable(
                            route = "singleProfile/{area}/{info}",
                            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
                            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(400)) }
                        ) {
                            val area = it.arguments?.getString("area")

                            val json = it.arguments?.getString("info")
                            val gson = Gson()

                            val info = gson.fromJson(json, User::class.java)

                            SingleProfileScreen(navController, info!!, area)
                        }
                    }

                }
            }
        }
    }
}
