package br.com.zup.casamento.domain.repository

import br.com.zup.casamento.data.datasource.local.dao.FornecedorDAO
import br.com.zup.casamento.data.datasource.remote.RetrofitService
import br.com.zup.casamento.data.model.FornecedorResponse
import br.com.zup.casamento.data.model.FornecedorResult
import br.com.zup.casamento.domain.model.Fornecedor

class FornecedorRepository(private val fornecedorDAO: FornecedorDAO){

    suspend fun getAllFornecedor(): List<FornecedorResult> = fornecedorDAO.getAllFornecedor()

    suspend fun insertFornecedor(fornecedor: Fornecedor){
        fornecedorDAO.insertFornecedor(fornecedor)
    }

    suspend fun insertAllFornecedorDB(listFornecedor: List<FornecedorResult>) {
        fornecedorDAO.insertAllFornecedor(listFornecedor)
    }

    suspend fun getAllFornecedorNetwork(language: String?): FornecedorResponse {
        return RetrofitService.apiService.getAllFornecedorNetwork(
            language = language
        )
    }
}