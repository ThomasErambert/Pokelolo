package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre
import org.example.espece_Sylvar
import org.example.espece_Pyron
import org.example.espece_Aquor

/** Une partie d'un joueur dans une zone. Plusieurs objets Partie peuvent coexister dans le même jeu. */
class Partie(var id: Int, var joueur: Entraineur, var zone: Zone) {
    /** Propose les trois starters et ajoute celui choisi à l'équipe. */
    fun choixStarter() {
        val monstres = listOf(
            IndividuMonstre(1, "Sylvar", espece_Sylvar, null, 1500.0),
            IndividuMonstre(2, "Pyron", espece_Pyron, null, 1500.0),
            IndividuMonstre(3, "Aquor", espece_Aquor, null, 1500.0)
        )
        monstres.forEachIndexed { index, monstre ->
            println("${index + 1} - ${monstre.nom}")
            monstre.afficheDetail()
        }
        var choix: Int?
        do {
            print("Choisir votre starter (1-3) : ")
            choix = readlnOrNull()?.toIntOrNull()
        } while (choix == null || choix !in 1..3)
        val starter = monstres[choix - 1]
        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur
    }

    /** Echange deux positions occupées dans la liste de l'équipe. */
    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size < 2) {
            println("Il faut au moins deux monstres dans l'équipe.")
            return
        }
        joueur.equipeMonstre.forEachIndexed { i, monstre -> println("${i + 1} - ${monstre.nom}") }
        print("Position actuelle : ")
        val a = (readlnOrNull()?.toIntOrNull() ?: 0) - 1
        print("Nouvelle position : ")
        val b = (readlnOrNull()?.toIntOrNull() ?: 0) - 1
        if (a !in joueur.equipeMonstre.indices || b !in joueur.equipeMonstre.indices) {
            println("Position invalide.")
            return
        }
        val monstre = joueur.equipeMonstre[a]
        joueur.equipeMonstre[a] = joueur.equipeMonstre[b]
        joueur.equipeMonstre[b] = monstre
    }

    /** Consulte les monstres et propose d'en modifier l'ordre. */
    fun examineEquipe() {
        while (true) {
            joueur.equipeMonstre.forEachIndexed { index, monstre ->
                println("${index + 1} - ${monstre.nom} (${monstre.pv}/${monstre.pvMax} PV)")
            }
            println("Numéro : détails | m : modifier l'ordre | q : retour")
            val choix = readlnOrNull()?.trim()?.lowercase() ?: return
            when (choix) {
                "q" -> return
                "m" -> modifierOrdreEquipe()
                else -> {
                    val index = (choix.toIntOrNull() ?: 0) - 1
                    if (index in joueur.equipeMonstre.indices) joueur.equipeMonstre[index].afficheDetail()
                    else println("Choix invalide.")
                }
            }
        }
    }

    /** Menu principal : rencontrer, examiner l'équipe, avancer ou reculer dans les zones. */
    fun jouer() {
        while (true) {
            println("Zone actuelle : ${zone.nom}")
            println("1 - Rencontrer un monstre sauvage")
            println("2 - Examiner l'équipe de monstres")
            println("3 - Aller à la zone suivante")
            println("4 - Aller à la zone précédente")
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> zone.rencontreMonstre()
                2 -> examineEquipe()
                3 -> if (zone.zoneSuivante != null) zone = zone.zoneSuivante!! else println("Pas de zone suivante.")
                4 -> if (zone.zonePrecedante != null) zone = zone.zonePrecedante!! else println("Pas de zone précédente.")
                null -> return
                else -> println("Choix invalide.")
            }
        }
    }
}
