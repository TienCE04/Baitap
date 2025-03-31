package com.example.baitap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baitap.databinding.ItemSinhVienBinding

class SinhVienAdapter(
    private val studentList: MutableList<DataSinhVien>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<SinhVienAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSinhVienBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: DataSinhVien, position: Int) {
            binding.htsinhvien.text = student.hoten
            binding.mssinhvien.text = student.maso.toString()
            binding.checkbox.isChecked = false // Reset checkbox state
            binding.checkbox.setOnClickListener {
                if (binding.checkbox.isChecked) {
                    onDelete(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSinhVienBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentList[position], position)
    }

    override fun getItemCount(): Int = studentList.size

    fun removeItem(position: Int) {
        studentList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, studentList.size)
    }
}