package com.example.marcio.popmoviesk.data.model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject


class Movie() : Parcelable {

    lateinit var posterPath: String
    var adult: Boolean = false
    lateinit var overview: String
    lateinit var releaseDate: String
    var id: Int = 0
    lateinit var originalTitle: String
    lateinit var originalLanguage: String
    lateinit var title: String
    lateinit var backdropPath: String
    var popularity: Double = 0.0
    var voteCount: Int = 0
    var video: Boolean = false
    var voteAverage: Double = 0.0

    constructor(jsonObject: JSONObject) : this() {
        posterPath = jsonObject.optString("poster_path")
        adult = jsonObject.optBoolean("adult")
        overview = jsonObject.optString("overview")
        releaseDate = jsonObject.optString("release_date")
        id = jsonObject.optInt("id")
        originalTitle = jsonObject.optString("original_title")
        originalLanguage = jsonObject.optString("original_language")
        title = jsonObject.optString("title")
        backdropPath = jsonObject.optString("backdrop_path")
        popularity = jsonObject.optDouble("poster_path")
        voteCount = jsonObject.optInt("popularity")
        video = jsonObject.optBoolean("video")
        voteAverage = jsonObject.optDouble("vote_average")
    }

    constructor(parcel: Parcel) : this() {
        posterPath = parcel.readString()
        adult = parcel.readByte().toInt() != 0
        overview = parcel.readString()
        releaseDate = parcel.readString()
        id = parcel.readInt()
        originalTitle = parcel.readString()
        originalLanguage = parcel.readString()
        title = parcel.readString()
        backdropPath = parcel.readString()
        popularity = parcel.readDouble()
        voteCount = parcel.readInt()
        video = parcel.readByte().toInt() != 0
        voteAverage = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeByte((if (adult) 1 else 0).toByte())
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeInt(id)
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeDouble(popularity)
        parcel.writeInt(voteCount)
        parcel.writeByte((if (video) 1 else 0).toByte())
        parcel.writeDouble(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}