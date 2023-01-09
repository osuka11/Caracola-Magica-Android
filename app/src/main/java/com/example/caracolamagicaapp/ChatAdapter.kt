package com.example.caracolamagicaapp

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.caracolamagicaapp.databinding.ChatListBinding
import java.lang.Boolean.FALSE
import java.util.jar.Pack200.Packer.FALSE


class ChatAdapter(val activity:MainActivity):ListAdapter<Message, ChatAdapter.ViewHolder>(DiffCallBack)
{
    companion object DiffCallBack: DiffUtil.ItemCallback<Message>(){
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.message == newItem.message
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatListBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

    inner class ViewHolder(val binding: ChatListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(message: Message){

            binding.messageText.text = message.message

            if(message.isResponse){
                //val layoutParams = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)

                //binding.messageText.textSize = 30.00f
                binding.root.background = getDrawable(activity,R.color.response)
                binding.messageText.gravity = Gravity.START

                //binding.messageText.layoutParams = layoutParams

            }else{
                //val layoutParams = RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
                //binding.messageText.textSize = 30.00f
                binding.messageText.gravity = Gravity.END
                binding.root.background = getDrawable(activity,R.color.answer)
                //binding.messageText.layoutParams = layoutParams



            }

            //binding.executePendingBindings()

        }
    }
}