package org.example.monde

import org.example.monstre.EspeceMonstre

/**
 * Représente un lieu spécifique dans le monde du jeu (comme une route, une caverne ou une mer)[cite: 7].
 *
 * Une zone est un endroit où le joueur peut chercher et rencontrer des monstres sauvages.
 * Elle permet également la navigation en étant reliée à une zone suivante ou précédente[cite: 7].
 *
 * @property id L'identifiant unique de la zone (entier)[cite: 7].
 * @property nom Le nom de la zone (chaîne de caractères)[cite: 7].
 * @property expZone L'expérience de base fournie par la zone (entier)[cite: 7].
 * @property especesMonstres La liste des espèces de monstres sauvages pouvant apparaître dans cette zone. Par défaut, c'est une liste mutable vide[cite: 7].
 * @property zoneSuivante La zone adjacente suivante à laquelle le joueur peut accéder, ou `null` s'il n'y en a pas[cite: 7].
 * @property zonePrecedante La zone adjacente précédente à laquelle le joueur peut revenir, ou `null` s'il n'y en a pas[cite: 7].
 */

class Zone (
    var id : Int,
    var nom : String,
    var expZone : Int,
    var especeMonstre: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante : Zone? = null,
    var zonePrecedante : Zone? = null,
    //TODO genereMonstre()
    //TODO rencontreMonstre
)