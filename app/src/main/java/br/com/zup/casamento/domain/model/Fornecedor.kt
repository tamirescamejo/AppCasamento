package br.com.zup.casamento.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "fornecedores")
data class Fornecedor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_fornecedor")
    var codFornecedor: Long? = null,

    @ColumnInfo(name = "nomeFornecedor")
    var nomeFornecedor: String,

    @ColumnInfo(name = "descricao")
    var descricao: String,

    var image: Int = 0,

    var isFavorite: Boolean = false
) : Parcelable