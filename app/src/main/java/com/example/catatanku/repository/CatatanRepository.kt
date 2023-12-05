package com.example.catatanku.repository

import androidx.lifecycle.LiveData
import com.example.catatanku.database.CatatanDatabase
import com.example.catatanku.entities.Catatan

class CatatanRepository (
    val database:CatatanDatabase
) {
    fun getAllCatatan(): LiveData<List<Catatan>> = database.dao().getAllCatatan()
    fun getCatatanById(uid:Int):LiveData<Catatan> = database.dao().getCatatanById(uid)
    suspend fun insertData(data:Catatan) = database.dao().tambahCatatan(data)
    suspend fun updateData(data:Catatan) = database.dao().updateCatatan(data)
    suspend fun deleteData(id:Catatan) = database.dao().hapusCatatn(id)
}