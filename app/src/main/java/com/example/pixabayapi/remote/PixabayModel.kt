package com.example.pixabayapi

data class PixabayModel(

    var hits: ArrayList<ImageModel>
)

data class ImageModel(
    var largeImageURL: String
)


