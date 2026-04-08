package com.ubaya.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.studentproject.model.Student

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?=null

    fun refresh(){
        //nti isi voley
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"
        val stringRequest = StringRequest(Request.Method.GET, url, {
            //volley success
            loadingLD.value = false
            val sType = object: TypeToken<List<Student>>(){}.type
            val result = Gson().fromJson<List<Student>>(it,sType)
            studentsLD.value=result as ArrayList<Student>?

        },{
            //volley error
            loadingLD.value =false
            studentLoadErrorLD.value = true
            Log.d("showcoley", it.toString())

        })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared(){
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}