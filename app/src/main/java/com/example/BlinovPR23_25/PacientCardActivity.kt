package com.example.BlinovPR23_25

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PacientCardAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                PacientCardScreen()
            }
        }
    }
}

@Composable
fun PacientCardScreen() {
    val name = remember { mutableStateOf("") }
    val middleName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val birthDate = remember { mutableStateOf("") }
    val context = LocalContext.current
    val isButtonEnabled = name.value.isNotBlank() &&
            middleName.value.isNotBlank() &&
            lastName.value.isNotBlank() &&
            birthDate.value.isNotBlank()

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Пропустить",
            fontSize = 14.sp,
            color = Color.Blue,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 5.dp)
                .clickable {
                    val intent = Intent(context, AnalyzesActivity::class.java)
                    context.startActivity(intent)
                }
        )
        Column {
            Text(
                text = "Создание карты\nпациента",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Без карты пациента вы не сможете заказать анализы.",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("В картах пациентов будут храниться результаты анализов вас и ваших близких.",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = middleName.value,
                onValueChange = { middleName.value = it },
                label = { Text("Отчество") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text("Фамилия") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = birthDate.value,
                onValueChange = { birthDate.value = it },
                label = { Text("Дата рождения") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val intent = Intent(context, AnalyzesActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = isButtonEnabled,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF1E88E5) else Color(0xFFE0E0E0)
                )
            ) {
                Text(text = "Создать")
            }
        }
    }
}