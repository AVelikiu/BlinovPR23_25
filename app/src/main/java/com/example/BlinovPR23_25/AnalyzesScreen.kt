package com.example.BlinovPR23_25

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AnalyzesScreen(context: Context = LocalContext.current) {
    val searchQuery = remember { mutableStateOf("") }

    data class CardData(
        val title: String,
        val description: String,
        val price: String,
        val imageId: Int,
        val backgroundColor: Color
    )
    data class CardData2(
        val title: String,
        val duration: String,
        val price: String
    )

    val cardList = listOf(
        CardData("Чек-ап для\n мужчин", "9 исследований", "8000 ₽", R.drawable.card1, Color(0xFF69A9EF)),
        CardData("Подготовка к\n вакцинации", "Комплексное обследование\nперед вакцинацией", "4000 ₽", R.drawable.card2, Color(0xFF5DDABE))
    )
    val cardDataList = listOf(
        CardData2("ПЦР-тест на определение РНК\nкоронавируса стандартный", "2 дня", "1800₽"),
        CardData2("Клинический анализ крови с\n лейкоцитарной формулировкой", "1 день", "690₽"),
        CardData2("Биохимический анализ крови, базовый", "1 день", "2440₽"),
        CardData2("СОЭ (венозная кровь)", "1 день", "1800₽"),
        CardData2("Общий анализ мочи", "1 день", "1800₽"),
        CardData2("Тироксин свободный (Т4 свободный)", "1 день", "1800₽"),
        CardData2("Группа крови + Резус-фактор", "1 день", "1800₽"),
    )

    val filteredCardDataList = cardDataList.filter {
        it.title.contains(searchQuery.value, ignoreCase = true) ||
                it.duration.contains(searchQuery.value, ignoreCase = true) ||
                it.price.contains(searchQuery.value, ignoreCase = true)
    }

    val buttonList = listOf("Популярные", "Covid", "Комплексные")
    var selectedButtonIndex by remember { mutableStateOf(-1) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(context = context)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    placeholder = { Text("Искать анализы") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text("Акции и новости", color = Color.Gray, fontSize = 18.sp, modifier = Modifier.padding(top = 40.dp))
                Spacer(modifier = Modifier.height(25.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cardList) { cardData ->
                        Card(
                            modifier = Modifier
                                .width(260.dp)
                                .height(170.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(containerColor = cardData.backgroundColor)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = cardData.imageId),
                                    contentDescription = "Card Image",
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(200.dp)
                                        .align(Alignment.BottomEnd)
                                        .padding(start = 40.dp, top = 30.dp)
                                )
                                Column(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                                    Text(text = cardData.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                                    Text(modifier = Modifier.padding(top = 5.dp), text = cardData.description, color = Color.White, fontSize = 10.sp)
                                    Text(modifier = Modifier.padding(top = 5.dp), text = cardData.price, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Text("Каталог анализов", color = Color.Gray, fontSize = 18.sp, modifier = Modifier.padding(top = 20.dp))
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(buttonList) { index, buttonText ->
                        Button(
                            onClick = { selectedButtonIndex = index },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedButtonIndex == index) Color(0xFF1E88E5) else Color(0xFFE5E5E5),
                                contentColor = if (selectedButtonIndex == index) Color.White else Color.Gray
                            )
                        ) {
                            Text(text = buttonText)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    filteredCardDataList.forEach { cardData2 ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(120.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(containerColor = Color(0xF6FDFCFC))
                        ) {
                            Box(modifier = Modifier.padding(10.dp).fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                                Column {
                                    Text(text = cardData2.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(text = cardData2.duration, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = cardData2.price, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(context: Context) {
    NavigationBar {
        Constants.BottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.iconResId), contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = false,
                onClick = {
                    when (item.route) {
                        "Анализы" -> context.startActivity(Intent(context, AnalyzesActivity::class.java))
                        "Результаты" -> context.startActivity(Intent(context, ResultsActivity()::class.java))
                        "Поддержка" -> context.startActivity(Intent(context, SupportActivity::class.java))
                        "Профиль" -> context.startActivity(Intent(context, ProfileActivity::class.java))
                    }
                }
            )
        }
    }
}
