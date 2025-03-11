package es.smarting.rickmortyapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import es.smarting.rickmortyapp.data.database.dao.UserPreferencesDAO
import es.smarting.rickmortyapp.data.database.entity.CharacterEntity

const val DATABASE_NAME = "rm_app_database.db"

//expect object RickMortyCTor:RoomDatabaseConstructor<RickMortyDatabase>

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RickMortyDatabase: RoomDatabase() {
    abstract fun getPreferencesDAO(): UserPreferencesDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RickMortyDatabase> {
    override fun initialize(): RickMortyDatabase
}