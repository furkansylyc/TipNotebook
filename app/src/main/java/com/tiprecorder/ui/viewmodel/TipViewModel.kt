package com.tiprecorder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiprecorder.data.model.Tip
import com.tiprecorder.data.repository.TipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipViewModel @Inject constructor(
    private val repository: TipRepository
) : ViewModel() {

    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    val tips: StateFlow<List<Tip>> = _tips.asStateFlow()

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> = _totalAmount.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTips()
        loadTotalAmount()
    }

    private fun loadTips() {
        viewModelScope.launch {
            repository.getAllTips().collect { tipList ->
                _tips.value = tipList
            }
        }
    }

    private fun loadTotalAmount() {
        viewModelScope.launch {
            repository.getTotalAmountInTRY().collect { total ->
                _totalAmount.value = total
            }
        }
    }

    fun addTip(tip: Tip) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.insertTip(tip)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTip(tip: Tip) {
        viewModelScope.launch {
            repository.deleteTip(tip)
        }
    }
}
