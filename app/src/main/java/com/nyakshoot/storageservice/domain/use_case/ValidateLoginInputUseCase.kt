package com.nyakshoot.storageservice.domain.use_case

import com.nyakshoot.storageservice.domain.model.LoginInputValidationType
import com.nyakshoot.storageservice.utils.EMAIL_ADDRESS_PATTERN

class ValidateLoginInputUseCase() {
    operator fun invoke(email: String, password: String): LoginInputValidationType {
        if(email.isEmpty() || password.isEmpty()) {
            return LoginInputValidationType.EmptyField
        }
        if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}