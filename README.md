**Jeu de GO**

_Sprint 1 à faire :
  boardsize, showboard, quit_

Sprint 1 remarque :
  - Accepter ID numero de commande. Exemple: 1 quit
  - Showboard crash. La taille par défaut devrait etre 19
  - Un seul espace entre les colones (respecter espacement)
  - Les messages d'erreur devrait etre standard donc en anglais 
  - Enlever la colone I ou L(verifier).

_Sprint 2 à faire : 
  capturer, jouer_

Sprint 2 remarque :
  - méthode **_liberties_** pour le nombre de liberte d'une case --> récursive qui prend une case en paramètre
  - méthode **_record_** --> "struct en C"

 _Sprint 3 à faire (noté):_
  _player white human
   player black random_
   pas de suicide, pas de ko



   **Compte rendu**


   
Le projet "Go" vise à développer un programme permettant à deux joueurs, qu'ils soient humains ou des programmes, de s'affronter dans une partie de Go.L'une des premières étapes de la partie est la définition de la taille du plateau de jeu avec la commande boardsize, suivie de l'affichage du plateau à l'aide de showboard. Lors de l'interaction avec le programme, l'utilisateur peut fournir un numéro de commande facultatif. Ce numéro est tapé par l'utilisateur et n'est pas un prompt. Il sera pris en compte, mémorisé, et utilisé obligatoirement dans la réponse du programme s'il est présent dans la commande.


Les fonctionnalitées/commandes opérationnelles : 

 - quit;
 - boardsize;
 - showboard;
 - capturer pion seul/groupe dans les bordures et dans les emplacements "normaux"
 - interdiction du suicide 
 - implémentation IJoueur par des classes : ConsoleJoueur, RandomJoueur
 - possibilité de jouer : console vs console, console vs random, random vs random

A noter que nous aurions pu optimiser le code mais faute de temps nous nous avons privilégiés le rendu à temps plutôt que de rendre vraiment en retard.

