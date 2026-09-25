package org.example.monstre
import java.io.File

/**
 * Représente une espèce de monstre dans le contexte du jeu[cite: 1, 3].
 *
 * Une espèce de monstre définit les caractéristiques générales et communes de tous les monstres
 * appartenant à cette même espèce, incluant leurs statistiques de base, leurs modificateurs
 * et leurs informations descriptives[cite: 1].
 *
 * @property id L'identifiant unique de l'espèce de monstre[cite: 1, 3].
 * @property nom Le nom de l'espèce de monstre[cite: 1, 3].
 * @property type Le type élémentaire associé à l'espèce[cite: 1].
 * @property baseAttaque La statistique d'attaque de base[cite: 1].
 * @property baseDefense La statistique de défense de base[cite: 1].
 * @property baseVitesse La statistique de vitesse de base[cite: 1].
 * @property baseAttaqueSpe La statistique d'attaque spéciale de base[cite: 1].
 * @property baseDefenseSpe La statistique de défense spéciale de base[cite: 1].
 * @property basePv La statistique de points de vie de base[cite: 1].
 * @property modAttaque Le modificateur appliqué à l'attaque[cite: 1].
 * @property modDefense Le modificateur appliqué à la défense[cite: 1].
 * @property modVitesse Le modificateur appliqué à la vitesse[cite: 1].
 * @property modAttaqueSpe Le modificateur appliqué à l'attaque spéciale[cite: 1].
 * @property modDefenseSpe Le modificateur appliqué à la défense spéciale[cite: 1].
 * @property modPv Le modificateur appliqué aux points de vie[cite: 1].
 * @property description La description générale de l'espèce[cite: 1].
 * @property particularites Les particularités ou spécificités de l'espèce[cite: 1].
 * @property caractères Les traits de caractère propres à l'espèce[cite: 1].
 */
class EspeceMonstre(
    var id : Int,
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
    val caractères: String = "",
) {
    fun afficheArt(value: Any) {}


    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}
