package br.com.zup.casamento.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.casamento.databinding.ActivityLoginBinding
import br.com.zup.casamento.domain.model.User
import br.com.zup.casamento.ui.home.HomeActivity
import br.com.zup.casamento.ui.login.viewmodel.LoginViewModel
import br.com.zup.casamento.ui.register.view.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.bvLogin.setOnClickListener {
            viewModel.login(
                user = User(
                    email = binding.etUsername.text.toString(),
                    password = binding.etPassword.text.toString()
                ),
                flagSaveData = binding.swSaveData.isChecked
            )
        }

        initObserver()
        viewModel.getUserLogged()
    }

    private fun initObserver() {
        viewModel.userLoggedState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    binding.etUsername.setText(it.data.email)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "Ops tivemos um problema! Tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.loginState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }

                is ViewState.Error -> {
                    binding.etUsername.error = it.throwable.message
                    binding.etPassword.error = it.throwable.message
                }
                else -> {}
            }
        }
    }
}