package id.ac.sttpyk.tokohp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HpModel(
    val `data`: List<Data>,
    val message: String,
    val response_code: Int
):Parcelable {
    @Parcelize
    data class Data(
        val created_at: String,
        val harga: Int,
        val hp: String,
        val id: Int,
        val updated_at: String
    ):Parcelable
}