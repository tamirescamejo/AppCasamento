package br.com.zup.casamento.data.datasource.remote

import androidx.room.Query
import br.com.zup.casamento.data.datasource.remote.RetrofitService.Companion.API_KEY
import br.com.zup.casamento.data.model.FornecedorResponse
import retrofit2.http.GET

interface FornecedorAPI {

    @GET("fornecedor/popular")
    suspend fun getAllFornecedorNetwork(
        @Query("api_key")
        apiKey: String? = API_KEY,
        @Query("language")
        language: String?
    ): FornecedorResponse
}