package org.example

import org.example.dresseur.Entraineur
import org.example.monstre.EspeceMonstre
import org.example.monde.Zone
import org.example.item.MonsterKube
import org.example.jeu.Partie

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2, "Regis", 200)

// Bestiaire original Pokelolo : huit espèces élémentaires.
var espece_Pyron = EspeceMonstre(1, "Pyron", "Feu", 13, 8, 11, 15, 8, 52, 10.0, 5.0, 8.0, 12.0, 6.0, 24.0, "Stickman incandescent qui concentre les flammes dans ses poings.", "Sa chevelure devient une torche quand il combat.", "Fougueux, courageux, impatient")
var espece_Aquor = EspeceMonstre(2, "Aquor", "Eau", 9, 13, 9, 12, 14, 63, 7.0, 11.0, 7.0, 10.0, 12.0, 34.0, "Stickman aquatique dont les bras manipulent des courants d’eau.", "Des gouttes orbitent autour de sa tête.", "Patient, protecteur, calme")
var espece_Sylvar = EspeceMonstre(3, "Sylvar", "Nature", 10, 11, 10, 11, 12, 59, 8.0, 9.0, 8.0, 9.0, 10.0, 30.0, "Stickman forestier parcouru de lianes et de feuilles.", "Ses branches repoussent après chaque combat.", "Curieux, loyal, paisible")
var espece_Voltix = EspeceMonstre(4, "Voltix", "Foudre", 12, 8, 16, 13, 8, 48, 10.0, 6.0, 14.0, 10.0, 6.0, 22.0, "Stickman électrique qui se déplace par bonds fulgurants.", "Ses cheveux crépitent à chaque mouvement.", "Vif, malicieux, impatient")
var espece_Granox = EspeceMonstre(5, "Granox", "Terre", 12, 16, 5, 8, 13, 70, 9.0, 14.0, 4.0, 6.0, 11.0, 36.0, "Stickman minéral protégé par des blocs de roche.", "Ses poings deviennent aussi durs que la pierre.", "Solide, stoïque, fidèle")
var espece_Cryon = EspeceMonstre(6, "Cryon", "Glace", 11, 10, 12, 14, 10, 53, 8.0, 8.0, 10.0, 12.0, 8.0, 26.0, "Stickman givré aux membres hérissés de cristaux.", "Il laisse une fine trace de givre sur son passage.", "Réfléchi, précis, réservé")
var espece_Noxis = EspeceMonstre(7, "Noxis", "Ombre", 12, 8, 14, 14, 9, 49, 10.0, 6.0, 12.0, 11.0, 7.0, 23.0, "Stickman nocturne enveloppé de brumes violettes.", "Sa silhouette disparaît dans les zones sombres.", "Discret, rusé, indépendant")
var espece_Solarys = EspeceMonstre(8, "Solarys", "Lumière", 11, 11, 11, 13, 12, 57, 9.0, 9.0, 9.0, 11.0, 10.0, 29.0, "Stickman rayonnant entouré d’un halo doré.", "Son éclat devient plus intense lorsqu’il se concentre.", "Généreux, assuré, serein")

var route1 = Zone(1, "Route 1", 100, mutableListOf(espece_Pyron, espece_Aquor, espece_Sylvar))
var route2 = Zone(2, "Route 2", 125, mutableListOf(espece_Voltix, espece_Granox, espece_Cryon))
var route3 = Zone(3, "Route 3", 150, mutableListOf(espece_Noxis, espece_Solarys, espece_Voltix, espece_Granox, espece_Cryon))
var kube1 = MonsterKube(1, "MonsterKube", "Permet de capturer un monstre sauvage.", 30.0)

/** Débute une nouvelle partie et demande le nom du joueur. */
fun nouvellePartie(): Partie {
    println("Bienvenue dans Pokelolo !")
    print("Quel est votre nom ? ")
    val nom = readlnOrNull()?.trim().orEmpty()
    if (nom.isNotEmpty()) joueur.nom = nom
    return Partie(1, joueur, route1)
}

fun main() {
    route1.zoneSuivante = route2
    route2.zonePrecedante = route1
    route2.zoneSuivante = route3
    route3.zonePrecedante = route2
    joueur.sacAItems.add(kube1)
    val partie = nouvellePartie()
    partie.choixStarter()
    partie.jouer()
}

/** Applique la couleur ANSI demandée au message. */
fun changeCouleur(message: String, couleur: String = ""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert", "orange" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[38;5;94m"
        else -> ""
    }
    return "$codeCouleur$message$reset"
}
