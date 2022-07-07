package br.com.zup.casamento.ui.forncedorlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.casamento.domain.model.Fornecedor
import br.com.zup.casamento.domain.usecase.FornecedorUseCase
import br.com.zup.casamento.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FornecedorViewModel(application: Application) : AndroidViewModel(application) {
    private val fornecedorUseCase = FornecedorUseCase(application)
    val fornecedorListState = MutableLiveData<ViewState<List<Fornecedor>>>()

    fun getAllFornecedores() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    fornecedorUseCase.getAllFornecedores()
                }
                fornecedorListState.value = response
            } catch (ex: Exception) {
                fornecedorListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista!"))
            }
        }
    }
}