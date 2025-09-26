package com.tiprecorder.data.dao

import androidx.room.*
import com.tiprecorder.data.model.CurrencyTotal
import com.tiprecorder.data.model.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips ORDER BY date DESC")
    fun getAllTips(): Flow<List<Tip>>

    @Query("SELECT SUM(amount) FROM tips")
    fun getTotalAmount(): Flow<Double?>

    @Query("SELECT SUM(amount) FROM tips WHERE currency = :currency")
    fun getTotalAmountByCurrency(currency: String): Flow<Double?>

    @Query("SELECT currency, SUM(amount) as total FROM tips GROUP BY currency")
    fun getTotalAmountByCurrencyGrouped(): Flow<List<CurrencyTotal>>

    @Insert
    suspend fun insertTip(tip: Tip): Long

    @Update
    suspend fun updateTip(tip: Tip)

    @Delete
    suspend fun deleteTip(tip: Tip)

    @Query("DELETE FROM tips WHERE id = :tipId")
    suspend fun deleteTipById(tipId: Long)
}
