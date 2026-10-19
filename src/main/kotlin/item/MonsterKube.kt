package org.example.item

import org.example.monstre.IndividuMonstre
import kotlin.random.Random

/** Objet de capture dont la chance dépend des PV de la cible. */
class MonsterKube(id: Int, nom: String, description: String, var chanceCapture: Double) :
    Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez un MonsterKube !")
        if (cible.entraineur != null) {
            println("Ce monstre appartient déjà à un dresseur.")
            return false
        }
        val joueur = org.example.joueur
        val ratioVie = cible.pv.toDouble() / cible.pvMax
        val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(5.0)
        if (Random.nextDouble(100.0) >= chanceEffective) {
            println("Le monstre s'est échappé !")
            return false
        }
        println("${cible.nom} a été capturé !")
        cible.renommer()
        if (joueur.equipeMonstre.size < 6) joueur.equipeMonstre.add(cible)
        else joueur.boiteMonstre.add(cible)
        cible.entraineur = joueur
        return true
    }
}
