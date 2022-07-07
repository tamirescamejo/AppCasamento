package br.com.zup.casamento.ui.fornecedordetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.casamento.FORNECEDOR_KEY
import br.com.zup.casamento.R
import br.com.zup.casamento.databinding.FragmentFornecedorDetailBinding
import br.com.zup.casamento.ui.home.HomeActivity

class FornecedorDetailFragment : Fragment() {
    private lateinit var binding: FragmentFornecedorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFornecedorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassedData()
    }

    private fun getPassedData() {
        val fornecedor = arguments?.getParcelable<FornecedorResult>(FORNECEDOR_KEY)

        fornecedor?.let {
            Picasso.get().load(URL_BASE_IMAGE + it.posterPath)
                .into(binding.imageView)

            binding.tvFornecedorName.text = it.title

            binding.tvFornecedorDescricao.text = it.overview

            (activity as HomeActivity).supportActionBar?.title = it.title
        }
}