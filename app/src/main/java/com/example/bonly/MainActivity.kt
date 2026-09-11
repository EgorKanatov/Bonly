package com.example.bonly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Card(Modifier.fillMaxWidth().padding(6.dp)) {
                Column(Modifier.padding(6.dp)) {
                    Text("Параметры облигации", modifier = Modifier.padding(6.dp), fontSize = 16.sp)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row() {
                            InputTextField(
                                name = "\uD83C\uDFE6 Номинал",
                                value = state.value.nominal,
                                onValueChange = viewModel::onNominalChanged,
                                keyboardType = KeyboardType.Decimal,
                                modifier = Modifier.fillMaxWidth(0.5f)

                            )
                            InputTextField(
                                name = "\uD83D\uDCC8 Текущая цена (%)",
                                value = state.value.pricePercent,
                                onValueChange = viewModel::onPercentChanged,
                                keyboardType = KeyboardType.Decimal,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        InputTextField(
                            name = "\uD83D\uDCBC Размер купона (руб)",
                            value = state.value.coupon,
                            onValueChange = viewModel::onCouponChanged,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth()

                        )
                        InputTextField(
                            name = "\uD83D\uDD8A НКД (руб)",
                            value = state.value.nkd,
                            onValueChange = viewModel::onNkdChanged,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth()

                        )
                        InputTextField(
                            name = "⏳ Выплат в год",
                            value = state.value.couponsPerYear,
                            onValueChange = viewModel::onCouponsPerYearChanged,
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.fillMaxWidth()

                        )
                        InputTextField(
                            name = "\uD83D\uDDD3 Дней до погашения",
                            value = state.value.daysToMaturity,
                            onValueChange = viewModel::onDaysToMaturityChanged,
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.fillMaxWidth()
                        )
                        TextField(
                            value = "Используется ставка НДФЛ 13%",
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 12.sp
                            ),
                            onValueChange = {},
                            enabled = false,
                            leadingIcon = {
                                Icon(painter = painterResource(R.drawable.info_24px), null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent,
                            )
                        )
                    }
                }
            }
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
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        label = { Text(text = name) },
        modifier = modifier
            .padding(6.dp)
    )
}
