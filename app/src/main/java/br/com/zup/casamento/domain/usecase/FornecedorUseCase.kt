package br.com.zup.casamento.domain.usecase

import android.app.Application
import androidx.constraintlayout.motion.utils.ViewState
import br.com.zup.casamento.data.datasource.local.FornecedorDataBase
import br.com.zup.casamento.data.model.FornecedorResult
import br.com.zup.casamento.domain.model.Fornecedor
import br.com.zup.casamento.domain.repository.FornecedorRepository

class FornecedorUseCase(application: Application){
    private val fornecedorDao = FornecedorDataBase.getDatabase(application).fornecedorDao()
    private val fornecedorRepository = FornecedorRepository(fornecedorDao)

    suspend fun getAllFornecedores(): ViewState<List<Fornecedor>> {
        return try {
            val fornecedores = fornecedorRepository.getAllFornecedores()
            ViewState.Success(fornecedores)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de fornecedores!"))
        }
    }

    suspend fun insertFornecedor(fornecedor: Fornecedor): ViewState<Fornecedor> {
        return try {
            fornecedorRepository.insertFornecedor(fornecedor)
            ViewState.Success(fornecedor)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o fornecedor!"))
        }
    }
}