package com.dev.mahmoud_ashraf.marvelapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
data class CharactersDetailsResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String? = "",
    @SerializedName("attributionText")
    val attributionText: String? = "",
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("status")
    val status: String? = ""
) {

    data class Data(
        @SerializedName("count")
        val count: Int? = 0,
        @SerializedName("limit")
        val limit: Int? = 0,
        @SerializedName("offset")
        val offset: Int? = 0,
        @SerializedName("results")
        val results: List<Result?>? = listOf(),
        @SerializedName("total")
        val total: Int? = 0
    )

    data class Result(
        @SerializedName("characters")
        val characters: Characters? = Characters(),
        @SerializedName("comics")
        val comics: Comics? = Comics(),
        @SerializedName("creators")
        val creators: Creators? = Creators(),
        @SerializedName("description")
        val description: String? = "",
        @SerializedName("endYear")
        val endYear: Int? = 0,
        @SerializedName("events")
        val events: Events? = Events(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("modified")
        val modified: String? = "",
        @SerializedName("next")
        val next: Next? = Next(),
        @SerializedName("previous")
        val previous: Previous? = Previous(),
        @SerializedName("rating")
        val rating: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = "",
        @SerializedName("startYear")
        val startYear: Int? = 0,
        @SerializedName("stories")
        val stories: Stories? = Stories(),
        @SerializedName("thumbnail")
        val thumbnail: Thumbnail? = Thumbnail(),
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("type")
        val type: String? = "",
        @SerializedName("urls")
        val urls: List<Url?>? = listOf()
    )

    data class Characters(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Item?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    )

    data class Item(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    )

    data class Comics(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<ItemX?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    )

    data class ItemX(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    )

    data class Creators(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<ItemXX?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    )

    data class ItemXX(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = "",
        @SerializedName("role")
        val role: String? = ""
    )

    data class Events(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Any?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    )

    data class Next(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    )

    data class Previous(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    )

    data class Stories(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<ItemXXX?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    )

    data class ItemXXX(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = "",
        @SerializedName("type")
        val type: String? = ""
    )

    data class Thumbnail(
        @SerializedName("extension")
        val extension: String? = "",
        @SerializedName("path")
        val path: String? = ""
    )

    data class Url(
        @SerializedName("type")
        val type: String? = "",
        @SerializedName("url")
        val url: String? = ""
    )
}