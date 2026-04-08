package com.ubaya.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.studentproject.databinding.StudentListItemBinding
import com.ubaya.studentproject.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        holder.binding.txtStuID.text = studentList[position].id
        holder.binding.txtStuName.text = studentList[position].name
        holder.binding.btnDetail.setOnClickListener {
            val student = studentList[position]
            //ngirim data student ke hlmn detail student
            val action = StudentListFragmentDirections.actionStudentDetailFragment(student)
            it.findNavController().navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


    override fun getItemCount()= studentList.size

    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)


}
