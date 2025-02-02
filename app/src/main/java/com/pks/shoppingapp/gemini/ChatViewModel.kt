package com.pks.shoppingapp.gemini

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.pks.shoppingapp.geminiApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor():ViewModel(){

    val geminiModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = geminiApi
    )
    val messagelist by lazy {
        mutableStateListOf<ChatModel>()
    }
    fun sendMessage(question:String){
        val chat = geminiModel.startChat()
        messagelist.add(ChatModel(text = question, role = "User"))
        viewModelScope.launch {
            val response = chat.sendMessage(question)
            messagelist.add(ChatModel(text = response.text.toString(), role = "Model"))
            Log.d("chat response", response.text.toString())
        }

    }
}