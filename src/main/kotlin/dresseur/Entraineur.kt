package org.example.dresseur

import org.example.item.Item
import org.example.monstre.IndividuMonstre
import java.time.LocalDate

/** Représente un dresseur (par exemple Ondine) et ses monstres. */
class Entraineur(
    var id: Int,
    var nom: String,
    var argents: Int,
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var sacAItems: MutableList<Item> = mutableListOf()
) {
    /** Affiche le nom du dresseur et son argent. */
    fun afficheDetail() {
        println("Dresseur : $nom")
        println("Argents : $argents")
    }
}
