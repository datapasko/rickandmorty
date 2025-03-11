package es.smarting.rickmortyapp.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RickMortyDatabase {
    val dbFile = NSHomeDirectory() + "/${DATABASE_NAME}"
    return Room.databaseBuilder<RickMortyDatabase>(
        name = dbFile,
    )
        .fallbackToDestructiveMigration(true) // TODO only development
        //.fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}