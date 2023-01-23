import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {

    do {
        var opcionUsuario = false
        println(
            "Examen Aplicaciones Moviles:" +
                    "\n1) Planeta" +
                    "\n2) Sistema Planetario" +
                    "\n3) Salir"
        )
        var opTabla = readln().toInt()
        if (opTabla != 3) {
            var opcionAux = false
            var textoConsola = ""
            if (opTabla == 1) {
                textoConsola += "Opciones" +
                        "\n1) Ingresar un nuevo planeta" +
                        "\n2) Ver informacion sobre planetas" +
                        "\n3) Actualizar información sobre planetas" +
                        "\n4) Eliminar informacion sobre planetas" +
                        "\n5) Volver"
            } else {
                textoConsola += "Opciones:" +
                        "\n1) Ingresar un nuevo Sistema Planetario" +
                        "\n2) Ver informacion sobre Sistemas Planetarios" +
                        "\n3) Actualizar información sobre Sistemas Planetarios" +
                        "\n4) Eliminar informacion sobre Sistemas Planetarios" +
                        "\n5) Volver"
            }
            while (!opcionAux) {
                println(textoConsola)
                var opcionCrud = readln().toInt()
                when (opcionCrud) {
                    (1) -> {
                        if (opTabla == 1) {
                            print("Nombre del planeta: ")
                            var nombre = readln()

                            print("Distancia del centro: ")
                            var distancia = readln().toDouble()

                            print("Fecha de descubrimiento (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaDesc: Date =
                                Date(fechaSplit[0].toInt()-1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())

                            print("Tamaño del Planeta: ")
                            var tamaño = readln().toFloat()

                            print("¿Posee Vida? 1.SI 2.NO:")
                            var vida=false
                            var auxVida = readln().toInt()
                            vida = auxVida == 1

                            var newPlaneta =
                                Planeta(Planeta.ObtenerIdPlaneta() + 1, nombre, distancia, fechaDesc, tamaño, vida)
                            newPlaneta.IngresarPlaneta()

                        } else {
                            print("Nombre del Sistema Planetario: ")
                            var nombre = readln()

                            print("Año de formación: ")
                            var año = readln().toFloat()

                            print("Galaxia a la que pertenece: ")
                            var galaxia = readln()

                            print("Tipo de Sistema Planetario: ")
                            var tipo = readln()


                            println("\nPlanetas")
                            Planeta.extraerPlaneta()

                            print("Seleccione los planetas que forman parte del sistema planetario (1,2,...):\n")
                            //Planeta.extraerPlaneta()
                            var stringPlaneta = readLine().toString() //lee por consola
                            var splitPlaneta = stringPlaneta.split(",")
                            var intPlanetasArray = splitPlaneta.map { it.toInt() }.toTypedArray()
                            var listaSistemaPlanetario: ArrayList<Planeta> = SistemaPlanetario.setListaPlanetas(intPlanetasArray)

                            var newSistemaPlanetario = SistemaPlanetario(SistemaPlanetario.ObtenerIdSistemaPlanetario() + 1,
                                nombre, año, galaxia, tipo, listaSistemaPlanetario)
                            newSistemaPlanetario.ingresarSistemaPlanetario(intPlanetasArray.size)

                        }
                    }

                    (2) -> {
                        if (opTabla == 1) {
                            println("Planetas:")
                            Planeta.extraerPlaneta()
                        } else {
                            println("Sistema Planetario")
                            SistemaPlanetario.extraerSistemaPlanetario()
                        }
                    }

                    (3) -> {
                        if (opTabla == 1) {
                            print("Ingrese el Id del planeta para modificar: ")
                            var idPlaneta = readln().toInt()
                            Planeta.ModificarPlaneta(idPlaneta)
                        } else {
                            print("Ingrese el Id del Sistema Planetario para modificar: ")
                            var idSistemaPlanetario = readln().toInt()
                            SistemaPlanetario.modificarSistemaPlanetario(idSistemaPlanetario)
                        }
                    }

                    (4) -> {
                        if (opTabla == 1) {
                            print("Ingrese el Id del planeta para eliminarlo: ")
                            var idPlaneta = readln().toInt()
                            Planeta.BorrarPlaneta(idPlaneta)
                        } else {
                            print("Ingrese el Id del Sistema Planetario para eliminarlo: ")
                            var idSistemaPlanetario = readln().toInt()
                            SistemaPlanetario.borrarSistemaPlanetario(idSistemaPlanetario)
                        }
                    }

                    (5) -> {
                        opcionAux = true
                    }
                }
            }
        } else {
            opcionUsuario = true
        }
    } while (!opcionUsuario)


}