package com.example.BlinovPR23_25
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                LoginScreen()
            }
        }
    }
}
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    val isButtonEnabled = email.isNotBlank()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(
                text = "👋 Добро пожаловать!",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 26.dp)
            )
            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "Вход по E-mail",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 4.dp)
                    .align(Alignment.Start)
            )
            TextField(
                value = email,
                onValueChange = {email = it},
                placeholder = { Text("example@mail.ru") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    val intent = Intent( context, EmailCodeActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF1E88E5) else Color(0xFFE7E7E7)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Далее")
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Text(
                text = "Или войдите с помощью",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(bottom = 4.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Войти с Яндекс", color = Color.Black)
            }
        }
    }
}