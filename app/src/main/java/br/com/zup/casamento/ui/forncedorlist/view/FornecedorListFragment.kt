package br.com.zup.casamento.ui.forncedorlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.utils.ViewState
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.casamento.FORNECEDOR_KEY
import br.com.zup.casamento.R
import br.com.zup.casamento.databinding.FragmentFornecedorListBinding
import br.com.zup.casamento.domain.model.Fornecedor
import br.com.zup.casamento.ui.forncedorlist.viewmodel.FornecedorViewModel
import br.com.zup.casamento.ui.home.HomeActivity


class FornecedorListFragment : Fragment() {
    private lateinit var binding: FragmentFornecedorListBinding

    private val viewModel: FornecedorViewModel by lazy {
        ViewModelProvider(this)[FornecedorViewModel::class.java]
    }

    private val adapter: FornecedorAdapter by lazy {
        FornecedorAdapter(arrayListOf(), this::goToFornecedorDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFornecedorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.fornecedores_title_menu)
        initObserver()
        setUpRvFornecedorList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllFornecedor()
    }

    private fun initObserver() {
        viewModel.fornecedorListState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateFornecedorList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun setUpRvFornecedorList() {
        binding.rvFornecedorList.adapter = adapter
        binding.rvFornecedorList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToFornecedorDetail(fornecedor: Fornecedor) {
        val bundle = bundleOf(FORNECEDOR_KEY to fornecedor)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_fornecedorListFragment_to_fornecedorDetailFragment, bundle
        )
    }
}