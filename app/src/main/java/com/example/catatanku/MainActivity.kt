package com.example.catatanku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.catatanku.databinding.ActivityMainBinding
import com.example.catatanku.ui.DetailCatatanActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.FragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)

        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity, DetailCatatanActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}