import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.components.SideMenu
import br.com.fiap.carrerup.util.DataStoreManager

@Composable
fun Home(navController: NavHostController, dataStoreManager: DataStoreManager) {

    val scope = rememberCoroutineScope()
    val isLoggedIn by dataStoreManager.getIsLoggedIn().collectAsState(initial = false)

    SideMenu(
        title = "MENTORES",
        navController = navController,
        dataStoreManager = dataStoreManager
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            Column(Modifier.padding(100.dp)) {
                Text(text = "Usuário está logado: $isLoggedIn")
                Button(
                    onClick = {
                        scope.launch {
                            if (isLoggedIn) {
                                dataStoreManager.logout()
                            } else {
                                dataStoreManager.login()
                            }
                        }
                    }
                ) {
                    Text(if (isLoggedIn) "Logout" else "Logar")
                }
            }
        }
    }

}
