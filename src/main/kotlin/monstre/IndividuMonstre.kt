package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Représente un individu spécifique (un monstre) dans le jeu.
 *
 * @property id L'identifiant unique du monstre.
 * @property nom Le nom ou surnom donné au monstre.
 * @property espece L'espèce à laquelle ce monstre appartient (définit ses statistiques de base).
 * @property entraineur L'entraîneur actuel du monstre, ou `null` s'il s'agit d'un monstre sauvage.
 * @param expInit Les points d'expérience initiaux du monstre au moment de sa création.
 */
class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    entraineur: Entraineur? = null,
    var expInit: Double = 0.0
)

/**
 * L'entraîneur actuel du monstre. Sa modification met à jour le champ correspondant.
 */
{
    var entraineur = entraineur
    get() = field
    set(value) {
        field = value
    }

    /**
     * L'entraîneur actuel du monstre[cite: 2].
     */

    /**
     * Le niveau actuel du monstre[cite: 2].
     */

    /**
     * La statistique d'attaque physique du monstre[cite: 2].
     */

    /**
     * La statistique de défense physique interne du monstre[cite: 2].
     */

    /**
     * Accesseur public pour la statistique de défense physique[cite: 2].
     */

    /**
     * La statistique de vitesse du monstre[cite: 3].
     */

    /**
     * La statistique d'attaque spéciale du monstre[cite: 3].
     */

    /**
     * La statistique de défense spéciale interne du monstre[cite: 3].
     */

    /**
     * Accesseur public pour la statistique de défense spéciale[cite: 3].
     */

    /**
     * Les points de vie maximum du monstre[cite: 3].
     */

    /**
     * Le potentiel unique du monstre, généré aléatoirement entre 0.5 et 2.0[cite: 3].
     */

    /**
     * Les points d'expérience actuels du monstre[cite: 3]. L'assignation d'une nouvelle valeur déclenche automatiquement une montée de niveau si l'expérience dépasse le palier requis[cite: 3].
     */

    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = espece.basePv + (-5..5).random()
    val potentiel: Double = Random.nextDouble(0.5, 2.0)
    var exp: Double = 0.0
    get() = field
    set(value) {
        field = value
        val estNiveau1 = niveau == 1
        while (field >= palierExp(niveau)) {
            levelUp()
            if (estNiveau1 == false) {
                println("Le monstre $nom est maintenant niveau $niveau !")
            }
        }
    }


    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        set(nouveauPv) {
            field = when {
                nouveauPv < 0 -> 0
                nouveauPv > pvMax -> pvMax
                else -> nouveauPv
            }
        }

    init {
        this.exp = expInit
    }


    fun palierExp(niveau: Int): Double {
        return 100.0 * (niveau - 1).toDouble().pow(2.0)
    }


    /**
     * Initialise l'expérience du monstre plicateur modifie davec la valeur fournie dans le constructeur[cite: 4].
     */

    /**
     * Calcule le nombre total de points d'expérience requis pour atteindre un niveau donné[cite: 4].
     *
     * @param niveau Le niveau cible pour lequel on veut connaître l'expérience requise[cite: 4].
     * @return Les points d'expérience nécessaires[cite: 4].
     */

    fun levelUp() {
        niveau += 1
        attaque += (espece.modAttaque * potentiel).roundToInt() + (-2..2).random()
        defense += (espece.modDefense * potentiel).roundToInt() + (-2..2).random()
        vitesse += (espece.modVitesse * potentiel).roundToInt() + (-2..2).random()
        attaqueSpe += (espece.modAttaqueSpe * potentiel).roundToInt() + (-2..2).random()
        defenseSpe += (espece.modDefenseSpe * potentiel).roundToInt() + (-2..2).random()

        val gainPvMax = (espece.modPv * potentiel).roundToInt() + (-5..5).random()
        pvMax += gainPvMax
        pv += gainPvMax
    }
}