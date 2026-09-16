package com.example.bonly.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bonly.R
import com.example.bonly.presentation.CalculatorViewModel

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    var showBuyDatePicker by remember { mutableStateOf(false) }
    var showMaturityDatePicker by remember { mutableStateOf(false) }
    var tempBuyDateMillis by remember { mutableStateOf<Long?>(null) }

    if (showBuyDatePicker) {
        DatePickerModal(
            title = "Выберите дату покупки",
            onDateSelected = { selectedMillis ->
                tempBuyDateMillis = selectedMillis
                showBuyDatePicker = false
                showMaturityDatePicker = true
            },
            onDismiss = { showBuyDatePicker = false }
        )
    }

    if (showMaturityDatePicker) {
        DatePickerModal(
            title = "Выберите дату погашения",
            onDateSelected = { maturityMillis ->
                val buyMillis = tempBuyDateMillis
                if (buyMillis != null) {
                    viewModel.onDatesSelected(
                        buyDateMillis = buyMillis,
                        maturityDateMillis = maturityMillis
                    )
                }
                showMaturityDatePicker = false
            },
            onDismiss = { showMaturityDatePicker = false }
        )
    }



    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(Modifier.padding(6.dp)) {
                    Text(
                        "Параметры облигации",
                        modifier = Modifier.padding(6.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row() {
                            InputTextField(
                                name = "\uD83C\uDFE6 Номинал",
                                value = state.value.nominal,
                                onValueChange = viewModel::onNominalChanged,
                                keyboardType = KeyboardType.Decimal,
                                modifier = Modifier.fillMaxWidth(0.5f),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.currency_ruble_24px),
                                        modifier = Modifier.alpha(0.5f),
                                        contentDescription = null
                                    )
                                }
                            )
                            InputTextField(
                                name = "\uD83D\uDCC8 Текущая цена",
                                value = state.value.pricePercent,
                                onValueChange = viewModel::onPercentChanged,
                                keyboardType = KeyboardType.Decimal,
                                modifier = Modifier.fillMaxWidth(1f),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.percent_24px),
                                        modifier = Modifier.alpha(0.5f),
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                        InputTextField(
                            name = "\uD83D\uDD8A НКД",
                            value = state.value.nkd,
                            onValueChange = viewModel::onNkdChanged,
                            keyboardType = KeyboardType.Decimal,
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.currency_ruble_24px),
                                    modifier = Modifier.alpha(0.5f),
                                    contentDescription = null
                                )
                            }
                        )
                        Row() {
                            InputTextField(
                                name = "\uD83D\uDCBC Размер купона",
                                value = state.value.coupon,
                                onValueChange = viewModel::onCouponChanged,
                                keyboardType = KeyboardType.Decimal,
                                modifier = Modifier.fillMaxWidth(0.5f),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.currency_ruble_24px),
                                        modifier = Modifier.alpha(0.5f),
                                        contentDescription = null
                                    )
                                }
                            )
                            InputTextField(
                                name = "⏳ Выплат в год",
                                value = state.value.couponsPerYear,
                                onValueChange = viewModel::onCouponsPerYearChanged,
                                keyboardType = KeyboardType.Number,
                                modifier = Modifier.fillMaxWidth(1f),
                                trailingIcon = null
                            )
                        }
                        InputTextField(
                            name = "Дней до погашения",
                            value = state.value.daysToMaturity,
                            onValueChange = viewModel::onDaysToMaturityChanged,
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { showBuyDatePicker = true }) {
                                    Icon(
                                        painter = painterResource(R.drawable.calendar_month_24px),
                                        contentDescription = "Выбрать даты"
                                    )
                                }
                            }
                        )
                        TextField(
                            shape = RoundedCornerShape(28.dp),
                            value = "Используется ставка НДФЛ 13%",
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 14.sp
                            ),
                            onValueChange = {},
                            enabled = false,
                            leadingIcon = {
                                Icon(painter = painterResource(R.drawable.info_24px), null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
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
            ResCard(state)
        }
    }
}

@Composable
fun InputTextField(
    name: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        trailingIcon = trailingIcon,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        label = { Text(text = name) },
        modifier = modifier
            .padding(6.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    title: String,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { selectedMillis ->
                    onDateSelected(selectedMillis)
                }
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = {
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 24.dp, top = 16.dp)
                )
            }
        )
    }
}
