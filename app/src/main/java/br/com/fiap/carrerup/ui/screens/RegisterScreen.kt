package br.com.fiap.carrerup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.R
import br.com.fiap.carrerup.components.CustomOutlinedTextField
import br.com.fiap.carrerup.enums.Areas
import br.com.fiap.carrerup.ui.theme.*

@Composable
fun RegisterScreen(navController: NavHostController) {

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var areaOfInterest by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_register_profile),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = DarkBlueGreen.copy(alpha = 0.6f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bem Vindo(a)!",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Crie sua conta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    CustomOutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Digite seu nome"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = surname,
                        onValueChange = { surname = it },
                        label = "Digite seu sobrenome"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Digite seu e-mail"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Digite sua senha",
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = "Confirme sua senha",
                        isPassword = true,
                        passwordVisible = confirmPasswordVisible,
                        onPasswordVisibilityChange = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = experience,
                        onValueChange = {
                            if (
                                it.isEmpty() && it.length <= 2 ||
                                it.toIntOrNull() != null && it.length <= 2
                            ) {
                                experience = it
                            }
                        },
                        label = "Anos de experiência na área",
                        isNumber = true,
                        isExperience = true
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomOutlinedTextField(
                        value = bio,
                        onValueChange = {
                            if (it.length <= 180) {
                                bio = it
                            }
                        },
                        label = "Fale sobre você"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                },
                            ) {
                                Text(text = area.label)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = {
                            /* Visto que não foi pedido que fizéssemos um backend, essas informações
                            não são enviadas a lugar algum, mas ficam prontas para serem enviadas em
                            versões posteriores caso seja necessário. Por enquanto, só mudamos a pá-
                            gina da aplicação.
                            */

                            // TODO: mudar para página de login/home
                        },
                        colors = ButtonDefaults.buttonColors(SoftSeaBlue),
                    ) {
                        Text(
                            text = "CADASTRAR",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }
    }


}