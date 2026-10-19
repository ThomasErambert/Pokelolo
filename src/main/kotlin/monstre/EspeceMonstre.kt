package org.example.monstre

import java.io.File

/** Espèce de monstre : ses caractéristiques communes et sa description (255 caractères maximum). */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caracteres: String = ""
) {
    /** Renvoie l'art ASCII de face ou de dos. */
    fun afficheArt(deFace: Boolean = true): String {
        val fichier = if (deFace) "front" else "back"
        val art = File("src/main/resources/art/${nom.lowercase()}/$fichier.txt").readText()
        return art.replace("/", "∕").replace("\\u001B", "\u001B")
    }
}
