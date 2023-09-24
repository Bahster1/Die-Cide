import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.logging.LogLevel
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.LoggingConfig
import com.aallam.openai.client.OpenAI
import kotlin.system.exitProcess

suspend fun main() {
    val apiKey = "INSERT API KEY HERE"
    val openAI = OpenAI(token = apiKey, logging = LoggingConfig(LogLevel.None))

    println("\n> Create chat completions...")
    val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-3.5-turbo"),
        messages = listOf(
            ChatMessage(
                role = ChatRole.System,
                content = "You are a helpful assistant that translates English to French."
            ),
            ChatMessage(
                role = ChatRole.User,
                content = "Translate the following English text to French: “OpenAI is awesome!”"
            )
        )
    )
    print(openAI.chatCompletion(chatCompletionRequest).choices.first().message.content)
    exitProcess(0)
}