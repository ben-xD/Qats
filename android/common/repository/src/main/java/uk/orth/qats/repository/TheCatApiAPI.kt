package uk.orth.qats.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uk.orth.qats.models.*

interface TheCatApiAPI {
    @GET("/categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("/images/search")
    @JvmSuppressWildcards
    suspend fun getImages(@Query("limit") quantity: Int,
                          @Query("size") quality: Quality,
                          @Query("order") order: Order,
                          @Query("mime_types") imageTypes: List<ImageType>,
                          @Query("category_ids") categoryIds: List<Int>
    ): Response<List<CatImage>>
}