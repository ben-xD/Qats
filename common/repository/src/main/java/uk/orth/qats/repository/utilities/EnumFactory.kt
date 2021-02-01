package uk.orth.qats.repository.utilities

import com.google.gson.annotations.SerializedName
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnumFactory : Converter.Factory() {
    /**
     * Modified from [here](https://medium.com/tedpark-developer/how-to-use-enum-with-retrofit-query-path-d9c14b93d68f)
     */
    override fun stringConverter(type: Type,
                                 annotations: Array<Annotation>,
                                 retrofit: Retrofit
    ): Converter<Enum<*>, String>? {
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