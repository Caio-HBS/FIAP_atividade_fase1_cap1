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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.carrerup.model.User
import br.com.fiap.carrerup.ui.screens.MentorshipScreen
import br.com.fiap.carrerup.ui.screens.NewsScreen
import br.com.fiap.carrerup.ui.screens.RegisterScreen
import br.com.fiap.carrerup.ui.screens.SingleProfileScreen
import br.com.fiap.carrerup.ui.theme.CarrerUpTheme
import br.com.fiap.carrerup.util.DataStoreManager
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

                    val dataStoreContext = LocalContext.current
                    val dataStoreManager = DataStoreManager(dataStoreContext)

                    NavHost(
                        navController = navController,
                        startDestination = "mentorship"
                    ) {
                        composable(
                            route = "mentorship",
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { 1000 },
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -1000 },
                                    animationSpec = tween(400)
                                )
                            }
                        ) { MentorshipScreen(navController, dataStoreManager) }

                        composable(
                            route = "singleProfile/{area}/{info}",
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { 1000 },
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -1000 },
                                    animationSpec = tween(400)
                                )
                            }
                        ) {
                            val area = it.arguments?.getString("area")
                            val json = it.arguments?.getString("info")
                            val gson = Gson()
                            val info = gson.fromJson(json, User::class.java)

                            SingleProfileScreen(
                                navController = navController,
                                user = info!!,
                                area = area,
                                dataStoreManager = dataStoreManager
                            )
                        }

                        composable(
                            route = "register",
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { 1000 },
                                    animationSpec = tween(500)
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -1000 },
                                    animationSpec = tween(400)
                                )
                            }
                        ) { RegisterScreen(navController) }

                        composable(
                            route = "news",
                            enterTransition = { fadeIn(animationSpec = tween(500)) },
                            exitTransition = { fadeOut(animationSpec = tween(300)) }
                        ) { NewsScreen(navController, dataStoreManager) }

                    }

                }
            }
        }
    }
}

