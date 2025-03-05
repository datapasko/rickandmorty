package es.smarting.rickmortyapp.di

import es.smarting.rickmortyapp.domain.usecase.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)
}