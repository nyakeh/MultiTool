package uk.co.nyakeh.multitool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.nyakeh.multitool.ui.theme.MultiToolTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiToolApp() {
                SavingRateScreen("34", listOf(srInput("ISA", 100, true), srInput("pension", 100, true)))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiToolApp(content: @Composable () -> Unit) {
    MultiToolTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Saving Rate", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)) },
            floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Filled.Add, "Add Input")
            } },
            content = { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.primaryContainer)) {
                content()
            }
            })
    }
}

@Composable
fun SavingRateScreen(rate: String, inputs: List<srInput>) {
    Column() {
        Row(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$rate",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "%",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = " Saving Rate",
                style = MaterialTheme.typography.displayMedium
            )
        }

        Box(){
           Column(){
               for (item in inputs){
                    Card(elevation = CardDefaults.elevatedCardElevation(),
                        colors = CardDefaults.elevatedCardColors(),
                        modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){
                            Text(text = "${item.name} : ${item.amount.toString()}")
                        }
                    }
               }
           }
        }

    }
}

data class srInput(val name: String, val amount: Int, val type: Boolean)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiToolApp() {
        SavingRateScreen("34", listOf(srInput("ISA", 100, true), srInput("pension", 100, true)))
    }
}