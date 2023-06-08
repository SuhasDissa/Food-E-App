package app.suhasdissa.foode.backend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.suhasdissa.foode.utils.UpdateUtil
import kotlinx.coroutines.launch

class CheckUpdateViewModel : ViewModel() {
    var latestVersion: Float? by mutableStateOf(null)
    val currentVersion = UpdateUtil.currentVersion

    init {
        getLatestRelease()
    }

    private fun getLatestRelease() {
        viewModelScope.launch {
            latestVersion = UpdateUtil.getLatestVersion()
        }
    }
}
