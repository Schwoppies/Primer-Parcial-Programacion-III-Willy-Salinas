package com.example.libreria

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ItemCatalogoUi(
    val id: String,
    val tipo: String,
    val titulo: String,
    val precio: Double,
    val detalleExtra: String,
    val disponible: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    itemsCatalogo: List<ItemCatalogoUi>,
    onSeleccionarItem: (ItemCatalogoUi) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catalogo Libreria") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(itemsCatalogo, key = { it.id }) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "${item.tipo} - ${item.titulo}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Precio: Bs. ${item.precio}")
                        Text(text = item.detalleExtra)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = { onSeleccionarItem(item) }) {
                                Text("Ver detalle")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProductoScreen(
    itemInicial: ItemCatalogoUi,
    onVolver: () -> Unit
) {
    var disponible by remember(itemInicial.id) { mutableStateOf(itemInicial.disponible) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle de ${itemInicial.tipo}") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = itemInicial.titulo,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Categoria: ${itemInicial.tipo}")
                    Text(text = "Precio: Bs. ${itemInicial.precio}")
                    Text(text = itemInicial.detalleExtra)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text(
                        text = if (disponible) "Estado: Disponible" else "Estado: Agotado"
                    )
                }
            }

            Button(
                onClick = { disponible = !disponible },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (disponible) "Marcar como agotado" else "Marcar como disponible")
            }

            OutlinedButton(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al catalogo")
            }
        }
    }
}
