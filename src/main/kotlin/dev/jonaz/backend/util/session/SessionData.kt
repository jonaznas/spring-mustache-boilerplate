package dev.jonaz.backend.util.session

import java.net.InetAddress

data class SessionData(
        val user: Int? = null,
        val token: String? = null,
        val remoteAddress: InetAddress? = null
)
