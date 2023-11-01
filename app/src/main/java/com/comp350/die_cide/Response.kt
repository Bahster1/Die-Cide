/*
    * Copyright 2023 Taylor Asplund
*/

package com.comp350.die_cide

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.logging.LogLevel
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.LoggingConfig
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.runBlocking

class Response {
    fun response(question: String, diceResult: Int): String? = runBlocking {
        val apiKey = "INSERT API KEY HERE"
        val openAI = OpenAI(token = apiKey, logging = LoggingConfig(LogLevel.None))

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a helpful decision making assistant. Make a decision based on the first number provided, if the number is above 10 say yes, if it is below say no. Give a reason why without saying what the number provided was. Do not mention whether the number is above or below 10. Do not mention the number provided."
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "$diceResult $question"
                )
            )
        )
        return@runBlocking openAI.chatCompletion(chatCompletionRequest).choices.first().message.content
    }

}

