package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme


@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel = viewModel()) {


    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CalculationResultContainer(modifier = Modifier.weight(1f), display = state.value)
        KeymapContainer(modifier = Modifier.wrapContentHeight(), viewModel = viewModel)
    }

}

@Composable
fun CalculationResultContainer(modifier: Modifier = Modifier, display: CalculatorState) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            .padding(end = 20.dp, bottom = 16.dp, start = 20.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        when(display){
            is CalculatorState.Error -> {
                Text(
                    display.expression,
                    lineHeight = 36.sp,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold,


                    )
                Text(
                    "",
                    fontSize = 17.sp,
                    lineHeight = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,

                    modifier = Modifier

                        .padding(end = 8.dp)

                )
            }
            CalculatorState.Initial -> {

            }
            is CalculatorState.Input -> {
                Text(
                    display.expression,
                    lineHeight = 36.sp,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,


                    )
                Text(
                    display.result,
                    lineHeight = 17.sp,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,

                    modifier = Modifier

                        .padding(end = 8.dp)

                )
            }
            is CalculatorState.Success -> {
                Text(
                    display.result,
                    lineHeight = 36.sp,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,


                    )
                Text(
                    "",
                    lineHeight = 17.sp,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,

                    modifier = Modifier

                        .padding(end = 8.dp)

                )
            }
        }


    }

}

@Composable
fun KeymapContainer(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            SymbolButton(modifier.weight(1f), symbol = "√") {
                viewModel.processCommand(CalculatorCommand.Input(Symbol.SQRT))
            }
            SymbolButton(modifier.weight(1f), symbol = "π") {
                viewModel.processCommand(CalculatorCommand.Input(Symbol.PI))
            }
            SymbolButton(modifier.weight(1f), symbol = "^") {
                viewModel.processCommand(CalculatorCommand.Input(Symbol.POW))
            }
            SymbolButton(modifier.weight(1f), symbol = "!") {
                viewModel.processCommand(CalculatorCommand.Input(Symbol.FAC))
            }
        }
        TopRow(viewModel = viewModel)
        TopLetterRow(viewModel = viewModel)
        MiddleLetterRow(viewModel = viewModel)
        BottomLetterRow(viewModel = viewModel)
        BottomRow(viewModel = viewModel)
    }
}

@Composable
fun TopRow(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "AC",
            color = MaterialTheme.colorScheme.secondary
        ) {
            viewModel.processCommand(CalculatorCommand.Clear)
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "()",
            color = MaterialTheme.colorScheme.tertiary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.PAR))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "%",
            color = MaterialTheme.colorScheme.tertiary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.PER))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "÷",
            color = MaterialTheme.colorScheme.tertiary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIV))
        }

    }
}

@Composable
fun TopLetterRow(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "7",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "8",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "9",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "×",
            color = MaterialTheme.colorScheme.tertiary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.MUL))
        }

    }
}

@Composable
fun MiddleLetterRow(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "4",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "5",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "6",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "−",
            color = MaterialTheme.colorScheme.tertiary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.SUB))
        }

    }
}

@Composable
fun BottomLetterRow(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "1",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "2",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "3",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "+",
            color = MaterialTheme.colorScheme.tertiary,
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))
        }
    }
}

@Composable
fun BottomRow(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        LetterContainer(
            modifier = Modifier.weight(2f),
            letter = "0",
            aspectRatio = 2 / 1f,
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
        }

        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = ",",
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT))
        }
        LetterContainer(
            modifier = Modifier.weight(1f),
            letter = "=",
            color = MaterialTheme.colorScheme.tertiary,
        ) {
            viewModel.processCommand(CalculatorCommand.Evaluate)
        }
    }
}

@Composable
fun SymbolButton(modifier: Modifier = Modifier, symbol: String, onClick: () -> Unit) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(
            symbol,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LetterContainer(
    modifier: Modifier = Modifier,
    letter: String,
    color: Color,
    aspectRatio: Float = 1f,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .aspectRatio(aspectRatio)
            .background(color = color),
        contentAlignment = Alignment.Center

    ) {
        Text(letter, fontSize = 40.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalculatorPreview() {
    CalculatorTheme {
        Calculator()
    }
}