package com.example.recipeplanner.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert

@Entity
data class RecipeDataObject(
    @PrimaryKey(autoGenerate = true) val rid: Int,
    @ColumnInfo(name = "title") val title: String
){
    constructor(title: String) : this(0,title)
}

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipedataobject")
    suspend fun getAll(): List<RecipeDataObject>

    @Upsert
    suspend fun insertRecipe(recipe: RecipeDataObject)
}