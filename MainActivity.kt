package com.example.laboratoryexercise6

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.laboratoryexercise6.ui.theme.LaboratoryExercise6Theme

class MainActivity : ComponentActivity() {
    private var numberOne = 0.0
    private var numberTwo = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaboratoryExercise6Theme { Operators() }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Operators() {
        val context = LocalContext.current
        val paddingValue = 5.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row {
                    OperatorButtons(
                        "Add", modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), context
                    )
                    OperatorButtons(
                        "Subtract", modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), context
                    )
                }
                Row {
                    OperatorButtons(
                        "Multiply", modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), context
                    )
                    OperatorButtons(
                        "Divide", modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), context
                    )
                }
                Row {
                    NumberInputs(
                        modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), "NumberOne", context
                    )
                    NumberInputs(
                        modifier = Modifier
                            .weight(1f)
                            .padding(paddingValue), "NumberTwo", context
                    )
                }
            }
        }
    }

    @Composable
    fun NumberInputs(modifier: Modifier, field: String, context: Context) {
        var text = rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier,
            value = text.value,
            onValueChange = {
                text.value = it
                if (it.isNotEmpty() && it.isDigitsOnly()) {

                    when (field) {
                        "NumberOne" -> numberOne = it.toDouble()
                        "NumberTwo" -> numberTwo = it.toDouble()
                    }
                } else {
                    toastResult(context, "Field is empty or not a number!")
                    when (field) {
                        "NumberOne" -> numberOne = 0.0
                        "NumberTwo" -> numberTwo = 0.0
                    }
                }
            },
            label = { Text(text = "Enter a number") },
        )
    }

    @Composable
    fun OperatorButtons(operator: String, modifier: Modifier, context: Context) {
        Button(
            modifier = modifier,
            onClick = {

                when (operator) {
                    "Add" -> toastResult(context, (numberOne + numberTwo).toString())
                    "Subtract" -> toastResult(context, (numberOne - numberTwo).toString())
                    "Multiply" -> toastResult(context, (numberOne * numberTwo).toString())
                    "Divide" -> toastResult(context, (numberOne / numberTwo).toString())
                }

            }) { Text(operator) }
    }

    fun toastResult(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}