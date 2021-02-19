package com.sid.swipecard.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CardResponse() : Parcelable {
    @SerializedName("data")
    @Expose
    var data: List<CardData>? = null

    constructor(parcel: Parcel) : this() {
        data = parcel.createTypedArrayList(CardData)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardResponse> {
        override fun createFromParcel(parcel: Parcel): CardResponse {
            return CardResponse(parcel)
        }

        override fun newArray(size: Int): Array<CardResponse?> {
            return arrayOfNulls(size)
        }
    }
}