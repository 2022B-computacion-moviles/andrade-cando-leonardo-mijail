package com.example.movcomplmac

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class ESqliteHelperEntrenador (
    contexto: Context?,
):SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """ 
                CREATE TABLE ENTRENADOR (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre varchar(50),
                email varchar(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        email: String
    ):Boolean{
        //this.readableDatabase Lectura
        //this.writeableDatabase Escritura
        val basedatosEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("nombre",nombre)
        valoresGuardar.put("email",email)

        val resultadoGuardar = basedatosEscritura
            .insert("ENTRENADOR",
                null,
                valoresGuardar
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        //val conexionEscritura = this.writableDatabase
        /*
        SELECT * FROM ENTRENADOR WHERE ID=?
        arrayof(
            id.toString()
            )
        */
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",//tabla
                "Id=?", //id=? and nombre=? where (podemos mandar parametros en orden)
                arrayOf( //Arreglo parametros en orden [1,"Adrian"]
                    id.toString()
                )
            )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        email: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("email",email)
        val resultadoActualizar = conexionEscritura
            .update(
                "ENTRENADOR", //nombre tabla
                valoresActualizar, //valores actualizar
                "id=?", //clausula where
                arrayOf(
                    idActualizar.toString()
                )//parametros where
            )
        conexionEscritura.close()
        return resultadoActualizar != -1
    }
    fun consultarEntrenadorPorId(id: Int): BEntrenador{
        // val baseDatos Lectura = this.readDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        //LOGICA OBTENER EL USUARIO
        do{
            val id = resultadoConsultaLectura.getInt(0) //columna indice 0 ->ID
            val nombre = resultadoConsultaLectura.getString(1)  //columna indice 1 -> NOMBRE
            val email =
                resultadoConsultaLectura.getString(2)  //columna indice 2 -> ENLACE
            if (id != null){
                usuarioEncontrado.id = id
                usuarioEncontrado.nombre = nombre
                usuarioEncontrado.email = email
            }
        }while (resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
        }

}
