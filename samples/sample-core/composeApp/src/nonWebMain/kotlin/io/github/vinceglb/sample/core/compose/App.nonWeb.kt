package io.github.vinceglb.sample.core.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import io.github.vinceglb.filekit.core.PlatformFile
import io.github.vinceglb.filekit.core.path

@Composable
actual fun PickDirectoryButton(
    directory: PlatformFile?,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClick) {
            Text("Directory picker")
        }

        Text("Selected directory: ${directory?.path ?: "None"}")
    }
}
