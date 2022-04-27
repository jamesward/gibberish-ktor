import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

fun main() {
    val client = HttpClient()

    val randomNumUrl = "https://random-num-x5ht4amjia-uc.a.run.app/"
    val randomWordUrl = "https://random-word-x5ht4amjia-uc.a.run.app/"

    embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                val num = client.get(randomNumUrl).bodyAsText().toInt()
                val words = List(num) {
                    async {
                        client.get(randomWordUrl).bodyAsText()
                    }
                }.awaitAll()
                call.respondText(words.joinToString(" "))
            }
        }
    }.start(wait = true).addShutdownHook {
        client.close()
    }

}