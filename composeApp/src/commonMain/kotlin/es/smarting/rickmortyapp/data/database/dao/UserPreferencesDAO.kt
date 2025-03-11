package es.smarting.rickmortyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.smarting.rickmortyapp.data.database.entity.CharacterEntity

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM character")
    suspend fun getCharacters():CharacterEntity?

    @Insert(entity = CharacterEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(characterEntity: CharacterEntity)
}