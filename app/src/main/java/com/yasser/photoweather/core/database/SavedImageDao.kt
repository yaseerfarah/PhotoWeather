package com.yasser.photoweather.core.database

import androidx.room.*


@Dao
interface SavedImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: SavedImageDto): Long

    @Query("SELECT * FROM $SavedImage_TableName  ORDER BY  createdAt ASC")
    suspend fun selectAllSavedImages(): List<SavedImageDto>





}