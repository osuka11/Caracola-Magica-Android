package com.example.caracolamagicaapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caracolamagicaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //binding.root.background = getDrawable(R.color.response)

        val viewModel:MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val inputMessageText = binding.inputMessage
        val chatRecycler = binding.chatRecycler

        chatRecycler.layoutManager = LinearLayoutManager(this)

        val adapter = ChatAdapter(this)

        chatRecycler.adapter = adapter



        viewModel.conversationList.observe(this){
                conversationList ->

            adapter.submitList(conversationList)
            adapter.notifyDataSetChanged()


            if(conversationList.size > 2) chatRecycler.scrollToPosition(conversationList.size-1) else chatRecycler.scrollToPosition(conversationList.size)


        }

        binding.askQuestionButton.setOnClickListener {

            validationMessage( viewModel, inputMessageText)

        }





    }

    @SuppressLint("NotifyDataSetChanged")
    private fun validationMessage(
        viewModel: MainViewModel,
        inputMessageText: EditText
    ) {

        if(inputMessageText.text.isNullOrEmpty()){
            Toast.makeText(this, "Debes escribir una pregunta para iniciar la conversación",Toast.LENGTH_SHORT ).show()
            return
        }
        val answer = inputMessageText.text.toString().trim()

        Log.d("MAIN ACTIVITY",answer)

        viewModel.makeResponse(answer)



        inputMessageText.text.clear()

    }



}