package com.example.BlinovPR23_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SupportActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupportScreen()
        }
    }
}