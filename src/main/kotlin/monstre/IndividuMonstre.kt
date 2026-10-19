package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/** Un individu unique d'une espèce ; deux individus peuvent appartenir à la même espèce, par exemple Canaros. */
class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double = 0.0
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = espece.basePv + (-5..5).random()
    val potentiel: Double = Random.nextDouble(0.5, 2.0)

    /** L'expérience cumulée déclenche automatiquement les montées de niveau. */
    var exp: Double = 0.0
        set(value) {
            field = value
            val niveauInitial = niveau
            while (field > 0 && field >= palierExp(niveau)) {
                levelUp()
                if (niveauInitial != 1) println("Le monstre $nom est maintenant niveau $niveau !")
            }
        }

    /** Points de vie actuels, toujours compris entre zéro et pvMax. */
    var pv: Int = pvMax
        set(value) { field = value.coerceIn(0, pvMax) }

    init { exp = expInit }

    /** Expérience cumulée nécessaire pour atteindre un niveau. */
    fun palierExp(niveau: Int): Double = 100.0 * (niveau - 1).toDouble().pow(2.0)

    /** Augmente le niveau et les statistiques selon les modificateurs de l'espèce. */
    fun levelUp() {
        niveau++
        attaque += (espece.modAttaque * potentiel).roundToInt() + (-2..2).random()
        defense += (espece.modDefense * potentiel).roundToInt() + (-2..2).random()
        vitesse += (espece.modVitesse * potentiel).roundToInt() + (-2..2).random()
        attaqueSpe += (espece.modAttaqueSpe * potentiel).roundToInt() + (-2..2).random()
        defenseSpe += (espece.modDefenseSpe * potentiel).roundToInt() + (-2..2).random()
        val gainPv = (espece.modPv * potentiel).roundToInt() + (-5..5).random()
        pvMax += gainPv
        pv += gainPv
    }

    /** Attaque un autre monstre, avec au moins un point de dégât. */
    fun attaquer(cible: IndividuMonstre) {
        val degats = (attaque - cible.defense / 2).coerceAtLeast(1)
        cible.pv -= degats
        println("$nom attaque ${cible.nom} et inflige $degats dégâts !")
    }

    /** Propose de renommer le monstre. Une saisie vide conserve son nom. */
    fun renommer() {
        print("Nouveau nom pour $nom (Entrée pour conserver) : ")
        val nouveauNom = readlnOrNull()?.trim().orEmpty()
        if (nouveauNom.isNotEmpty()) nom = nouveauNom
    }

    /** Affiche les statistiques et l'art ASCII du monstre. */
    fun afficheDetail() {
        println("$nom (${espece.nom}) - Niveau $niveau")
        println("Type : ${espece.type} | Exp : $exp | PV : $pv/$pvMax")
        println("Attaque : $attaque | Défense : $defense | Vitesse : $vitesse")
        println("Attaque spé. : $attaqueSpe | Défense spé. : $defenseSpe")
        println(espece.afficheArt())
    }
}
