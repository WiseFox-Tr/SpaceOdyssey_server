package wisefox.com.spaceodysseyserver.model

data class ServerResponseBean<T>(
    val code: Int,
    val message: String?,
    val data: T? = null
)

data class ParamsBean(
    val level: LevelBean,
    val theme: ThemeBean,
)