package br.com.zup.casamento.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.ViewModelProvider
import br.com.zup.casamento.R
import br.com.zup.casamento.databinding.ActivityRegisterBinding
import br.com.zup.casamento.domain.model.User
import br.com.zup.casamento.ui.home.HomeActivity
import br.com.zup.casamento.ui.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bvLogin.setOnClickListener {
            viewModel.register(
                user = User(
                    name = binding.etUserNameRegister.text.toString(),
                    email = binding.etEmailRegister.text.toString(),
                    password = binding.etPasswordRegister.text.toString(),
                    confirmationPassword = binding.etConfirmPasswordRegister.text.toString()
                )
            )
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.registerState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}