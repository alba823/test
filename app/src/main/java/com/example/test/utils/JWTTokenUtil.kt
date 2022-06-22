package com.example.test.utils

import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner

class JWTTokenUtil(secret: String) {
    private val secret: String
    private val claims = mapOf(
        UID to "any value",
        IDENTITY to "any value"
    )

    init {
        this.secret = secret
    }

    fun generateToken(): String {
        val secretEncoded = secret.encode()
        val signer: Signer = HMACSigner.newSHA256Signer(secretEncoded)

        val jwt = JWT().apply {
            claims.onEach { claim ->
                addClaim(claim.key, claim.value)
            }
        }

        return JWT.getEncoder().encode(jwt, signer)
    }

    companion object {
        const val UID = "uid"
        const val IDENTITY = "identity"
    }
}