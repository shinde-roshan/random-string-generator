package com.example.randomstringgenerator.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomstringgenerator.R
import com.example.randomstringgenerator.data.model.RandomText
import com.example.randomstringgenerator.ui.theme.RandomStringGeneratorTheme
import com.example.randomstringgenerator.utils.resDateToDisplayDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory)
) {
    var inputLength by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_16)),
            modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
        ) {
            OutlinedTextField(
                value = inputLength,
                onValueChange = { inputLength = it },
                label = { Text(stringResource(R.string.enter_string_length)) },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    val length = inputLength.toIntOrNull()
                    if (length != null && length > 0) {
                        viewModel.fetchRandomText(length)
                    }
                },
                modifier = Modifier
            ) {
                Text(text = stringResource(R.string.generate))
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_16)))

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(dimensionResource(R.dimen.dp_16)))
        }

        uiState.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
            )
        }

        if (uiState.history.isNotEmpty()) {
            Results(
                history = uiState.history,
                onCleared = { viewModel.clearRecent() }
            )
        }

    }
}

@Composable
fun Results(
    history: List<RandomText>,
    onCleared: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dp_8))
        ) {
            Text(
                text = stringResource(R.string.results),
                style = MaterialTheme.typography.displaySmall
            )
            OutlinedButton (
                onClick = onCleared
            ) {
                Text(text = stringResource(R.string.clear))
            }

        }

        LazyColumn {
            items(history) { item ->
                TextRow(
                    randomText = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.dp_8))
                )
            }
        }

    }
}

@Composable
fun TextRow(
    randomText: RandomText,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dp_16))
        ) {
            Column {
                Text(
                    text = randomText.value,
                    style = MaterialTheme.typography.titleLarge
                )
                Row {
                    Text(
                        text = stringResource(R.string.length_v, randomText.length),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.created_v, resDateToDisplayDate(randomText.created)),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextRow() {
    RandomStringGeneratorTheme {
        TextRow(
            RandomText("abcd", 4, "today")
        )
    }
}
