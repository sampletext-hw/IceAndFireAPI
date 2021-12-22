package ice_and_fire_api.api.model

data class Hero(
    var name: String? = null,
    var culture: String? = null,
    var born: String? = null,
    var titles: Array<String>? = null,
    var aliases : Array<String>? = null,
    var playedBy: Array<String>? = null,
)
