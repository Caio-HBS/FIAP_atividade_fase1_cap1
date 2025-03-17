package br.com.fiap.carrerup.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.carrerup.R
import br.com.fiap.carrerup.model.User
import br.com.fiap.carrerup.ui.theme.SandBlue
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.gson.Gson

@Composable
fun Mentors(res: User, controller: NavHostController, area: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(SandBlue),
        onClick = {
            val gson = Gson()

            val json = Uri.encode(gson.toJson(res))

            controller.navigate("singleProfile/$area/$json")
        }
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(res.picture.large)
                        .placeholder(R.drawable.generic_account_circle)
                        .error(R.drawable.generic_account_circle)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Text(
                    text = "${res.name.first + " " + res.name.last}, ${res.dob.age}".uppercase(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "${res.location.city}, ${res.location.state}, ${res.location.country.uppercase()}",
                    fontSize = 15.sp
                )
                Spacer(Modifier.padding(top = 8.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Experiência na área: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append(res.registered.age.toString())
                            append(" anos")
                        }
                    }
                )
            }
        }
    }

}
