package com.example.recipeapp.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe (
    /*
    * Represent a recipe table in database
    */
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "recipe_name") var name: String? = null,
    @ColumnInfo(name = "recipe_author") var author: String? = null,
    @ColumnInfo(name = "recipe_steps_count") var stepsCount: Int? = null,
    @ColumnInfo(name = "recipe_image_url") var imageUrl: String? = null,
    @ColumnInfo(name = "recipe_time_needed") var timeNeeded: Int? = null,
    @ColumnInfo(name = "recipe_recipe_type_id") var recipeTypeId: Int?= null,
    @ColumnInfo(name = "recipe_ingredients") var ingredients: String? = null):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeValue(stepsCount)
        parcel.writeString(imageUrl)
        parcel.writeValue(timeNeeded)
        parcel.writeValue(recipeTypeId)
        parcel.writeString(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}