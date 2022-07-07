package br.com.zup.casamento.ui.fornecedoradd.viewmodel

import android.app.Application
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.casamento.domain.model.Fornecedor
import br.com.zup.casamento.domain.usecase.FornecedorUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class FornecedorAddViewModel(application: Application) : AndroidViewModel(application) {
    private val fornecedorUseCase = FornecedorUseCase(application)
    val fornecedorAddState = MutableLiveData<ViewState<Fornecedor>>()

    fun insertFornecedores(fornecedor: Fornecedor) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    fornecedorUseCase.insertFornecedor(fornecedor)
                }
                fornecedorAddState.value = response
            } catch (ex: Exception) {
                fornecedorAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o fornecedor!"))
            }
        }
    }
}