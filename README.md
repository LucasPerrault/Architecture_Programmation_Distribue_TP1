# TP1 d'Architecture et Programmation Distribuée

Dans ce TP1 nous avons appris à manipuler des **Sockets en Java**.

**Rappel :** 
Socket est l'élément fondamental de toute connexion réseau côté client qui utilise par défaut le protocole TCP. De plus, elle bénéficie de deux constructeurs vous permettant de l'instancier avec soit un objet InetAddress soit un objet String , représentant le nom de l'hôte serveur avec lequel vous souhaitez communiquer.

## Partie 0 : Utilisation basique des sockets en JAVA.

Application : Nous devions créer un serveur permettant de calculer deux chiffres (int) ainsi qu'un client envoyant deux nombres au serveur. Une fois ceux-ci envoyés, nous devions attendre en réponse la somme de ces deux nombres.

Détails :
- Nous initialisons le *ServerSocket* côté Serveur qui va attendre deux inputs (les deux nombres) d'un client,
- Nous créons un client se connectant le *ServerSocket* initialisé juste avant (même port, même adresse),
- Le client se charge d'envoyer deux nombres en *Output* au serveur,
- Le serveur les reçoit, les calculs et les réenvoie *Output* au client
- Le client lit en *Input* la réponse du serveur

***Plus de détails voir les commentaires dans le code***


## Partie 1 : Transfère d'un fichier .txt.

Application : Le client doit se charger d'envoyer un fichier .txt au serveur. Le serveur lui, doit le lire puis le sauvegarder.

Détails :
- Initialisation *ServerSocket* côté serveur,
- Connexion du client au *Socket* créé juste avant,
- Lecture d'un InputStream côté client,
- Copie du stream dans l'OutputStream côté Client afin de l'envoyer au serveur,
- Lecture de l'InputStream côté Serveur,
- Création d'un fichier côté Serveur,
- Copie du stream dans le fichier créé à l'instant côté Serveur.

***Plus de détails voir les commentaires dans le code***


## Partie 2 : Multi Thread

Application : Créer un serveur multi-thread afin de se connecter avec plusieurs clients (ici Putty).

Détails :
- Initialisation *ServerSocket*,
- Implémentation d'un *Runnable* avec la fonction override *Run*,
- Démarrage du thread par client,
- Connexion avec deux consoles client Putty

## Partie 3 : Chat Group

Application : Créer un chat local avec plusieurs clients. 

[Serveur]
- Initialisation du *ServerSocket*,
- Implémentation d'un *Runnable* avec la fonction override *Run*,
- Démarrage du multi-thread,
- Ajout du client dans la méthode Run,
- Boucle infini permettant de Broadcasté à tous les clients connectés les messages reçus par tel utilisateur,

[Client]
- Instanciation de l'interface chat,
- Connexion au serveur par *Socket*,
- Attendre l'envoi d'un message (clic du bouton),
- Envoyer le message au serveur,
- Boucle infinie sur la réception de réponse du serveur (broadcast multiple).

Bonus : Choix random d'un prénom préenregistré à chaque connexion d'un client.



