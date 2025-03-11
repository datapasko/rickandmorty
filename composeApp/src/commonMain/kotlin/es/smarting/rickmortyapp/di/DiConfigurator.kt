package es.smarting.rickmortyapp.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

expect fun platformModule():Module

fun initKoin(configuration: KoinAppDeclaration? = null){
    startKoin {
        configuration?.invoke(this)
        modules(
            dataModule,
            domainModule,
            uiModule,
            platformModule(),
            databaseModule()
        )
    }
}