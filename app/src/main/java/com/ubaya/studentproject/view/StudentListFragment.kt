package com.ubaya.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.studentproject.R
import com.ubaya.studentproject.databinding.FragmentStudentDetailBinding
import com.ubaya.studentproject.databinding.FragmentStudentListBinding
import com.ubaya.studentproject.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter  = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//initiate view model
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
//initiate rec view
        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = studentListAdapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        //observe student list
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
            binding.swipeRefresh.isRefreshing = false
        })

        //observe error
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it) binding.txtError.visibility = View.VISIBLE
            else binding.txtError.visibility = View.GONE
        })

        //observe loading
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it) binding.progressLoad.visibility = View.VISIBLE
            else binding.progressLoad.visibility = View.GONE
        })
    }


}