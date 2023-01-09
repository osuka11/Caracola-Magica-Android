package com.example.caracolamagicaapp

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //private val _answer: MutableLiveData<String> = MutableLiveData<String>()
    //val answer:LiveData<String> get() = _answer
    private val _conversationList: MutableLiveData<MutableList<Message>> = MutableLiveData<MutableList<Message>>(
        mutableListOf(Message(true,"Hola, soy la caracola magica"))
    )
    val conversationList :LiveData<MutableList<Message>> get() = _conversationList

    init {


    }

    companion object {
        val responses = listOf(
            "No lo sé",
            "Sí",
            "No lo creo",
            "No",
            "Problablemente",
            "Tal vez",
            "Sigue preguntando",
            "Ni lo sueñes",
            "¿Cómo?"
        )
    }

    fun makeResponse(answer: String){

        Log.d("MAIN ViewModel",answer)

        val messageUser = Message(false, answer)
        Log.d("MAIN VIEW MODEL",_conversationList.value!!.toList().toString())

        _conversationList.value!!.add(messageUser)


        val thread = Thread(
            Runnable {
                Thread.sleep(1000)
                val randIndexResponse = (Math.random()* responses.size).toInt()
                val messageApp = Message(true,responses[randIndexResponse] )
                _conversationList.value!!.add(messageApp)


            }
        )
        thread.start()






    }


}