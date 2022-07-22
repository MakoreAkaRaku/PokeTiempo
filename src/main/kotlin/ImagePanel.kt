import Resources.TXTBOXSIZE
import Resources.mapaEsp
import Resources.pokedex
import Resources.txtBoxImg
import java.awt.Font
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

open class ImagePanel(val municipios: List<Municipio>) : JPanel(), MouseListener {
    private val PADDING = 10

    //private var indexPkmn: Int = 0
    init {
        addMouseListener(this)
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        if (g != null) {
            g.drawImage(mapaEsp, 0, 0, null)
            municipios.forEach {
                g.drawImage(
                    it.getPokeImage(),
                    (it.posRelX!! * mapaEsp.width).toInt() - pokedex.pokeWidth / 2,
                    (it.posRelY!! * mapaEsp.height).toInt() - pokedex.pokeHeight / 2,
                    null
                )
                g.drawImage(
                    txtBoxImg,
                    (it.posRelX!! * mapaEsp.width).toInt() + PADDING,
                    (it.posRelY!! * mapaEsp.height).toInt() + PADDING,
                    null
                )
                var tmp = it.getTemperaturaMedia().toString().toCharArray()
                g.drawChars(
                    tmp,
                    0,
                    tmp.size,
                    (it.posRelX!! * mapaEsp.width).toInt() + PADDING / 2 + TXTBOXSIZE / 2,
                    (it.posRelY!! * mapaEsp.height).toInt() + TXTBOXSIZE,
                )
            }
        }
    }

    override fun mouseClicked(e: MouseEvent?) = Unit

    override fun mousePressed(e: MouseEvent?) = Unit

    override fun mouseReleased(e: MouseEvent?) = Unit

    override fun mouseEntered(e: MouseEvent?) = Unit

    override fun mouseExited(e: MouseEvent?) = Unit
}