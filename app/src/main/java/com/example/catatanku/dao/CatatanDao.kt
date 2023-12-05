package com.example.catatanku.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.catatanku.entities.Catatan

@Dao
interface CatatanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun tambahCatatan(data : Catatan)

    @Update()
    suspend fun updateCatatan(data : Catatan)

    @Delete()
    suspend fun hapusCatatn(data : Catatan)

    @Query("SELECT * FROM tb_catatan")
    fun getAllCatatan():LiveData<List<Catatan>>

    @Query("SELECT * FROM tb_catatan WHERE uid = :uid")
    fun getCatatanById(uid:Int):LiveData<Catatan>
}