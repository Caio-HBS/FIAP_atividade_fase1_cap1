package br.com.fiap.carrerup.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.factory.RandomuserFactory
import br.com.fiap.carrerup.model.UserResponse
import br.com.fiap.carrerup.ui.theme.LightGrayGreen
import br.com.fiap.carrerup.ui.theme.PaleLightGreen
import br.com.fiap.carrerup.ui.theme.PaleRed
import br.com.fiap.carrerup.util.DataStoreManager
import br.com.fiap.carrerup.util.getArea
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideMenu(
    title: String,
    navController: NavHostController,
    dataStoreManager: DataStoreManager,
    content: @Composable (PaddingValues) -> Unit,

    ) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var retrievedUser by remember { mutableStateOf<UserResponse?>(null) }

    val openAlertDialog = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    if (openAlertDialog.value) {
        AlertDialogBox(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = { openAlertDialog.value = false },
            dialogTitle = "Erro ao carregar perfil",
            dialogText = "Não foi possível carregar os dados do usuário.",
        )
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.background(Color.Transparent),
                drawerContainerColor = PaleLightGreen
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "MEU MENU",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider(Modifier.padding(vertical = 5.dp))

                    NavigationDrawerItem(
                        label = { Text("Mentoria") },
                        selected = title == "MENTORES",
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGrayGreen,
                            unselectedContainerColor = Color.Transparent
                        ),
                        onClick = { navController.navigate("mentorship") }
                    )
                    NavigationDrawerItem(
                        label = { Text("Feedback") },
                        selected = title == "FEEDBACK",
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGrayGreen,
                            unselectedContainerColor = Color.Transparent
                        ),
                        onClick = {
                            // TODO: caminha para página de feedback
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Noticias") },
                        selected = title == "NOTICIAS",
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGrayGreen,
                            unselectedContainerColor = Color.Transparent
                        ),
                        onClick = {
                            navController.navigate("news")
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        label = { Text("Meu perfil") },
                        selected = false,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGrayGreen,
                            unselectedContainerColor = Color.Transparent
                        ),
                        icon = { Icon(Icons.Outlined.AccountCircle, contentDescription = null) },
                        onClick = {

                            /* Obs.: A API escolhida apenas gera usuários aleatórios, portanto
                                 fazemos uma requisição de apenas um usuário à API na intenção de
                                 simular uma operação de consulta a um backend, que retornaria
                                 dados do usuário logado.
                                 */
                            val call = RandomuserFactory()
                                .getUserService()
                                .getUsers(1)

                            call.enqueue(object : Callback<UserResponse> {
                                override fun onResponse(
                                    call: Call<UserResponse>,
                                    res: Response<UserResponse>
                                ) {
                                    if (res.isSuccessful) {
                                        val area = getArea()
                                        retrievedUser = res.body()

                                        val gson = Gson()

                                        val json = Uri.encode(gson.toJson(retrievedUser!!.users[0]))

                                        navController.navigate("singleProfile/$area/$json")
                                    } else {
                                        openAlertDialog.value = true
                                    }
                                }

                                override fun onFailure(
                                    call: Call<UserResponse>,
                                    res: Throwable
                                ) {
                                    openAlertDialog.value = true
                                }
                            })

                        }
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Sair da conta",
                                color = PaleRed,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        selected = false,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = LightGrayGreen,
                            unselectedContainerColor = Color.Transparent
                        ),
                        onClick = {
                            scope.launch {
                                dataStoreManager.logout()
                            }
                            navController.navigate("home")
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(PaleLightGreen),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}
