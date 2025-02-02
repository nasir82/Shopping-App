package com.pks.shoppingapp.gemini

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GeminiChatScreen(modifier: Modifier = Modifier) {
    val question = remember {
        mutableStateOf("")
    }
    val chatViewModel:ChatViewModel = hiltViewModel()
   Scaffold(
       topBar = {
           Row(modifier = Modifier.padding(top = 40.dp, start = 10.dp, end = 10.dp)) {
               Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
               Spacer(modifier = Modifier.width(10.dp))
               Text(text = "Ask to gemini")
           }
       }
   ) {
       inPad->
       Box(modifier = Modifier
           .fillMaxSize()
           .padding(inPad)) {
           Column(modifier = Modifier
               .fillMaxSize()
               .padding(horizontal = 10.dp, vertical = 5.dp), verticalArrangement = Arrangement.Bottom) {
               
               LazyColumn(modifier = Modifier.weight(1f), reverseLayout = true) {
                   items(chatViewModel.messagelist.reversed()){
                       Text(text = it.text)
                   }
               }
               Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                   OutlinedTextField(value = question.value, onValueChange = {
                                                                             question.value = it
                   },modifier = Modifier.weight(
                       .8f
                   ),
                       label = {
                           Text(text = "What do you want?")
                       })
                   IconButton(onClick = {
                       chatViewModel.sendMessage(question.value)
                       question.value = ""
                   }) {
                       Icon(imageVector = Icons.Default.Send, contentDescription = "",modifier = Modifier.size(32.dp))
                   }

               }

           }
       }

   }
}