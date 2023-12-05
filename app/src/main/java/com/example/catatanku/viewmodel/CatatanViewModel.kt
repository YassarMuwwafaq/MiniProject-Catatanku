package com.example.catatanku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catatanku.entities.Catatan
import com.example.catatanku.repository.CatatanRepository
import kotlinx.coroutines.launch

class CatatanViewModel(val catatanRepository : CatatanRepository) : ViewModel() {
    fun getAllCatatan() = catatanRepository.getAllCatatan()
    fun getCatatanById(uid:Int) = catatanRepository.getCatatanById(uid)
    fun tambahCatatan(data : Catatan) = viewModelScope.launch {
        catatanRepository.insertData(data)
    }
    fun updateCatatan(data : Catatan) = viewModelScope.launch {
        catatanRepository.updateData(data)
    }
    fun hapusCatatan(data : Catatan) = viewModelScope.launch {
        catatanRepository.deleteData(data)
    }

}