package org.example.monde

import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import org.example.jeu.CombatMonstre
import org.example.joueur
import java.time.LocalDateTime
import kotlin.random.Random

/** Une zone du monde ; les zones forment une chaîne de routes. */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especeMonstre: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedante: Zone? = null
) {
    /** Génère une espèce aléatoire de la zone avec expZone +/- 20 %. */
    fun genereMonstre(): IndividuMonstre {
        val espece = especeMonstre.random()
        val exp = expZone * Random.nextDouble(0.8, 1.2)
        return IndividuMonstre(Random.nextInt(1, Int.MAX_VALUE), espece.nom, espece, null, exp)
    }

    /** Rencontre un monstre sauvage avec le premier monstre encore capable de combattre. */
    fun rencontreMonstre() {
        val actif = joueur.equipeMonstre.firstOrNull { it.pv > 0 }
        if (actif == null) {
            println("Aucun monstre de l'équipe ne peut combattre.")
            return
        }
        CombatMonstre(actif, genereMonstre()).lanceCombat()
    }
}
