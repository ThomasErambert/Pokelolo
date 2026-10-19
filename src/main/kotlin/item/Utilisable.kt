package org.example.item

import org.example.monstre.IndividuMonstre

/** Contrat des objets utilisables sur un individu monstre. */
interface Utilisable {
    /** Renvoie vrai si l'objet a eu un effet sur la cible. */
    fun utiliser(cible: IndividuMonstre): Boolean
}
