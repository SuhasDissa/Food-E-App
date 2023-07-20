package app.suhasdissa.foode.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.backend.models.NutritionTableData

@Composable
fun NutritionTable(table: NutritionTableData) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            table.heading.let { col ->
                Text(
                    col.first,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    col.second,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimary

                )
                Text(
                    col.third,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        table.data.forEach { row ->
            Row(Modifier.fillMaxWidth()) {
                row.let { col ->
                    Text(
                        col.first,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        col.second,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        col.third,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodySmall

                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TablePreview() {
    NutritionTable(
        table =
        NutritionTableData(
            heading = Triple("Nutritional Composition", "For 100g", "Per Serving"),
            data = listOf(
                Triple("Energy", "10000", "4000"),
                Triple("Carbs", "10000", "4000"),
                Triple("Vitamin", "10000", "4000")
            )
        )
    )
}
