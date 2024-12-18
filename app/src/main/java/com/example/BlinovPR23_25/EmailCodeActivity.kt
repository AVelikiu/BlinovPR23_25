@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.BlinovPR23_25

import androidx.compose.material3.ExperimentalMaterial3Api
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
class EmailCodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailCodeScreen()
        }
    }
}
@Composable
fun EmailCodeScreen() {
    val context = LocalContext.current
    val code = remember { mutableStateListOf("", "", "", "") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 8.dp)
        ) {
            IconButton(onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Введите код из 4 цифр",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0 until 4) {
                    CodeInputField (
                        index = i,
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
                            }
                            if (code.all { it.length == 1 }) {
                                val intent = Intent(context, PasswordActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    )
                }
            }
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun CodeInputField(index: Int, code: String, onValueChange: (String) -> Unit) {
    TextField(
        value = code,
        onValueChange = onValueChange,
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = if (index < 3) ImeAction.Next else ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor =  Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp)
    )
}