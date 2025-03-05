package es.smarting.rickmortyapp.di

import es.smarting.rickmortyapp.data.RepositoryImpl
import es.smarting.rickmortyapp.data.remote.ApiService
import es.smarting.rickmortyapp.data.remote.paging.CharacterPagingSource
import es.smarting.rickmortyapp.domain.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation){
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url{
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com/api/"
                    //parameters.append("key", "")
                }
            }
        }
    }

    factoryOf(::ApiService)
    factoryOf(::RepositoryImpl).bind(Repository::class)
    //factory <Repository> { RepositoryImpl(get())  }

    factoryOf(::CharacterPagingSource)
}