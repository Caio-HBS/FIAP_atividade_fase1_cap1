package br.com.fiap.carrerup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.components.LazyStaggeredGridItem
import br.com.fiap.carrerup.components.SideMenu
import br.com.fiap.carrerup.ui.theme.BostonBlue
import br.com.fiap.carrerup.ui.theme.DarkTeal
import br.com.fiap.carrerup.ui.theme.DeepBlue
import br.com.fiap.carrerup.ui.theme.Keppel
import br.com.fiap.carrerup.ui.theme.PaleLightGreen
import br.com.fiap.carrerup.ui.theme.SweetCorn
import br.com.fiap.carrerup.ui.theme.Tasha
import br.com.fiap.carrerup.util.DataStoreManager

@Composable
fun NewsScreen(
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {

    SideMenu(
        title = "NOTICIAS",
        navController = navController,
        dataStoreManager = dataStoreManager
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
                .background(color = PaleLightGreen)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = "O que você",
                    modifier = Modifier.padding(horizontal = 25.dp),
                    fontSize = 27.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DeepBlue
                )
                Text(
                    text = "quer descobrir hoje?",
                    modifier = Modifier.padding(horizontal = 25.dp),
                    fontSize = 27.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DeepBlue
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = "Escolha um tópico",
                    modifier = Modifier.padding(horizontal = 25.dp),
                    fontSize = 20.sp,
                    color = DarkTeal
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))
                LazyStaggeredGridItem(
                    colorList = listOf(SweetCorn, Keppel, BostonBlue, BostonBlue),
                    heightsList = listOf(160.dp, 120.dp, 180.dp, 140.dp),
                    info = mapOf("T.I." to "", "SAÚDE" to "", "FINANÇAS" to "", "MARKETING" to "")
                )
                LazyStaggeredGridItem(
                    colorList = listOf(Tasha, Tasha, SweetCorn, Keppel),
                    heightsList = listOf(120.dp, 160.dp, 180.dp, 140.dp),
                    info = mapOf("DADOS" to "", "PESQUISA" to "", "DIREITO" to "", "TURISMO" to "")
                )
            }

        }

    }

}