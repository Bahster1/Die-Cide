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
        val apiKey = "INSERT API KEY HERE"
        val openAI = OpenAI(token = apiKey, logging = LoggingConfig(LogLevel.None))

        var systemMessage = ""

        when(diceResult) {
            1 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a sarcastic tone. If the answer is a close-ended question, your decision  will be no."
            2 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a sarcastic tone. If the answer is a close-ended question, your decision  will be no."
            3 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a sarcastic tone. If the answer is a close-ended question, your decision  will be no."
            4 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a pessimistic tone. If the answer is a close-ended question, your decision  will be no."
            5 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a pessimistic tone. If the answer is a close-ended question, your decision  will be no."
            6 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a pessimistic tone. If the answer is a close-ended question, your decision  will be no."
            7 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a critical tone. If the answer is a close-ended question, your decision  will be no."
            8 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a critical tone. If the answer is a close-ended question, your decision  will be no."
            9 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a formal tone. If the answer is a close-ended question, your decision  will be no."
            10 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a formal tone. If the answer is a close-ended question, your decision  will be no."
            11 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a formal tone. If the answer is a close-ended question, your decision  will be yes."
            12 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a friendly tone. If the answer is a close-ended question, your decision  will be yes."
            13 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a friendly tone. If the answer is a close-ended question, your decision  will be yes."
            14 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a friendly tone. If the answer is a close-ended question, your decision  will be yes."
            15 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a optimistic tone. If the answer is a close-ended question, your decision  will be yes."
            16 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a optimistic tone. If the answer is a close-ended question, your decision  will be yes."
            17 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Respond in a optimistic tone. If the answer is a close-ended question, your decision  will be yes."
            18 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Be a hype man in your response. If the answer is a close-ended question, your decision  will be yes."
            19 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Be a hype man in your response. If the answer is a close-ended question, your decision  will be yes."
            20 -> systemMessage = "You are a helpful assistant. You are required to make a decision and give a short reason to explain why you decided your decision. Do not give a preamble to your decision reasoning. Say your decision and reason together. Be a hype man in your response. If the answer is a close-ended question, your decision  will be yes."
        }

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-4-1106-preview"),
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

