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

expect fun getTimeMillis(): Long
expect fun getStartup(): Long

fun main() {

    val current = getTimeMillis()

    val start = getStartup()

    println("jvm start = ${current - start}")

    val client = HttpClient()

    val randomNumUrl = "https://random-num-x5ht4amjia-uc.a.run.app/"
    val randomWordUrl = "https://random-word-x5ht4amjia-uc.a.run.app/"

    embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                val t = getTimeMillis()

                println("ms = ${t - start}")

                val num = client.get(randomNumUrl).bodyAsText().toInt()
                val words = List(num) {
                    async {
                        client.get(randomWordUrl).bodyAsText()
                    }
                }.awaitAll()
                call.respondText(words.joinToString(" "))
            }
        }
    }.also {
        val s = getTimeMillis()
        println("start = ${s - start}")
    }.start(wait = true).addShutdownHook {
        client.close()
    }

}