package es.smarting.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import es.smarting.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController (configure = { initKoin() }){ App() }