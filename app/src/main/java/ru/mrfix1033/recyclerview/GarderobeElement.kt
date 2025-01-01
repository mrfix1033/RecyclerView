package ru.mrfix1033.recyclerview

import android.os.Parcel
import android.os.Parcelable

class GarderobeElement(var title: String, var description: String, val image: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GarderobeElement> {
        override fun createFromParcel(parcel: Parcel): GarderobeElement {
            return GarderobeElement(parcel)
        }

        override fun newArray(size: Int): Array<GarderobeElement?> {
            return arrayOfNulls(size)
        }
    }
}