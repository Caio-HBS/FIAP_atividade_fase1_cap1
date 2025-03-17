package br.com.fiap.carrerup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.carrerup.R

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    isNumber: Boolean = false,
    isExperience: Boolean = false,
    onPasswordVisibilityChange: (() -> Unit)? = null
) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(30.dp))
            .padding(1.dp),
        label = {
            Text(text = label)
        },
        shape = RoundedCornerShape(50.dp),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image =
                    if (passwordVisible) R.drawable.visibility_off else R.drawable.visibility
                IconButton(onClick = { onPasswordVisibilityChange?.invoke() }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = "Mudar visibilidade"
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedTextColor = Color.Gray,
            focusedTextColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            cursorColor = Color.Gray
        ),
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        suffix = if (isExperience) {
            { Text(text = "ANOS") }
        } else null,
    )

}