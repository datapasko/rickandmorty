package es.smarting.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform