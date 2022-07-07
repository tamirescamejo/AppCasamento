package br.com.zup.casamento.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.casamento.data.model.FornecedorResult
import br.com.zup.casamento.domain.model.Fornecedor

@Dao
interface FornecedorDAO {
    @Query("SELECT * FROM fornecedor ORDER BY nomeFornecedor ASC")
    fun getAllFornecedor(): List<FornecedorResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFornecedor(fornecedor: Fornecedor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFornecedor(listFornecedor: List<FornecedorResult>)
}