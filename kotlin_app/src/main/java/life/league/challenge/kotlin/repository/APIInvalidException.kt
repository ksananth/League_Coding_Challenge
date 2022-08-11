package life.league.challenge.kotlin.repository

data class APIInvalidException(val value: String) : Throwable()