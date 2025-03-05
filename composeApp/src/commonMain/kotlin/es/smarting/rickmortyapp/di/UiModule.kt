package es.smarting.rickmortyapp.di

import es.smarting.rickmortyapp.ui.home.tabs.characters.CharactersViewModel
import es.smarting.rickmortyapp.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
}