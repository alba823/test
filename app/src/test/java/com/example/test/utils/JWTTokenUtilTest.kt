package com.example.test.utils

import com.example.test.data.api.SECRET
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACVerifier
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

internal const val EXPECTED_ALGORITHM = "HmacSHA256"
internal const val EXPECTED_TYPE = "JWT"

@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class JWTTokenUtilTest {

    private lateinit var tokenUtil: JWTTokenUtil
    private lateinit var jwt: JWT

    @Before
    fun setUp() {
        tokenUtil = JWTTokenUtil(SECRET)
        val verifier = HMACVerifier.newVerifier(SECRET.encode())
        val token = tokenUtil.generateToken()
        jwt = JWT.getDecoder().decode(
            token,
            verifier
        )
    }

    @Test
    fun `generated token payload should contain uid and identity`() {
        Assert.assertTrue(jwt.allClaims.containsKey(JWTTokenUtil.UID))
        Assert.assertTrue(jwt.allClaims.containsKey(JWTTokenUtil.IDENTITY))
    }

    @Test
    fun `generated token header algorithm should be HmacSHA256`() {
        Assert.assertEquals(
            EXPECTED_ALGORITHM,
            jwt.header.algorithm.algorithm
        )
    }

    @Test
    fun `generated token header type should be JWT`() {
        Assert.assertEquals(
            EXPECTED_TYPE,
            jwt.header.type
        )
    }
}