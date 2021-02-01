package uk.orth.qats.repository

import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.orth.qats.models.*
import uk.orth.qats.repository.utilities.EnumFactory
import javax.inject.Inject

class CatImagesService @Inject constructor() {
    private val retrofit: Retrofit
    private val api: TheCatApiAPI

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumFactory())
            .build()
        api = retrofit.create(TheCatApiAPI::class.java)
    }

    // https://github.com/Kotlin/kotlinx.coroutines/issues/706
    val allCategories = GlobalScope.async(Dispatchers.IO, start = CoroutineStart.LAZY) { api.getCategories() }

    suspend fun getCatImages(
        quantity: Int,
        quality: Quality = Quality.MEDIUM,
        order: Order = Order.ASCENDING,
        categories: List<Category> = emptyList(),
        imageTypes: List<ImageType> = listOf(ImageType.ANY)
    ): Result<List<CatImage>> {
        if (quantity !in 1..100) throw IllegalArgumentException("Can only 1 to 100 (inclusive) images.")
        val response = api.getImages(
            quantity,
            quality,
            order,
            imageTypes,
            categories.map { it.id })
        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(HttpException(response))
        }
    }

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/"
    }
}