package br.com.fiap.carrerup.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.R
import br.com.fiap.carrerup.components.SideMenu
import br.com.fiap.carrerup.model.User
import br.com.fiap.carrerup.util.getDescription
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun SingleProfileScreen(
    navController: NavHostController,
    user: User? = null,
    area: String? = "",
    isSelf: Boolean? = false
) {

    val fullName = user!!.name.first + " " + user.name.last

    SideMenu(title = "PERFIL") { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 90.dp),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user.picture.large)
                                .placeholder(R.drawable.generic_account_circle)
                                .error(R.drawable.generic_account_circle)
                                .crossfade(true)
                                .build()
                        ),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    if (isSelf == false) {
                        Row {
                            Button(
                                onClick = {},
                                enabled = false,
                                colors = ButtonDefaults.buttonColors(Color.Gray),
                                elevation = ButtonDefaults.elevatedButtonElevation(3.dp)
                            ) {
                                Text(text = "Denunciar")
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                            Button(
                                onClick = {
                                    // TODO: implementar botão de seguir
                                },
                                enabled = true,
                                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                                elevation = ButtonDefaults.elevatedButtonElevation(3.dp)
                            ) {
                                Text(text = "Seguir")
                            }
                        }
                    } else {
                        Button(
                            onClick = {},
                            enabled = false,
                            colors = ButtonDefaults.buttonColors(Color.Red),
                            elevation = ButtonDefaults.elevatedButtonElevation(3.dp)
                        ) {
                            Text(text = "Sair da conta")
                        }
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = fullName.uppercase(),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${user.location.city}, ${user.location.state}, ${user.location.country}",
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "Idade: ${user.dob.age} anos")

                    Text(text = "Área: ${area}")
                    Text(text = "Experiência: ${user.registered.age} anos")
                    Spacer(modifier = Modifier.padding(5.dp))
                    Card(
                        modifier = Modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ) {
                        Text(
                            text = getDescription(area!!),
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }

}
