package com.example.catatanku.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catatanku.repository.CatatanRepository

class ViewModelFactory( private val catatanRepository : CatatanRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(CatatanRepository::class.java)
            return constructor.newInstance(catatanRepository)
        } catch (e:Exception) {

        }
        return super.create(modelClass)
    }
}