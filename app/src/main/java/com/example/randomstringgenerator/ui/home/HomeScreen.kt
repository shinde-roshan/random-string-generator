package com.example.randomstringgenerator.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomstringgenerator.R
import com.example.randomstringgenerator.data.model.RandomText
import com.example.randomstringgenerator.ui.theme.RandomStringGeneratorTheme

@Composable
fun TextRow(
    randomText: RandomText,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dp_4))
        ) {
            Column {
                Text(
                    text = randomText.value,
                    style = MaterialTheme.typography.titleLarge
                )
                Row{
                    Text(
                        text = stringResource(R.string.length_v, randomText.length),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.created_v, randomText.created),
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
