import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import java.security.cert.X509Certificate
import java.util.*
import com.fasterxml.jackson.module.kotlin.*
import java.awt.BorderLayout
import java.io.File
import javax.net.ssl.X509TrustManager
import javax.swing.JFrame
import javax.swing.border.Border

lateinit var imageJPanel: ImagePanel
lateinit var mainWindow: JFrame


suspend fun main() {
    var httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }
        engine {
            https {
                trustManager = object : X509TrustManager {
                    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

                    override fun getAcceptedIssuers(): Array<X509Certificate>? = null
                }
            }
        }
    }
    val mapper = jacksonObjectMapper()
    var municipiosReq = Resources.urlMunicipios + Resources.fillApiKey
    var mEspana = mapper.readValue<List<Municipio>>(httpClient.get<String>(municipiosReq))
    val capProvincia = mEspana.filter { it.nombre in Resources.capitalesComunidadesAutonomas }
    var coordsM = mapper.readValue<List<CoordMunicipio>>(File(Resources.coordsPath))
    var diaPredecir = Date().toApiString()

    capProvincia
        .forEach { m ->
            coordsM.forEach {
                if (it.nombreM == m.nombre) {
                    m.posRelX = it.posRelX
                    m.posRelY = it.posRelY
                }
            }
            var reqResp =
                mapper.readValue<Request>(httpClient.get<String>(Resources.urlForecast + m.id.split("id")[1] + Resources.fillApiKey))
            var data = mapper.readValue<List<DatosResponse>>(httpClient.get<String>(reqResp.datos))
            m.prediccion = data[0].prediccion

        }
    imageJPanel = ImagePanel(capProvincia)
    mainWindow = createWindow()
    mainWindow.isVisible = true
}

fun createWindow(): JFrame {
    var frame = JFrame().apply {
        this.layout = BorderLayout()
        setSize(1000, 780)
        add(imageJPanel, BorderLayout.CENTER)
        add(GlosarioPanel(), BorderLayout.WEST)
        isResizable = false
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    return frame
}