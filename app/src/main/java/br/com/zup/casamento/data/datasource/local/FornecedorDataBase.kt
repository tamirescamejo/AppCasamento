package br.com.zup.casamento.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.zup.casamento.data.datasource.local.dao.FornecedorDAO
import br.com.zup.casamento.domain.model.Fornecedor

@Database(entities = [Fornecedor::class], version = 1)
@TypeConverters(Converters::class)
abstract class FornecedorDataBase : RoomDatabase() {
    abstract fun fornecedorDao(): FornecedorDAO

    companion object {
        @Volatile
        private var INSTANCE: FornecedorDataBase? = null

        fun getDatabase(context: Context): FornecedorDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FornecedorDataBase::class.java,
                    "fornecedor_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}