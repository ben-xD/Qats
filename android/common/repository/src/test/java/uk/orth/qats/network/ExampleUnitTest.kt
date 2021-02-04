package uk.orth.qats.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.orth.qats.repository.QuizAPI
import uk.orth.qats.repository.QuizService
import uk.orth.qats.repository.utilities.EnumFactory
import uk.orth.qats.repository.utilities.createResult

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = MediaType.parse("text/plain")
            val requestBody = RequestBody.create(mediaType, "TEST STRING")
            val retrofit = Retrofit.Builder()
                .baseUrl(QuizService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(EnumFactory())
                .build()
            val api = retrofit.create(QuizAPI::class.java)
            api.createQuiz(requestBody).createResult()
        }
    }
}