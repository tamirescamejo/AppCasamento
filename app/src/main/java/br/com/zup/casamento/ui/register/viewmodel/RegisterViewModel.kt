package br.com.zup.casamento.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.casamento.domain.model.User
import br.com.zup.casamento.domain.usecase.RegisterUseCase

class RegisterViewModel : ViewModel() {
    private val registerUseCase = RegisterUseCase()
    val registerState = MutableLiveData<ViewState<User>>()

    fun register(user: User) {
        if (user.email.isEmpty()) {
            registerState.value = ViewState.Error(Throwable("Favor informar o e-mail!"))
        } else if (user.password.isEmpty() || user.password != user.confirmationPassword) {
            registerState.value = ViewState.Error(Throwable("Favor informar senhas iguais"))
        } else {
            registerState.value = registerUseCase.registerUser(user).value
        }
    }
}