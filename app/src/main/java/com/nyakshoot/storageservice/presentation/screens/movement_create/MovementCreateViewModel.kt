package com.nyakshoot.storageservice.presentation.screens.movement_create

import androidx.lifecycle.ViewModel
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovementCreateViewModel @Inject constructor(
    private val iPositionRepository: IPositionRepository,
) : ViewModel() {

}