package com.sid.swipecard.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//This is the POJO class which is made as per the JSON of the API response
class CardData() : Parcelable{
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        text = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardData> {
        override fun createFromParcel(parcel: Parcel): CardData {
            return CardData(parcel)
        }

        override fun newArray(size: Int): Array<CardData?> {
            return arrayOfNulls(size)
        }
    }

}
