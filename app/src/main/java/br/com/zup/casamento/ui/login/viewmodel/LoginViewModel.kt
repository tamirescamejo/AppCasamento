package br.com.zup.casamento.ui.login.viewmodel

import android.app.Application
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.zup.casamento.domain.model.User
import br.com.zup.casamento.domain.usecase.LoginUseCase

class LoginViewModel(application: Application)  : AndroidViewModel(application) {
    private val loginUseCase = LoginUseCase(application)
    val loginState = MutableLiveData<ViewState<User>>()
    val userLoggedState = MutableLiveData<ViewState<User>>()

    fun login(user: User, flagSaveData: Boolean) {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            loginState.value = ViewState.Error(Throwable("Favor informar o e-mail e senha!"))
        } else {
            loginState.value = loginUseCase.login(user, flagSaveData).value
        }
    }

    fun getUserLogged() {
        userLoggedState.value = loginUseCase.getUserLogged().value
    }
}