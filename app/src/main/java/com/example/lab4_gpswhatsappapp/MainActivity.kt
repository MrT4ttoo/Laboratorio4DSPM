package com.example.lab4_gpswhatsappapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.lab4_gpswhatsappapp.ui.theme.Lab4_GpsWhatsappAppTheme
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4_GpsWhatsappAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val latitude = remember { mutableStateOf("0.0") }
    val longitude = remember { mutableStateOf("0.0") }
    val snackbarHostState = remember { SnackbarHostState() }
    var showDeniedMessage by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    latitude.value = it.latitude.toString()
                    longitude.value = it.longitude.toString()
                }
            }
        } else {
            showDeniedMessage = true
        }
    }

    // Mostrar snackbar si fue denegado
    LaunchedEffect(showDeniedMessage) {
        if (showDeniedMessage) {
            snackbarHostState.showSnackbar("Permiso de ubicación denegado.")
            showDeniedMessage = false
        }
    }

    // Pedir permiso automáticamente
    LaunchedEffect(Unit) {
        val permissionCheck = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    latitude.value = it.latitude.toString()
                    longitude.value = it.longitude.toString()
                }
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        AppUI(
            latitude = latitude.value,
            longitude = longitude.value,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun AppUI(latitude: String, longitude: String, modifier: Modifier = Modifier) {
    var phone by remember { mutableStateOf("+507") }
    var message by remember { mutableStateOf("Estoy perdido, por favor encuéntrenme") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Push Notification Service", color = Color.Gray)
        Text("Lat.: $latitude  Lon.: $longitude")

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "Persona",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Mensaje") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val mapsUrl = "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
                val fullMessage = "$message\nMi ubicación es: $mapsUrl"
                val url = "https://wa.me/${phone}?text=${Uri.encode(fullMessage)}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar a WhatsApp")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAppUI() {
    Lab4_GpsWhatsappAppTheme {
        AppUI(latitude = "8.98", longitude = "-79.52")
    }
}
