package es.smarting.rickmortyapp.di

import es.smarting.rickmortyapp.data.database.RickMortyDatabase
import es.smarting.rickmortyapp.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databaseModule()= module {
    single<RickMortyDatabase> { getDatabaseBuilder(get()) }
}