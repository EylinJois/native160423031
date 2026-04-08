package com.ubaya.studentproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.studentproject.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD= MutableLiveData<Student>()
    val TAG:String = "Volley Tag"
    var queue: RequestQueue?=null

    fun fetch(student: Student){
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        queue= Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"
        val stringreq = StringRequest(Request.Method.GET, url, {},{})
        stringreq.tag = TAG
        queue?.add(stringreq)

        studentLD.value = student
    }
}