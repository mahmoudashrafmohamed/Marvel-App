package com.dev.mahmoud_ashraf.marvelapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
@Parcelize
data class CharactersResponse(
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
) : Parcelable {
    @Parcelize
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
    ) : Parcelable

    @Parcelize
    data class Result(
        @SerializedName("comics")
        val comics: Comics? = Comics(),
        @SerializedName("description")
        val description: String? = "",
        @SerializedName("events")
        val events: Events? = Events(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("modified")
        val modified: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = "",
        @SerializedName("series")
        val series: Series? = Series(),
        @SerializedName("stories")
        val stories: Stories? = Stories(),
        @SerializedName("thumbnail")
        val thumbnail: Thumbnail? = Thumbnail(),
        @SerializedName("urls")
        val urls: List<Url?>? = listOf()
    ) : Parcelable

    @Parcelize
    data class Comics(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Item?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    ) : Parcelable

    @Parcelize
    data class Item(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    ) : Parcelable

    @Parcelize
    data class Events(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Item?>? = listOf()
    ) : Parcelable

    @Parcelize
    data class Series(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Item?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    ) : Parcelable

   /* @Parcelize
    data class ItemX(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = ""
    ) : Parcelable*/

    @Parcelize
    data class Stories(
        @SerializedName("available")
        val available: Int? = 0,
        @SerializedName("collectionURI")
        val collectionURI: String? = "",
        @SerializedName("items")
        val items: List<Item?>? = listOf(),
        @SerializedName("returned")
        val returned: Int? = 0
    ) : Parcelable

   /* @Parcelize
    data class ItemXX(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("resourceURI")
        val resourceURI: String? = "",
        @SerializedName("type")
        val type: String? = ""
    ) : Parcelable*/

    @Parcelize
    data class Thumbnail(
        @SerializedName("extension")
        val extension: String? = "",
        @SerializedName("path")
        val path: String? = ""
    ) : Parcelable

    @Parcelize
    data class Url(
        @SerializedName("type")
        val type: String? = "",
        @SerializedName("url")
        val url: String? = ""
    ) : Parcelable
}