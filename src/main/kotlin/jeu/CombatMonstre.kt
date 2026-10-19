package org.example.jeu

import org.example.item.Utilisable
import org.example.monstre.IndividuMonstre
import org.example.joueur

/** Combat entre le monstre du joueur et un monstre sauvage. */
class CombatMonstre(var monstreJoueur: IndividuMonstre, var monstreSauvage: IndividuMonstre) {
    var round: Int = 1
    private var fuite = false
    private var expDistribuee = false

    /** Vrai si aucun monstre de l'équipe n'a plus de PV. */
    fun gameOver(): Boolean = joueur.equipeMonstre.none { it.pv > 0 }

    /** Vrai si le monstre sauvage est vaincu ou capturé. L'EXP est gagnée uniquement en cas de KO. */
    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv <= 0) {
            if (!expDistribuee) {
                println("${monstreJoueur.nom} a gagné !")
                val gainExp = monstreSauvage.exp * 0.20
                monstreJoueur.exp += gainExp
                println("${monstreJoueur.nom} gagne $gainExp exp")
                expDistribuee = true
            }
            return true
        }
        return monstreSauvage.entraineur == joueur
    }

    /** Le monstre sauvage attaque uniquement s'il est toujours debout. */
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) monstreSauvage.attaquer(monstreJoueur)
    }

    /** Permet d'attaquer, utiliser un objet, changer de monstre, fuir ou attendre. */
    fun actionJoueur(): Boolean {
        if (gameOver()) return false
        if (monstreJoueur.pv <= 0) {
            val remplaçant = joueur.equipeMonstre.firstOrNull { it.pv > 0 }
            if (remplaçant != null) monstreJoueur = remplaçant else return false
        }
        println("1 - Attaquer | 2 - Objet | 3 - Changer | 4 - Fuir | 5 - Attendre")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> monstreJoueur.attaquer(monstreSauvage)
            2 -> {
                if (joueur.sacAItems.isEmpty()) {
                    println("Le sac est vide.")
                    return true
                }
                joueur.sacAItems.forEachIndexed { index, item -> println("${index + 1} - ${item.nom}") }
                print("Quel objet ? ")
                val index = (readlnOrNull()?.toIntOrNull() ?: 0) - 1
                if (index !in joueur.sacAItems.indices) {
                    println("Objet inconnu.")
                    return true
                }
                val item = joueur.sacAItems[index]
                if (item is Utilisable) {
                    val reussi = item.utiliser(monstreSauvage)
                    if (reussi) return false
                } else println("Cet objet n'est pas utilisable en combat.")
            }
            3 -> {
                joueur.equipeMonstre.forEachIndexed { index, monstre ->
                    println("${index + 1} - ${monstre.nom} (${monstre.pv}/${monstre.pvMax} PV)")
                }
                print("Choisir un monstre : ")
                val index = (readlnOrNull()?.toIntOrNull() ?: 0) - 1
                if (index in joueur.equipeMonstre.indices && joueur.equipeMonstre[index].pv > 0) {
                    monstreJoueur = joueur.equipeMonstre[index]
                } else println("Ce monstre ne peut pas combattre.")
            }
            4 -> {
                println("Vous prenez la fuite !")
                fuite = true
                return false
            }
            5 -> println("Vous attendez.")
            else -> println("Choix invalide.")
        }
        return true
    }

    /** Affiche le niveau, les PV et les ASCII arts des deux monstres. */
    fun afficheCombat() {
        println("======== Début Round : $round ========")
        println("${monstreSauvage.nom} - Niveau ${monstreSauvage.niveau} - PV ${monstreSauvage.pv}/${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt(true))
        println(monstreJoueur.espece.afficheArt(false))
        println("${monstreJoueur.nom} - Niveau ${monstreJoueur.niveau} - PV ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
    }

    /** Fait agir le monstre le plus rapide en premier. */
    fun jouer() {
        afficheCombat()
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            if (!actionJoueur()) return
            if (!joueurGagne()) actionAdversaire()
        } else {
            actionAdversaire()
            if (!gameOver() && !joueurGagne()) {
                if (!actionJoueur()) return
            }
        }
    }

    /** Enchaîne les rounds et restaure les PV de l'équipe en cas de défaite. */
    fun lanceCombat() {
        while (!fuite && !gameOver() && !joueurGagne()) {
            jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }
}
