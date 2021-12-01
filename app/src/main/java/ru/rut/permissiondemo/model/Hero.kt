package ru.rut.permissiondemo.model

data class Hero(
    var name: String? = null,
    var realname: String? = null,
    var team: String? = null,
    var firstAppearanca: String? = null,
    var createdBy : String? = null,
    var publisher: String? = null,
    var imageURL: String? = null,
    var bio: String? = null
)
