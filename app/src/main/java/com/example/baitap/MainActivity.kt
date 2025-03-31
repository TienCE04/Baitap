package com.example.baitap

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baitap.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SinhVienAdapter
    private val studentList = mutableListOf<DataSinhVien>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        adapter = SinhVienAdapter(studentList) { position ->
            adapter.removeItem(position)
            Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
        }
        binding.dssinhvien.layoutManager = LinearLayoutManager(this)
        binding.dssinhvien.adapter = adapter
        binding.add.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add -> {
                val name = binding.name.text.toString().trim()
                val mssv = binding.mssv.text.toString().trim()

                when {
                    name.isEmpty() -> {
                        binding.name.error = "Vui lòng nhập họ tên"
                        return
                    }
                    mssv.isEmpty() -> {
                        binding.mssv.error = "Vui lòng nhập mã số sinh viên"
                        return
                    }
                    !mssv.matches(Regex("\\d+")) -> {
                        binding.mssv.error = "Mã số sinh viên phải là số"
                        return
                    }
                }

                val student = DataSinhVien(name, mssv.toInt())
                studentList.add(student)
                adapter.notifyItemInserted(studentList.size - 1)
                binding.dssinhvien.scrollToPosition(studentList.size - 1)

                binding.name.text.clear()
                binding.mssv.text.clear()
                Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
            }
        }
    }
}