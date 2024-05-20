package com.nyakshoot.storageservice

import com.nyakshoot.storageservice.domain.model.LoginInputValidationType
import com.nyakshoot.storageservice.domain.use_case.ValidateLoginInputUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidateLoginInputUseCaseTest {
    private val validateLoginInputUseCase = ValidateLoginInputUseCase()

    @Test
    fun `invoke with empty email returns EmptyField`() {
        val result = validateLoginInputUseCase("", "password")
        assertEquals(LoginInputValidationType.EmptyField, result)
    }

    @Test
    fun `invoke with empty password returns EmptyField`() {
        val result = validateLoginInputUseCase("test@example.com", "")
        assertEquals(LoginInputValidationType.EmptyField, result)
    }

    @Test
    fun `invoke with valid email and password returns Valid`() {
        val result = validateLoginInputUseCase("test@example.com", "password")
        assertEquals(LoginInputValidationType.Valid, result)
    }

    @Test
    fun `invoke with invalid email returns NoEmail`() {
        val result = validateLoginInputUseCase("invalid-email", "password")
        assertEquals(LoginInputValidationType.NoEmail, result)
    }
}