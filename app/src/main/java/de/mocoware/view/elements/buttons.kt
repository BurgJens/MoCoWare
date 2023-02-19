package de.mocoware.view.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.size.Scale

@Composable
fun ButtonStandard(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun ButtonChooseGame(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.background
        ),
        border = BorderStroke(
            2.dp,
            MaterialTheme.colors.surface
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun GenericButton(offsetX: Dp, offsetY: Dp, text: String, color: Color, textColor: Color, rotation: Float, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .absoluteOffset(offsetX, offsetY)
            .rotate(rotation),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text = text, color = textColor)
    }
}
@Composable
fun GenericScaleButton(scale: Dp, text: String, color: Color, textColor: Color, onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(scale,scale),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text = text, color = textColor)
    }
}