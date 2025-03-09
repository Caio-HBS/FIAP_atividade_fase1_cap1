package br.com.fiap.carrerup.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results") val users: List<User>
)

data class User(
    @SerializedName("name") val name: Name,
    @SerializedName("location") val location: Location,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: DateOfBirth,
    @SerializedName("registered") val registered: Registered,
    @SerializedName("picture") val picture: Picture
) {
    data class Name(
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
    )

    data class Location(
        @SerializedName("city") val city: String,
        @SerializedName("state") val state: String,
        @SerializedName("country") val country: String
    )

    data class DateOfBirth(
        @SerializedName("age") val age: Int
    )

    data class Registered(
        @SerializedName("age") val age: Int
    )

    data class Picture(
        @SerializedName("large") val large: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("thumbnail") val thumbnail: String
    )
}
