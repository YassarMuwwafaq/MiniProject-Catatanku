package com.example.catatanku.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatanku.adapter.CatatanAdapter
import com.example.catatanku.database.CatatanDatabase
import com.example.catatanku.databinding.FragmentHomeBinding
import com.example.catatanku.repository.CatatanRepository
import com.example.catatanku.util.ViewModelFactory
import com.example.catatanku.viewmodel.CatatanViewModel

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var catatanAdapter : CatatanAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    private fun catatanViewModel(): CatatanViewModel {
        val database = CatatanDatabase(requireContext())
        val repo = CatatanRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[CatatanViewModel::class.java]
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyleView(catatanViewModel())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyleView(vm : CatatanViewModel) {
        val dataCatatan = vm.getAllCatatan()
        catatanAdapter = CatatanAdapter({
            val intent = Intent(context, DetailCatatanActivity::class.java)
            intent.putExtra("id", it.uid)
            startActivity(intent)
            activity?.finish()
        },
            onDelete = { catatan ->
                vm.hapusCatatan(catatan).let {
                    Toast.makeText(context,"Catatan dihapus",Toast.LENGTH_SHORT).show()
                }
            })
        binding?.rvCatatan?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = catatanAdapter
        }
        dataCatatan.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                catatanAdapter.submitList(data)
                binding?.rvCatatan?.visibility = View.VISIBLE
                binding?.tvKosong?.visibility = View.GONE
            } else {
                binding?.rvCatatan?.visibility = View.GONE
                binding?.tvKosong?.visibility = View.VISIBLE
            }
        }
    }
}