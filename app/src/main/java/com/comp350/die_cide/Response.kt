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
    fun getResponse(question: String, diceResult: Int): String? = runBlocking {
        val apiKey = "INSERT_API_KEY_HERE"
        val openAI = OpenAI(token = apiKey, logging = LoggingConfig(LogLevel.None))

        var systemMessage = ""

        when(diceResult) {
            1 -> systemMessage = "You are a helpful assistant that speaks like the Mad Hatter. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            2 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            3 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            4 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            5 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            6 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            7 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            8 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            9 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            10 -> systemMessage = "You are a helpful assistant that speaks like Shakespeare. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be no."
            11 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            12 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            13 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            14 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            15 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            16 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            17 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            18 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            19 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
            20 -> systemMessage = "You are a helpful assistant that speaks like Snoop Dog. You are required to make a decision and give a short reason to explain why you decided your decision. If the answer is a close-ended question, your decision  will be yes."
        }

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = systemMessage
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = question
                )
            )
        )
        return@runBlocking openAI.chatCompletion(chatCompletionRequest).choices.first().message.content
    }

}

