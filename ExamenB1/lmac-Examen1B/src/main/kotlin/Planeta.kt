import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

class Planeta(
    private var idPlaneta:Int,
    private var nombrePlaneta:String,
    private var distancia:Double,
    private var fecha_descubrimiento: Date,
    private var tamaño_planeta:Float,
    private var poseeVida: Boolean,
){
    init {
        idPlaneta
        nombrePlaneta
        distancia
        fecha_descubrimiento
        tamaño_planeta
        poseeVida
    }
    fun setidPlaneata(idPlaneta: Int) {
        this.idPlaneta = idPlaneta
    }
    fun setnombrePlaneta(nombrePlaneta: String){
        this.nombrePlaneta = nombrePlaneta
    }
    fun setDistancia(distancia: Double){
        this.distancia = distancia
    }
    fun setFecha_Descubrimiento(fecha_descubrimiento: Date){
        this.fecha_descubrimiento = fecha_descubrimiento
    }
    fun setTamañoPlaneta(tamaño_planeta: Float){
        this.tamaño_planeta = tamaño_planeta
    }
    fun setposeeVida(poseeVida: Boolean){
        this.poseeVida = poseeVida
    }
    fun getidPlaneta(): Int {
        return idPlaneta
    }
    fun getNombrePlante(): String{
        return nombrePlaneta
    }

    fun getDistancia(): Double{
        return distancia
    }
    fun getFecha_Descubrimineto(): Date{
        return fecha_descubrimiento
    }
    fun getTamaño_Planeta(): Float{
        return tamaño_planeta
    }
    fun getposeeVida():Boolean{
        return poseeVida
    }

    companion object{
        //compartir objetos
        fun extraerPlaneta(){
            var path = Paths.get("src/main/resources/planeta.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var planeta = it.split(",")
                println(
                    "id: " + planeta[0] + "\n"
                            + "Nombre: " + planeta[1] + "\n"
                            + "Distancia: " + planeta[2] + "\n"
                            + "Fecha descubrimiento: " + planeta[3] + "\n"
                            + "tamaño: " + planeta[4] + "\n"
                            + "Posee vida: " + planeta[5] + "\n"
                )
            }

        }

        fun ModificarPlaneta(idPlaneta: Int) {
            var path = Paths.get("src/main/resources/planeta.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var planeta = it.split(",")
                if (planeta[0] == idPlaneta.toString()) {
                    var Update = true
                    println(
                        "id: " + planeta[0] + "\n"
                                + "Nombre: " + planeta[1] + "\n"
                                + "Distancia: " + planeta[2] + "\n"
                                + "Fecha descubrimiento: " + planeta[3] + "\n"
                                + "tamaño: " + planeta[4] + "\n"
                                + "Posee Vida: " + planeta[5] + "\n"
                    )
                    //atributos a modificar

                    var valoresActualizar = arrayOf<String>("0", "0", "0", "0","0")
                    do {
                        println("Elija el atributo a modificar: 1. Nombre del planeta, 2. Distancia, " +
                                "3. Fecha descubrimiento, 4. Tamaño 5.Posee Vida ")
                        var opcion_actualizar = readln().toInt()
                        when (opcion_actualizar) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                valoresActualizar.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese el nuevo Distancia: ")
                                var dist = readln()
                                valoresActualizar.set(1, dist)
                            }

                            (3) -> {
                                print("Ingrese la nueva fecha de publicación (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                valoresActualizar.set(2, formato.format(newFecha))
                            }

                            (4) -> {
                                print("Ingrese el nuevo tamaño: ")
                                var tamaño = readln()
                                valoresActualizar.set(3, tamaño)
                            }

                            (5) -> {
                                print("¿Posee vida? 1. SI 2.NO")
                                var vida=false
                                var auxVida = readln().toInt()
                                vida = auxVida == 1
                                valoresActualizar.set(4, vida.toString())
                            }
                        }

                        println("¿Existen más campos a actualizar 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            Update = false //
                            for (i in 0..valoresActualizar.size - 1) {
                                if (valoresActualizar[i] == "0") {
                                    valoresActualizar[i] = planeta[i + 1]
                                }
                            }
                            archivoUpdate += planeta[0] + "," + valoresActualizar[0] + "" +
                                    ","+ valoresActualizar[1] + "," + valoresActualizar[2] + "" +
                                    "," + valoresActualizar[3] +","+ valoresActualizar[4]+"\n"
                        }
                    } while (Update == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("No existe el registro")
            } else {
                File("src/main/resources/planeta.txt").printWriter()
                    .use { out -> out.print(archivoUpdate) }
                println("Registro actualizado con exito")
                extraerPlaneta()
            }
        }

        fun BorrarPlaneta(idPlaneta: Int) {

            var path = Paths.get("src/main/resources/planeta.txt")
            var flag = false
            var archivoAux = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var elemCadena = it.split(",")
                if (elemCadena[0] == idPlaneta.toString()) {
                    flag = true
                } else {
                    archivoAux += it + "\n"
                }
            }
            if (!flag) {
                println("Registro no encontrado")
            } else {
                File("src/main/resources/planeta.txt")
                    .printWriter().use { out -> out.print(archivoAux) }
                println("Registro eliminado con exito")
                extraerPlaneta()
            }
        }
        fun ObtenerIdPlaneta(): Int {
            var path = Paths.get("src/main/resources/planeta.txt")
            var id = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                 id+= 1
            }
            return id
        }
    }

    fun IngresarPlaneta() {
        var path = Paths.get("src/main/resources/planeta.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idPlaneta.toString() + "," + this.nombrePlaneta + "," + this.distancia.toString() + "," +
                    formato.format(this.fecha_descubrimiento)+"," + this.tamaño_planeta.toString() + "" +
                    ","+ this.poseeVida + "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("Nuevo registro agregado")
        } catch (e: IOException) {
            println("Fallo al agregar el nuevo registro")
        }
    }

}