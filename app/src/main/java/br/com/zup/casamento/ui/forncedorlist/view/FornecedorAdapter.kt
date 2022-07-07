package br.com.zup.casamento.ui.forncedorlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.casamento.data.model.FornecedorResult
import br.com.zup.casamento.databinding.FornecedorItemBinding
import br.com.zup.casamento.domain.model.Fornecedor
import com.squareup.picasso.Picasso

class FornecedorAdapter(
    private var fornecedorList: MutableList<Fornecedor>,
    private val clickFornecedor: (fornecedor: Fornecedor) -> Unit
    ) :
    RecyclerView.Adapter<FornecedorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FornecedorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fornecedor = fornecedorList[position]
        holder.showFornecedorInfo(fornecedor)
        holder.binding.cvItemLista.setOnClickListener {
            clickFornecedor(fornecedor)
        }
        holder.showfornecedorInfo(fornecedor)
    }

    override fun getItemCount() = fornecedorList.size

    fun updateFornecedorList(newList: MutableList<FornecedorResult>) {
        if (fornecedorList.size == 0) {
            fornecedorList = newList
        } else {
            fornecedorList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: FornecedorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showForncedorInfo(movieResult: FornecedorResult) {
            binding.tvFornecedorName.text = fornecedorResult.title
            Picasso.get().load(URL_BASE_IMAGE + fornecedorResult.posterPath)
                .into(binding.ivFornecedorImage)
        }
    }
}