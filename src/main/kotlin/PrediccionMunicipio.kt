import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

data class Request(
    val descripcion: String,
    val estado: String,
    val datos: String,
    val metadatos: String
)

@Serializable
@JsonIgnoreProperties("origen", "elaborado", "nombre", "provincia", "id", "version")
data class DatosResponse(
    val prediccion: PrediccionResponse
)

@Serializable
data class PrediccionResponse(
    val dia: List<DatosDiaResponse>
)

@Serializable
@JsonIgnoreProperties("cotaNieveProv", "viento", "rachaMax", "sensTermica", "humedadRelativa", "uvMax")
data class DatosDiaResponse(
    val estadoCielo: List<EstadoCieloResponse>,
    val fecha: String,
    val temperatura: TemperaturaResponse,
    val probPrecipitacion: List<ProbabilidadPrecipitacionResponse>
)

@Serializable
data class TemperaturaResponse(
    val maxima: Int,
    val minima: Int,
    val dato: List<TemperaturaDatosResponse>
)

@Serializable
data class EstadoCieloResponse(
    val value: String,
    val periodo: String?,
    val descripcion: String
) {
    companion object {
        val mapaDePosiblesDescripcionesAPokemon = Pokemon4thGen.run {
            mapOf(
                "" to missigno,
                "Despejado" to solrock,
                "Despejado noche" to solrock,
                "Poco nuboso" to swablu,
                "Poco nuboso noche" to swablu,
                "Intervalos nubosos" to swablu,
                "Intervalos nubosos noche" to swablu,
                "Nuboso" to swablu,
                "Nuboso noche" to swablu,
                "Muy nuboso" to swablu,
                "Cubierto" to swablu,
                "Nubes altas" to altaria,
                "Nubes altas noche" to altaria,
                "Intervalos nubosos con lluvia escasa" to swablu,
                "Intervalos nubosos con lluvia escasa noche" to swablu,
                "Nuboso con lluvia escasa" to swablu,
                "Nuboso con lluvia escasa noche" to swablu,
                "Muy nuboso con lluvia escasa" to swablu,
                "Cubierto con lluvia escasa" to swablu,
                "Intervalos nubosos con lluvia" to pelipper,
                "Intervalos nubosos con lluvia noche" to pelipper,
                "Nuboso con lluvia" to pelipper,
                "Nuboso con lluvia noche" to pelipper,
                "Muy nuboso con lluvia" to pelipper,
                "Cubierto con lluvia" to pelipper,
                "Intervalos nubosos con nieve escasa" to snover,
                "Intervalos nubosos con nieve escasa noche" to snover,
                "Nuboso con nieve escasa" to snorunt,
                "Nuboso con nieve escasa noche" to snorunt,
                "Muy nuboso con nieve escasa" to froslass,
                "Cubierto con nieve escasa" to froslass,
                "Intervalos nubosos con nieve" to abomasnow,
                "Intervalos nubosos con nieve noche" to abomasnow,
                "Nuboso con nieve" to glaceon,
                "Nuboso con nieve noche" to glaceon,
                "Muy nuboso con nieve" to glaceon,
                "Cubierto con nieve" to glaceon,
                "Intervalos nubosos con tormenta" to zapdos,
                "Intervalos nubosos con tormenta noche" to zapdos,
                "Nuboso con tormenta" to zapdos,
                "Nuboso con tormenta noche" to zapdos,
                "Muy nuboso con tormenta" to zapdos,
                "Cubierto con tormenta" to zapdos,
                "Intervalos nubosos con tormenta y lluvia escasa" to zapdos,
                "Intervalos nubosos con tormenta y lluvia escasa noche" to zapdos,
                "Nuboso con tormenta y lluvia escasa " to zapdos,
                "Nuboso con tormenta y lluvia escasa noche" to zapdos,
                "Muy nuboso con tormenta y lluvia escasa" to zapdos,
                "Cubierto con tormenta y lluvia escasa" to zapdos,
                "Niebla" to altaria,
                "Bruma" to altaria,
                "Calima" to tyranitar
            )
        }


    }
}

@Serializable
data class TemperaturaDatosResponse(
    val value: Int,
    val hora: Int
)


@Serializable
data class ProbabilidadPrecipitacionResponse(
    val value: Int,
    val periodo: String?
)