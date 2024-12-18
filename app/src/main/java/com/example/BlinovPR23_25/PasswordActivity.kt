package com.example.BlinovPR23_25
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

class PasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                PasswordScreenContent()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreenContent() {
    val code = remember { mutableStateListOf("", "", "", "") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Пропустить",
            color = Color(0xFF42A5F5),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    val intent = Intent(context, PacientCardAcitivity::class.java)
                    context.startActivity(intent)
                }
                .padding(16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Создайте пароль",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Для защиты ваших персональных данных",
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until 4) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = if (code[i].isNotEmpty()) Color.Blue else Color.LightGray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0 until 4) {
                    CodeInputField(
                        code = code[i],
                        onValueChange = { value ->
                            if (value.length <= 1 && value.all { it.isDigit() }) {
                                code[i] = value
                                if (value.isNotEmpty()) {
                                    if (i < 3) {
                                        focusManager.moveFocus(FocusDirection.Next)
                                    } else {
                                        focusManager.clearFocus()
                                    }
                                }
                                if (code.all { it.isNotEmpty() }) {
                                    val intent = Intent(context,PacientCardAcitivity::class.java)
                                    context.startActivity(intent)
                                }
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeInputField(code: String, onValueChange: (String) -> Unit) {
    TextField(
        value = code,
        onValueChange = onValueChange,
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, // Стандартная цифровая клавиатура
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF5F5F5) // Серый фон, как вы хотели
        ),
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp)
    )
}

