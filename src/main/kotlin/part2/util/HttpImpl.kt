package hu.pinter.beci.part2.util

import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class HttpImpl : Http {
    override fun get(url: String): String {
        try {
            val httpClient = HttpClient.newHttpClient()
            val httpRequestByUrl = HttpRequest.newBuilder().uri(URI.create(url)).build()
            val httpResponse = httpClient.send(httpRequestByUrl, BodyHandlers.ofString())
            return httpResponse.body()
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> {
                    throw IOException("Not supported I/O operation!")
                }

                is InterruptedException -> {
                    throw InterruptedException("Interrupted exception occurred!")
                }
            }

        }
        //default empty response
        return """
            { "address" : {
                    "house_number":"",
                    "road":"",
                    "city":"",
                    "state":"",
                    "postcode":"",
                    "country_code":""
            }
         }
        """
    }
}