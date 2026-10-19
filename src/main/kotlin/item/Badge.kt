package org.example.item

import org.example.dresseur.Entraineur

/** Badge obtenu auprès d'un champion. */
class Badge(id: Int, nom: String, description: String, var champion: Entraineur) : Item(id, nom, description)
