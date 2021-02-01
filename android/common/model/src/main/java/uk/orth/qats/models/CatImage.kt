package uk.orth.qats.models

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

data class Category(val id: Int, val name: String)