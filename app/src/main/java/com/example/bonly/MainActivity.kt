package com.example.bonly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bonly.domain.Bond
import com.example.bonly.presentation.CalculatorViewModel
import com.example.bonly.ui.theme.BonlyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BonlyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            InputTextField(
                name = "\uD83C\uDFE6 Номинал",
                value = state.value.nominal,
                onValueChange = viewModel::onNominalChanged,
                keyboardType = KeyboardType.Decimal
            )
        }
        item {
            InputTextField(
                name = "\uD83D\uDCC8 Текущая цена (%)",
                value = state.value.pricePercent,
                onValueChange = viewModel::onPercentChanged,
                keyboardType = KeyboardType.Decimal
            )
        }
        item {
            InputTextField(
                name = "\uD83D\uDCBC Размер купона (руб)",
                value = state.value.coupon,
                onValueChange = viewModel::onCouponChanged,
                keyboardType = KeyboardType.Decimal
            )
        }
        item {
            InputTextField(
                name = "\uD83D\uDD8A НКД (руб)",
                value = state.value.nkd,
                onValueChange = viewModel::onNkdChanged,
                keyboardType = KeyboardType.Decimal
            )
        }
        item {
            InputTextField(
                name = "⏳ Выплат в год",
                value = state.value.couponsPerYear,
                onValueChange = viewModel::onCouponsPerYearChanged,
                keyboardType = KeyboardType.Number
            )
        }
        item {
            InputTextField(
                name = "\uD83D\uDDD3 Дней до погашения",
                value = state.value.daysToMaturity,
                onValueChange = viewModel::onDaysToMaturityChanged,
                keyboardType = KeyboardType.Number
            )
        }
        item {
            Text(
                text = "Чистая доходность: ${String.format("%.2f", state.value.yieldResult)}%",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun InputTextField(
    name: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        label = { Text(text = name) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )
}
