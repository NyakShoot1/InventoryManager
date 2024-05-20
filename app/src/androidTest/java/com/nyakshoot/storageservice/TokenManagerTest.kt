package com.nyakshoot.storageservice
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import com.nyakshoot.storageservice.data.local.TokenManager
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TokenManagerTest {

    private val Context.dataStore by preferencesDataStore(name = "token_datastore")
    private lateinit var tokenManager: TokenManager
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        tokenManager = TokenManager(context)
    }

    @Test
    fun `set and get access token`() = runBlocking {
        val accessToken = "test_access_token"
        tokenManager.setAccessToken(accessToken)

        val retrievedToken = tokenManager.getAccessToken().first()
        assertEquals(accessToken, retrievedToken)
    }

    @Test
    fun `set and get refresh token`() = runBlocking {
        val refreshToken = "test_refresh_token"
        tokenManager.setRefreshToken(refreshToken)

        val retrievedToken = tokenManager.getRefreshToken().first()
        assertEquals(refreshToken, retrievedToken)
    }
}
