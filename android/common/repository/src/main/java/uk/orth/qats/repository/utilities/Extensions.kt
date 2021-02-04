package uk.orth.qats.repository.utilities

import retrofit2.HttpException
import retrofit2.Response
import uk.orth.qats.repository.Result

// A helper extension property to allow `when` to be exhaustive.
val <T> T.exhaustive: T
    get() = this

// Converting from Retrofit Response to Qats Result
fun <T : Any> Response<T>.createResult(): Result<T> {
    return if (this.isSuccessful) {
        Result.Success(this.body()!!)
    } else {
        Result.Error(HttpException(this))
    }
}

fun Exception.createResult(): Result.Error {
    return Result.Error(this)
}