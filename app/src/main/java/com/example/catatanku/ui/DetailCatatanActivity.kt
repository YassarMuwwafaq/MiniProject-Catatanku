package com.example.catatanku.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.catatanku.MainActivity
import com.example.catatanku.database.CatatanDatabase
import com.example.catatanku.databinding.ActivityDetailCatatanBinding
import com.example.catatanku.entities.Catatan
import com.example.catatanku.repository.CatatanRepository
import com.example.catatanku.util.ViewModelFactory
import com.example.catatanku.viewmodel.CatatanViewModel

class DetailCatatanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCatatanBinding
    fun catatanViewModel(): CatatanViewModel {
        val database = CatatanDatabase(this)
        val repo = CatatanRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[CatatanViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tampilkan data by id
        val intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id", -1)
            catatanViewModel().getCatatanById(id).observe(this){ data ->
                data.let {
                    binding.etJudul.setText(it.judul)
                    binding.etDeskripsi.setText(it.deskripsi)
                }
            }

        }

        binding.imgBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.imgSave.setOnClickListener {
            if (intent!=null) {
                //update data
                updateCatatan(catatanViewModel())
            } else {
                //tambah data
                tambahCatatan(catatanViewModel())
            }
        }
    }
    fun tambahCatatan(vm : CatatanViewModel) {
        vm.tambahCatatan(Catatan(
            null,
            judul = binding.etJudul.text.toString(),
            deskripsi = binding.etDeskripsi.text.toString()
        )).let {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    fun updateCatatan(vm:CatatanViewModel) {
        val intent = intent.extras
        vm.updateCatatan(
            Catatan(
                intent?.getInt("id", -1),
                judul = binding.etJudul.text.toString(),
                deskripsi = binding.etDeskripsi.text.toString()
            )
        ).let {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}