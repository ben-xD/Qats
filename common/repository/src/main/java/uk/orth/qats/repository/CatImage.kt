package uk.orth.qats.repository

import com.google.gson.annotations.SerializedName
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.net.URL

data class CatImage(
    val id: String,
    val imageType: ImageType,
    val url: URL,
    val categories: List<String>,
)

enum class HatsMode {
    NONE, ONLY, ANY
}

enum class ImageType {
    GIF, JPG, PNG, ANY
}

enum class Order(val value: String) {
    RANDOM("RANDOM"),
    ASCENDING("ASC"),
    DESCENDING("DESC"),
}

enum class Quality(val value: String) {
    THUMBNAIL("thumb"),
    SMALL("small"),
    MEDIUM("med"),
    ORIGINAL("full")
}

class EnumFactory : Converter.Factory() {
    /**
     * Modified from [here](https://medium.com/tedpark-developer/how-to-use-enum-with-retrofit-query-path-d9c14b93d68f)
     */
    override fun stringConverter(type: Type,
                                 annotations: Array<Annotation>,
                                 retrofit: Retrofit): Converter<Enum<*>, String>? {
         if (type is Class<*> && type.isEnum) {
            Converter<Enum<*>, String> { enum ->
                try {
                    return@Converter enum.javaClass.getField(enum.name).getAnnotation(SerializedName::class.java)?.value
                } catch(exception: Exception) {
                    return@Converter null
                }
            }
         }
        return null
    }
}

data class Category(val id: Int, val name: String)