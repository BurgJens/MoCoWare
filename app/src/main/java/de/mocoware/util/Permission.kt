package com.example.testrobert

import android.graphics.Color
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import de.mocoware.view.elements.ButtonStandard
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@Composable
fun Permission(
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {

    val permissionStateCamera = rememberPermissionState(android.Manifest.permission.CAMERA)
    val permissionLocation = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)


    PermissionRequired(
        permissionState = permissionStateCamera,
        permissionNotGrantedContent = {
            Rationale(
                titel = "Zum spielen Kamera freigeben!",
                text = "In einigen Spielen musst du etwas fotografieren, schalte deswegen deine Kamera frei!",
                onRequestPermission = { permissionStateCamera.launchPermissionRequest() }
            ) },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = content
    )

    PermissionRequired(
        permissionState = permissionLocation,
        permissionNotGrantedContent = {
            Rationale(
                titel = "Zum spielen Standort freigeben!",
                text = "In einigen Spielen muss deine Geschwindigkeit berechnet werden, dies geht nur über deinen Standort!",
                onRequestPermission = { permissionLocation.launchPermissionRequest() }
            ) },
        permissionNotAvailableContent = permissionNotAvailableContent,
        content = content
    )

}





@Composable
private fun Rationale(
    titel:String,
    text: String,
    onRequestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /* Don't */ },
        title = {
            Text(text = titel)
        },
        text = {
            Text(text)
        },
        confirmButton = {
            ButtonStandard(
                text = "OK",
                modifier = Modifier,
                onClick =onRequestPermission)
        }
    )
}
