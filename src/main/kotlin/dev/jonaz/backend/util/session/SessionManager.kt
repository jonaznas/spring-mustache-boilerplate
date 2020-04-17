package dev.jonaz.backend.util.session

import java.net.InetAddress
import java.util.*
import kotlin.streams.asSequence

object SessionManager {
    private var storage: MutableMap<String, SessionData> = mutableMapOf()

    private fun deleteSessions(keys: Map<String, SessionData>) = keys.forEach { (t, _) -> storage.keys.remove(t) }

    private fun getById(userid: Int): Map<String, SessionData> {
        var keys = storage.filter { it.value.user == userid }
        if (keys.keys.size > 1) {
            deleteSessions(keys)
            keys = mapOf()
        }
        return keys
    }

    private fun generateToken(length: Long = 60): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.:,-#"
        return Random().ints(length, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
    }

    private fun exist(token: String?): Boolean = storage.containsKey(token)

    fun get(token: String?): SessionData? = storage[token]

    fun create(userid: Int, remoteAddress: InetAddress?): String {
        val newToken = generateToken()
        val oldSessions = getById(userid)

        deleteSessions(oldSessions)

        storage[newToken] = SessionData(userid, newToken, remoteAddress)

        return newToken
    }

    fun validate(token: String?, remoteAddress: InetAddress?): Boolean {
        return if (exist(token)) {
            val session = get(token)

            if (session?.remoteAddress != remoteAddress) return false

            true
        } else false
    }

    fun destroy(userid: Int) {
        val keys = getById(userid)
        deleteSessions(keys)
    }
}
