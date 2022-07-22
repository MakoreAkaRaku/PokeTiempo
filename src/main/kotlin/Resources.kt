import java.awt.Image
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.imageio.ImageIO

object Resources { // DATOS ESTADO CIELO http://www.aemet.es/es/eltiempo/prediccion/municipios/ayuda
    val valorDIA = 24*60*60*1000
    val coordsPath = "src/main/resources/coordsMunicipios/coords.json"
    private val mapPath = "src/main/resources/Mapas/espanaCCAA2png.png"
    private val txtBoxPath = "src/main/resources/Pokemon/text-box.png"
    val TXTBOXSIZE = 25
    val txtBoxImg = ImageIO.read(File(txtBoxPath)).getScaledInstance(TXTBOXSIZE, TXTBOXSIZE, Image.SCALE_REPLICATE)
    val mapaEsp = ImageIO.read(File(mapPath))

    // TODO: Falta verificar que existeixen
//    val capitalesProvincias = arrayOf(
//        "Álava","Albacete","Alicante","Almería","Asturias","Ávila","Badajoz","Barcelona","Burgos",
//        "Cáceres","Cádiz","Cantabria","Castellón","Ciudad Real","Córdoba","Cuenca","Girona","Granada",
//        "Guadalajara","Guipúzcoa","Huelva","Huesca","Islas Baleares","Jaén","A Coruña","La Rioja","Las Palmas",
//        "León","Lleida","Lugo","Madrid","Málaga","Murcia","Navarra","Ourense","Palencia","Pontevedra","Salamanca",
//        "Santa Cruz de Tenerife","Segovia","Sevilla","Soria","Tarragona","Teruel","Toledo","Valencia","Valladolid",
//        "Vizcaya","Zamora","Zaragoza"
//    )
    val capitalesComunidadesAutonomas =
        arrayOf(
            "Sevilla",
            "Zaragoza",
            "Oviedo",
            "Palma de Mallorca",
            "Santa Cruz de Tenerife",
            "Santander",
            "Toledo",
            "Valladolid",
            "Barcelona",
            "Ceuta",
            "Mérida",
            "Santiago de Compostela",
            "Logroño",
            "Madrid",
            "Melilla",
            "Murcia",
            "Pamplona/Iruña",
            "Valencia",
            "Vitoria-Gasteiz"
        )
    val pokedex = Pokedex()
    val urlMunicipios =
        "https://opendata.aemet.es/opendata/api/maestro/municipios"
    val urlForecast = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/"
    val fillApiKey =
        "/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJjcm9tYW5jb2xvbUBnbWFpbC5jb20iLCJqdGkiOiIzOTQwYzFhYi1lZGZjLTQ0ZjEtYWY5Zi04NDQ3M2RmYTViZTkiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTYzODM1NzMyMiwidXNlcklkIjoiMzk0MGMxYWItZWRmYy00NGYxLWFmOWYtODQ0NzNkZmE1YmU5Iiwicm9sZSI6IiJ9.cCZUCgKImBSeRG8P7EDXWFM441xL9RULF0nmwZD1POM"
}

fun Date.toApiString(): String {
    var d = Date()
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
    d.time = System.currentTimeMillis() + Resources.valorDIA// The next day (tomorrow)
    return "${sdf.format(d)}T00:00:00"
}