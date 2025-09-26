package com.tiprecorder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiprecorder.data.model.Currency
import com.tiprecorder.data.model.Currencies
import com.tiprecorder.data.model.Tip
import com.tiprecorder.data.repository.TipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddTipViewModel @Inject constructor(
    private val repository: TipRepository
) : ViewModel() {

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()

    private val _selectedCurrency = MutableStateFlow<Currency?>(null)
    val selectedCurrency: StateFlow<Currency?> = _selectedCurrency.asStateFlow()

    private val _selectedDate = MutableStateFlow(Date())
    val selectedDate: StateFlow<Date> = _selectedDate.asStateFlow()

    private val _notes = MutableStateFlow("")
    val notes: StateFlow<String> = _notes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid.asStateFlow()

    init {
        // Default currency
        _selectedCurrency.value = Currencies.supportedCurrencies.first()
        validateInput()
    }

    fun updateAmount(newAmount: String) {
        _amount.value = newAmount
        validateInput()
    }

    fun updateCurrency(currency: Currency) {
        _selectedCurrency.value = currency
        validateInput()
    }

    fun updateDate(date: Date) {
        _selectedDate.value = date
        validateInput()
    }

    fun updateNotes(newNotes: String) {
        _notes.value = newNotes
    }

    private fun validateInput() {
        val amountValue = _amount.value.toDoubleOrNull()
        val isValidAmount = amountValue != null && amountValue > 0
        val hasCurrency = _selectedCurrency.value != null
        
        _isValid.value = isValidAmount && hasCurrency
    }

    fun saveTip(): Boolean {
        if (!_isValid.value) return false

        val amountValue = _amount.value.toDoubleOrNull() ?: return false
        val currency = _selectedCurrency.value ?: return false

        val tip = Tip(
            amount = amountValue,
            currency = currency.code,
            date = _selectedDate.value,
            notes = _notes.value.takeIf { it.isNotBlank() }
        )

        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.insertTip(tip)
                clearForm()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }

        return true
    }

    private fun clearForm() {
        _amount.value = ""
        _notes.value = ""
        _selectedDate.value = Date()
        _selectedCurrency.value = Currencies.supportedCurrencies.first()
    }
}
