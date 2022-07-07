package br.com.zup.casamento.data.model

import com.google.gson.annotations.SerializedName

data class FornecedorResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<FornecedorResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
