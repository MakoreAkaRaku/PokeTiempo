import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Pokedex {
    val pokemon4thGen = Pokemon4thGen
    val coordsImage: Array<Vec2d>
    val pokeImage: BufferedImage
    val pokeWidth: Int
    val pokeHeight: Int
    val numTotalPokemons: Int
    val missignoImg: BufferedImage
    private val pokePath = "src/main/resources/Pokemon/4thGen.png"
    private val missignoPath = "src/main/resources/Pokemon/missigno.png"

    init {
        var pokeFile = File(pokePath)
        pokeImage = ImageIO.read(pokeFile)
        var missignoFile = File(missignoPath)
        missignoImg = ImageIO.read(missignoFile)
        val pokePerCol = 18
        val pokePerRow = 28
        pokeHeight = pokeImage.height / pokePerCol
        pokeWidth = pokeImage.width / pokePerRow
        val nonPokeLasRow = pokePerRow - 17
        numTotalPokemons = pokePerCol * pokePerRow - nonPokeLasRow
        coordsImage = Array<Vec2d>(numTotalPokemons) {
            val y = pokeHeight * (it / pokePerRow)
            val x = pokeWidth * it % pokeImage.width
            Vec2d(x.toDouble(), y.toDouble())
        }
    }

    operator fun get(index: Int): Image {
        //Si es missigno, lo tratamos a parte
        if (index == -1) {
            return missignoImg.getScaledInstance(pokeWidth, pokeHeight, Image.SCALE_DEFAULT)
        }

        var vectTemp = coordsImage[index]
        return pokeImage.getSubimage(vectTemp.x.toInt(), vectTemp.y.toInt(), pokeWidth, pokeHeight)
    }

}