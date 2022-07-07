package br.com.zup.casamento.ui.fornecedoradd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.ViewModelProvider
import br.com.zup.casamento.R
import br.com.zup.casamento.databinding.FragmentFornecedorAddBinding
import br.com.zup.casamento.domain.model.Fornecedor
import br.com.zup.casamento.ui.forncedorlist.viewmodel.FornecedorViewModel
import br.com.zup.casamento.ui.home.HomeActivity
import kotlinx.coroutines.flow.combine

class FornecedorAddFragment : Fragment() {
    private lateinit var binding: FragmentFornecedorAddBinding
    private val viewModel: FornecedorViewModel by lazy {
        ViewModelProvider(this)[FornecedorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFornecedorAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bvSaveFornecedor.setOnClickListener {
            viewModel.insertForncedor(
                Fornecedor(
                    nomeFornecedor = binding.etFornecedorNameAdd.text.toString(),
                    descricao = binding.etFornecedorDescricaoAdd.text.toString(),
                )
            )
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.fornecedorAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}