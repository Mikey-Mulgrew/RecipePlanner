package com.example.recipeplanner.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Upsert
import com.example.recipeplanner.data.IngredientUnits
import kotlinx.serialization.Serializable

@Entity
data class RecipeDataObject(
    @PrimaryKey(autoGenerate = true) val rid: Int,
    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "ingredients")
    @TypeConverters(IngredientsConverter::class)
    val ingredients: List<IngredientDataObject>? = null,

    @ColumnInfo(name = "instructions")
    @TypeConverters(InstructionsConverter::class)
    val instructions: List<String>? = null
) {
    constructor(
        title: String,
        ingredients: List<IngredientDataObject>?,
        instructions: List<String>?
    ) : this(0, title, ingredients, instructions)
}

@Serializable
@Entity
data class IngredientDataObject(
    @PrimaryKey(autoGenerate = true) val iid: Int,
    val ingredient: String,
    val amount: Float,
    val unit: IngredientUnits
) {
    constructor(ingredient: String, amount: Float, unit: IngredientUnits) : this(
        0,
        ingredient,
        amount,
        unit
    )
}

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipedataobject")
    suspend fun getRecipeWithIngredients(): List<RecipeDataObject>

    @Upsert
    suspend fun insertRecipe(recipe: RecipeDataObject)

    @Query("DELETE FROM recipedataobject WHERE rid = :recipeId")
    suspend fun deleteByRecipeId(recipeId: Int)
}