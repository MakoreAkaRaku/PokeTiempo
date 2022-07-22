import Resources.pokedex
import java.awt.Image
import java.util.*

data class Municipio(
    val latitud: String,
    val id_old: String?,
    val url: String,
    val latitud_dec: String,
    val altitud: String,
    val capital: String,
    val num_hab: String,
    val zona_comarcal: String,
    val destacada: String,
    val nombre: String,
    val longitud_dec: String,
    val id: String,
    val longitud: String
) {
    var posRelX: Float? = null
    var posRelY: Float? = null
    var prediccion: PrediccionResponse? = null
    val diaAPredecir = Date().toApiString()

    fun getPokeImage(): Image {
        var estadoCielo = getEstadoCielo()
        val pokemonRef = EstadoCieloResponse.mapaDePosiblesDescripcionesAPokemon[estadoCielo] ?: Pokemon4thGen.missigno
        return pokedex[pokemonRef]
    }

    fun getEstadoCielo(): String {
        var predManana = getPrediccionManana()
        var estadosC = predManana.estadoCielo.filter { !it.descripcion.isEmpty() }
        var estadoCVal = estadosC.toSet()
        var cnts = Array<Int>(estadoCVal.size, init = { 0 })
        estadosC.forEach {
            for (i in estadoCVal.indices) {
                if (estadoCVal.elementAt(i).descripcion == it.descripcion) {
                    cnts[i]++
                }
            }
        }
        var indexStored = 0
        var valorIndStored = -1
        for (i in estadoCVal.indices) {
            if (valorIndStored <= cnts[i]) {
                valorIndStored = cnts[i]
                indexStored = i
            }
        }
        return estadoCVal.elementAt(indexStored).descripcion
    }

    fun getTemperaturaMedia(): Int {
        var predManana = getPrediccionManana()
        var temperatura = 0
        var cnt = 0
        predManana.temperatura.dato.forEach {
            temperatura += it.value
            cnt++
        }
        if (cnt != 0)
            temperatura /= cnt
        return temperatura
    }

    /**
     * Returns Tomorrow's Overcast about the City
     */
    private fun getPrediccionManana(): DatosDiaResponse {
        return prediccion?.dia?.find {
            it.fecha == diaAPredecir
        }!!
    }
}