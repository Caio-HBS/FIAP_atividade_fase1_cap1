package br.com.fiap.carrerup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.R
import br.com.fiap.carrerup.components.Mentors
import br.com.fiap.carrerup.components.SideMenu
import br.com.fiap.carrerup.enums.Areas
import br.com.fiap.carrerup.factory.RandomuserFactory
import br.com.fiap.carrerup.model.UserResponse
import br.com.fiap.carrerup.ui.theme.DarkTeal
import br.com.fiap.carrerup.ui.theme.SoftTeal
import br.com.fiap.carrerup.util.DataStoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

@Composable
fun MentorshipScreen(
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {

    var areaOfInterest by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    var isError by remember { mutableStateOf(false) }

    var listUsersState by remember {
        mutableStateOf<UserResponse?>(null)
    }

    SideMenu(
        title = "MENTORES",
        navController = navController,
        dataStoreManager = dataStoreManager
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_mentor_list),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    items(Areas.entries.toTypedArray()) { area ->
                        Button(
                            modifier = Modifier.padding(horizontal = 1.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                if (area.name == areaOfInterest) DarkTeal else SoftTeal
                            ),
                            onClick = {
                                areaOfInterest = area.name

                                isLoading = true

                                /* Obs.: A API escolhida apenas gera usuários aleatórios, portanto
                                geramos um número qualquer entre 1 e 20 para fazer chamada à API na
                                intenção de simular uma operação de consulta a um backend, que
                                retornaria diferentes usuários para diferentes parâmetros de busca.
                                */
                                val call = RandomuserFactory()
                                    .getUserService()
                                    .getUsers(Random.nextInt(1, 21))

                                call.enqueue(object : Callback<UserResponse> {
                                    override fun onResponse(
                                        call: Call<UserResponse>,
                                        res: Response<UserResponse>
                                    ) {
                                        if (res.isSuccessful) {
                                            isError = false
                                            isLoading = false
                                            listUsersState = res.body()
                                        } else {
                                            isLoading = false
                                            isError = true
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<UserResponse>,
                                        res: Throwable
                                    ) {
                                        isLoading = false
                                        isError = true
                                    }
                                })
                            },
                        ) {
                            Text(text = area.label)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                if (listUsersState != null) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        items(listUsersState!!.users) { user ->
                            Mentors(res = user, controller = navController, area = areaOfInterest)
                        }
                    }
                }
                // Renderiza a barra de carregamento na tela.
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.LightGray,
                            trackColor = Color.Gray,
                            strokeWidth = 5.dp
                        )
                    }
                }
                // Responsável por informar o usuário que a operação não deu certo.
                if (isError) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            elevation = CardDefaults.elevatedCardElevation(8.dp)
                        ) {
                            Text(
                                text = "PARECE QUE NOSSOS SERVIDORES ESTÃO SOBRECARREGADOS",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(16.dp)
                            )
                            Text(
                                text = "Tente novamente mais tarde.",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}
