package es.smarting.rickmortyapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(context: Context): RickMortyDatabase {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickMortyDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigration(true) // TODO only development
        //.fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}