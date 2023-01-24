import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    fun despliegeInfo(elemento: String): String {
        var salida=""
        salida += "Opciones \n" +
                "1. Ingresar un nuevo "+ elemento +"\n" +
                "2. Ver informacion sobre " +elemento+"\n" +
                "3. Actualizar información sobre "+elemento+"\n" +
                "4. Eliminar informacion sobre "+ elemento + "\n" +
                "5. Volver \n"
        return salida
    }
    fun banner() : String{
        var salida=""
        salida += "Examen Aplicaciones Moviles: \n" +
                "1. Planeta\n" +
                "2. Sistema Planetario\n" +
                "3. Salir"
        return  salida
    }
    fun planeta (){
        print("Nombre del planeta: ")
        val nombre = readln()

        print("Distancia del centro: ")
        val distancia = readln().toDouble()

        print("Fecha de descubrimiento (AAAA-MM-DD): ")
        val fecha = readln().split("-")
        val fechaDesc: Date =
            Date(fecha[0].toInt()-1900, fecha[1].toInt() - 1, fecha[2].toInt())

        print("Tamaño del Planeta: ")
        val tamaño = readln().toFloat()

        print("¿Posee Vida? 1.SI 2.NO:")
        var vida=false
        val auxVida = readln().toInt()
        vida = auxVida == 1

        val newPlaneta =
            Planeta(Planeta.ObtenerIdPlaneta() + 1, nombre, distancia, fechaDesc, tamaño, vida)
        newPlaneta.IngresarPlaneta()

        despliegeInfo("planeta")
    }
    fun sistemaPlanetario(){
        print("Nombre del Sistema Planetario: ")
        val nombre = readln()

        print("Año de formación: ")
        val año = readln().toFloat()

        print("Galaxia a la que pertenece: ")
        val galaxia = readln()

        print("Tipo de Sistema Planetario: ")
        val tipo = readln()

        println("Planetas")
        Planeta.extraerPlaneta()

        print("Seleccione los planetas que forman parte del sistema planetario:\n")

        val intPlanetasArray = readLine().toString().split(",").map { it.toInt() }.toTypedArray()
        val listaSistemaPlanetario: ArrayList<Planeta> = SistemaPlanetario.setListaPlanetas(intPlanetasArray)

        val newSistemaPlanetario = SistemaPlanetario(SistemaPlanetario.ObtenerIdSistemaPlanetario() + 1,
            nombre, año, galaxia, tipo, listaSistemaPlanetario)
        newSistemaPlanetario.ingresarSistemaPlanetario(intPlanetasArray.size)

    }

    do {
        var opcionUsuario = false
        println(banner())
        val opTabla = readln().toInt()
        when (opTabla){
            1 ->print(despliegeInfo("planeta"))
            2 ->print(despliegeInfo("Sistema Planetario"))
            3 ->opcionUsuario = true
            else ->{
                print("Opcion no reconocida")
                opcionUsuario = true

            }
        }
        var opcionAux = false
        while (!opcionAux) {
            val opcionCrud = readln().toInt()
            when (opcionCrud) {
                (1) -> {
                    if (opTabla == 1) {
                        planeta()
                        print(despliegeInfo("planeta"))
                    } else {
                        sistemaPlanetario()
                        print(despliegeInfo("Sistema Planetario"))
                    }
                }

                (2) -> {
                    if (opTabla == 1) {
                        println("Planetas:")
                        Planeta.extraerPlaneta()
                        print(despliegeInfo("planeta"))

                    } else {
                        println("Sistema Planetario")
                        SistemaPlanetario.extraerSistemaPlanetario()
                        print(despliegeInfo("Sistema Planetario"))
                    }
                }

                (3) -> {
                    if (opTabla == 1) {
                        print("Ingrese el Id del planeta para modificar: ")
                        Planeta.ModificarPlaneta(readln().toInt())
                        print(despliegeInfo("planeta"))
                    } else {
                        print("Ingrese el Id del Sistema Planetario para modificar: ")
                        SistemaPlanetario.modificarSistemaPlanetario(readln().toInt())
                        print(despliegeInfo("Sistema Planetario"))
                    }
                }

                (4) -> {
                    if (opTabla == 1) {
                        print("Ingrese el Id del planeta para eliminarlo: ")
                        Planeta.BorrarPlaneta(readln().toInt())
                        print(despliegeInfo("planeta"))
                    } else {
                        print("Ingrese el Id del Sistema Planetario para eliminarlo: ")
                        SistemaPlanetario.borrarSistemaPlanetario(readln().toInt())
                        print(despliegeInfo("Sistema Planetario"))
                    }
                }
                (5) -> {
                    opcionAux = true
                }
            }
        }
    } while (!opcionUsuario)





}