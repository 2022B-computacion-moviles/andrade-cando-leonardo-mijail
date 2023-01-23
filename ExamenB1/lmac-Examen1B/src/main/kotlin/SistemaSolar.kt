import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

class SistemaPlanetario(
    private var idSistemaPlanetario: Int,
    private var nombreSistemaPlanetario: String,
    private var formacionAño: Float,
    private var galaxia: String,
    private var tipoSistemaPlanetario: String,

    private var arrayplanetas: ArrayList<Planeta>
){
    init {
        idSistemaPlanetario
        nombreSistemaPlanetario
        formacionAño
        galaxia
        tipoSistemaPlanetario
        arrayplanetas
    }

    fun setidSistemaPlanetario(idSistemaPlanetario: Int){
        this.idSistemaPlanetario = idSistemaPlanetario
    }
    fun setnombreSistemaPlanetario(nombreSistemaPlanetario: String){
        this.nombreSistemaPlanetario = nombreSistemaPlanetario
    }
    fun setformacionAño(formacionAño: Float){
        this.formacionAño = formacionAño
    }
    fun setgalaxia(galaxia: String){
        this.galaxia = galaxia
    }
    fun setTipoSistemaPlanetario(tipoSistemaPlanetario: String){
        this.tipoSistemaPlanetario = tipoSistemaPlanetario
    }
    fun getidSistemaPlanetario():Int {
        return idSistemaPlanetario
    }
    fun getNombreSistemaPlanetario():String {
        return nombreSistemaPlanetario
    }
    fun getFormacionAño():Float {
        return formacionAño
    }
    fun getgalaxia():String{
        return galaxia
    }
    fun getTipoSistemaPlanetario():String{
        return tipoSistemaPlanetario
    }

    companion object{
        //compartir objetos
        fun extraerSistemaPlanetario() {
            var path = Paths.get("src/main/resources/SistemaSolar.txt")
            var path2 = Paths.get("src/main/resources/planeta.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var sistemaPlanetario = it.split(",")
                print(
                    "Id: " + sistemaPlanetario[0] + "\n"
                            + "Nombre: " + sistemaPlanetario[1] + "\n"
                            + "Formacion: " + sistemaPlanetario[2] + "\n"
                            + "Galaxia: " + sistemaPlanetario[3] + "\n"
                            + "Tipo: " + sistemaPlanetario[4] + "\n"
                )
                println("Planetas:")

                Files.lines(path2, Charsets.UTF_8).forEach {
                    var planetas = it.split(",")
                    var idPlaneta = planetas[0]
                    for (i in 5..sistemaPlanetario.size - 1) {
                        if (idPlaneta == sistemaPlanetario[i]) {
                            println("\t" + planetas[0] + ") " + planetas[1] + " - " + planetas[3])
                        }
                    }
                }
            }
            println()
        }

        fun modificarSistemaPlanetario(idSistemaPlanetario: Int) {
            var path = Paths.get("src/main/resources/sistemaSolar.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var sistemaPlanetario = it.split(",")
                if (sistemaPlanetario[0] == idSistemaPlanetario.toString()) {
                    var update = true
                    print(
                        "Id: " + sistemaPlanetario[0] + "\n"
                                + "Nombre: " + sistemaPlanetario[1] + "\n"
                                + "Formacion: " + sistemaPlanetario[2] + "\n"
                                + "Galaxia: " + sistemaPlanetario[3] + "\n"
                                + "Tipo: " + sistemaPlanetario[4] + "\n"
                    )
                    println("Lista de Planetas:")
                    var path = Paths.get("src/main/resources/planeta.txt")
                    Files.lines(path, Charsets.UTF_8).forEach {
                        var planetas = it.split(",")
                        var idPlaneta = planetas[0]
                        for (i in 5..sistemaPlanetario.size - 1) {
                            if (idPlaneta == sistemaPlanetario[i]) {
                                println("\t" + planetas[0] + ") " + planetas[1] + " - " + planetas[3])
                            }
                        }
                    }
                    //Ver que atributo desea modificar
                    var auxModificar = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Formacion, 3) Galaxia, 4) Tipo, 5) Planetas")
                        var atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                auxModificar.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese el año de formacion del sistema planetario: ")
                                var formacion = readln()
                                auxModificar.set(1, formacion)
                            }

                            (3) -> {
                                print("Ingrese la galaxia: ")
                                var galaxia = readln()
                                auxModificar.set(2, galaxia)
                            }

                            (4) -> {
                                print("Ingrese el nuevo tipo de sistema planetario: ")
                                var tipo = readln()
                                auxModificar.set(3, tipo)
                            }

                            (5) -> {
                                print("Seleccione una acción 1) Agregar un nuevo planeta 2) Eliminar un planeta: ")
                                var opcion = readln().toInt()
                                if (opcion == 1) {
                                    println("Planetas")
                                    Planeta.extraerPlaneta()
                                    print("Seleccione los planetas que se deseen agregar (1,2,...): ")
                                    var listaActualizar = readln()
                                    auxModificar.set(4, ModificarListaPlanetas(listaActualizar, sistemaPlanetario[0].toInt()))
                                } else {
                                    print("Seleccione los planetas que se deseen eliminar (1,2,...): ")
                                    var listaBorrar = readln()
                                    var auxLista = BorrarListaPlanetas(listaBorrar, sistemaPlanetario[0].toInt())
                                    auxModificar.set(4, auxLista)
                                }
                            }
                        }

                        println("¿Desea seguir actualizando 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            update = false //Terminar update de albúm
                            for (i in 0..auxModificar.size - 1) {
                                if (auxModificar[i] == "0") {
                                    if (i == 4) { // Tomando lista de canciones original del albúm
                                        for (j in 5..sistemaPlanetario.size - 1) {
                                            if (j == sistemaPlanetario.size - 1) {
                                                auxModificar[i] += sistemaPlanetario[j]
                                            } else {
                                                auxModificar[i] += sistemaPlanetario[j] + ","
                                            }
                                        }
                                    } else {
                                        auxModificar[i] = sistemaPlanetario[i + 1]
                                    }
                                }
                            }
                            archivoUpdate += sistemaPlanetario[0] + "," + auxModificar[0] + "," + auxModificar[1] + "," + auxModificar[2] + "," + auxModificar[3] + "," + auxModificar[4] + "\n"
                        }
                    } while (update)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("Sistema Planetario no existente")
            } else {
                File("src/main/resources/sistemaSolar.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("Sistema planetario actualizado")
                extraerSistemaPlanetario()
            }
        }

        fun ModificarListaPlanetas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/sistemaSolar.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var sistemaPlanetario = it.split(",")
                if (sistemaPlanetario[0].toInt() == id) {
                    for (i in 5..sistemaPlanetario.size - 1) {
                        if (i == sistemaPlanetario.size - 1) {
                            newLista += sistemaPlanetario[i]
                        } else {
                            newLista += sistemaPlanetario[i] + ","
                        }
                    }
                }
            }
            return newLista + "," + lista
        }

        fun BorrarListaPlanetas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/sistemaSolar.txt")
            var listaBorrar = lista.split(",")
            Files.lines(path, Charsets.UTF_8).forEach {
                var sistemaPlanetario = it.split(",")
                if (sistemaPlanetario[0].toInt() == id) {
                    for (i in 5..sistemaPlanetario.size - 1) {
                        var bandera = false
                        for (j in 0..listaBorrar.size - 1) {
                            if (sistemaPlanetario[i] != listaBorrar[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            newLista += sistemaPlanetario[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(newLista, 1)
        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }
        fun borrarSistemaPlanetario(idSistemaPlanetario: Int) {
            //Leer archivo
            var path = Paths.get("src/main/resources/sistemaSolar.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var sistemaPlanetario = it.split(",")
                if (sistemaPlanetario[0] == idSistemaPlanetario.toString()) {
                    println("Sistema Planetario eliminado con exito")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("No existen registros del Sistema Planetario")
            } else {
                File("src/main/resources/sistemaSolar.txt").printWriter()
                    .use { out -> out.print(archivoUpdate) }
            }
        }

        fun setListaPlanetas(arrayPlanetas: Array<Int>): ArrayList<Planeta> {
            var path = Paths.get("src/main/resources/planeta.txt")
            var listaPlanetas: ArrayList<Planeta> = ArrayList()
            var i = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayPlanetas.size) {
                    if (stringSplit[0] == arrayPlanetas[i].toString()) {
                        var splitFecha = stringSplit[3].split("-")
                        var planetaAux = Planeta(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2].toDouble(),
                            Date(splitFecha[0].toInt(), splitFecha[1].toInt(), splitFecha[2].toInt()),
                            stringSplit[4].toFloat(),
                            stringSplit[5].toBoolean()
                        )
                        listaPlanetas.add(planetaAux)
                        i++
                    }
                }
            }
            return listaPlanetas
        }

        fun ObtenerIdSistemaPlanetario(): Int {
            var path = Paths.get("src/main/resources/sistemaSolar.txt")
            var id = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                id+= 1
            }
            return id
        }
    }

    fun ingresarSistemaPlanetario(sizeArrayPlaneta: Int) {

        var path = Paths.get("src/main/resources/sistemaSolar.txt")
        var data =
            this.idSistemaPlanetario.toString() + "" +
                    "," + this.nombreSistemaPlanetario +
                    "," + this.formacionAño.toString() +
                    "," + this.galaxia +
                    "," + this.tipoSistemaPlanetario +
                    ","
        var i = 1
        for (item in this.arrayplanetas!!){
            if(i< sizeArrayPlaneta){
                data += item.getidPlaneta().toString() + ","
            } else {
                data += item.getidPlaneta().toString()
            }
            i++
        }

        data += "\n"

        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("Sistema Planetario agregado\n")
        } catch (e: IOException) {
            println("Error en la creacion de un Sistema Planetario")
        }
    }

}