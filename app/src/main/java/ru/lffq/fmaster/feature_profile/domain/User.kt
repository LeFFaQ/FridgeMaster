package ru.lffq.fmaster.feature_profile.domain


sealed class User(
    open val nickname: String,
    open val firstName: String? = null,
    open val secondName: String? = null,
) {
    object Anonymous : User("Анонимный пользователь")
    data class Authorized(
        override val nickname: String,
        override val firstName: String,
        override val secondName: String,
        val profilePic: String
    ) : User(nickname, firstName, secondName)
}