import java.awt.Graphics
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JPanel

class GlosarioPanel : JPanel() {
    private val INNERPADDING = 12
    private val prediccion: String

    init {
        var dia = Date(System.currentTimeMillis() + Resources.valorDIA)
        var sdf = SimpleDateFormat("dd MMMM yyyy", Locale("es", "ES"))
        prediccion = "Predicción para ${sdf.format(dia)}"
        println(prediccion)
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        //var posTextH = INNERPADDING
        //var posTextW = INNERPADDING + width / 2 - textSize
    }
}