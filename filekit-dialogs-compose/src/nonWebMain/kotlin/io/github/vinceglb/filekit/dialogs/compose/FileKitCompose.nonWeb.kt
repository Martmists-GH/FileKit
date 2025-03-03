package io.github.vinceglb.filekit.dialogs.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.FileKitDialogSettings
import io.github.vinceglb.filekit.dialogs.deprecated.openFileSaver
import io.github.vinceglb.filekit.dialogs.openDirectoryPicker
import kotlinx.coroutines.launch

@Composable
public fun rememberDirectoryPickerLauncher(
    title: String? = null,
    directory: PlatformFile? = null,
    dialogSettings: FileKitDialogSettings = FileKitDialogSettings.createDefault(),
    onResult: (PlatformFile?) -> Unit,
): PickerResultLauncher {
    // Init FileKit
    InitFileKit()

    // Coroutine
    val coroutineScope = rememberCoroutineScope()

    // Updated state
    val currentTitle by rememberUpdatedState(title)
    val currentDirectory by rememberUpdatedState(directory)
    val currentOnResult by rememberUpdatedState(onResult)

    // FileKit launcher
    val returnedLauncher = remember {
        PickerResultLauncher {
            coroutineScope.launch {
                val result = FileKit.openDirectoryPicker(
                    title = currentTitle,
                    directory = currentDirectory,
                    dialogSettings = dialogSettings,
                )
                currentOnResult(result)
            }
        }
    }

    return returnedLauncher
}

@Composable
public actual fun rememberFileSaverLauncher(
    dialogSettings: FileKitDialogSettings,
    onResult: (PlatformFile?) -> Unit
): SaverResultLauncher {
    // Init FileKit
    InitFileKit()

    // Coroutine
    val coroutineScope = rememberCoroutineScope()

    // Updated state
    val currentOnResult by rememberUpdatedState(onResult)

    // FileKit launcher
    val returnedLauncher = remember {
        SaverResultLauncher { suggestedName, extension, directory, bytes ->
            coroutineScope.launch {
                val result = FileKit.openFileSaver(
                    bytes = bytes,
                    suggestedName = suggestedName,
                    extension = extension,
                    directory = directory,
                    dialogSettings = dialogSettings,
                )
                currentOnResult(result)
            }
        }
    }

    return returnedLauncher
}
