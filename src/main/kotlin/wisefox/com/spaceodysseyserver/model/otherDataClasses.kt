package wisefox.com.spaceodysseyserver.model

data class ResponseCode<T>(
    val code: Int,
    val message: String?,
    val data: T? = null
)

data class Params(
    val level: LevelBean,
    val theme: ThemeBean,
)