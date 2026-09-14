package com.example.bonly.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonly.R
import com.example.bonly.presentation.CalculatorState
import com.example.bonly.ui.theme.LightGreen
import com.example.bonly.ui.theme.LightRed
import com.example.bonly.ui.theme.NGreen
import com.example.bonly.ui.theme.NRed

@Composable
fun ResCard(state: State<CalculatorState>) {

    val cardColor = if (state.value.yieldResult > 0) {
        LightGreen
    } else if (state.value.yieldResult < 0) {
        LightRed
    } else {
        Color.LightGray
    }

    val textColor = if (state.value.yieldResult > 0) {
        NGreen
    } else if (state.value.yieldResult < 0) {
        NRed
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Card(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier
            .fillMaxSize()
            .padding(6.dp)) {
            Text(
                "Результаты расчета",
                modifier = Modifier.padding(6.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Row(Modifier.fillMaxSize()) {
                    IconButton(
                        enabled = false,
                        onClick = {},
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(0.15f),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bar_chart_24px),
                            null,
                            modifier = Modifier.fillMaxSize(),
                            )
                    }

                    Column() {
                        Text("Доходность к погашению: ", Modifier.padding(6.dp))
                        Text(
                            text = "${
                                String.format(
                                    "%.2f",
                                    state.value.yieldResult
                                )
                            }%",
                            modifier = Modifier.padding(6.dp),
                            fontSize = 28.sp,
                            color = textColor
                        )
                    }
                }

            }
        }

    }
}
