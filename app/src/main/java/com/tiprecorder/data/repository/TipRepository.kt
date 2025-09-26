package com.tiprecorder.data.repository

import com.tiprecorder.data.dao.TipDao
import com.tiprecorder.data.model.CurrencyConverter
import com.tiprecorder.data.model.CurrencyTotal
import com.tiprecorder.data.model.Tip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TipRepository @Inject constructor(
    private val tipDao: TipDao
) {
    fun getAllTips(): Flow<List<Tip>> = tipDao.getAllTips()

    fun getTotalAmount(): Flow<Double?> = tipDao.getTotalAmount()

    fun getTotalAmountByCurrency(currency: String): Flow<Double?> = tipDao.getTotalAmountByCurrency(currency)

    fun getTotalAmountInTRY(): Flow<Double> = tipDao.getTotalAmountByCurrencyGrouped().map { currencyTotals ->
        currencyTotals.sumOf { currencyTotal ->
            CurrencyConverter.convertToTRY(currencyTotal.total, currencyTotal.currency)
        }
    }

    suspend fun insertTip(tip: Tip): Long = tipDao.insertTip(tip)

    suspend fun updateTip(tip: Tip) = tipDao.updateTip(tip)

    suspend fun deleteTip(tip: Tip) = tipDao.deleteTip(tip)

    suspend fun deleteTipById(tipId: Long) = tipDao.deleteTipById(tipId)
}
