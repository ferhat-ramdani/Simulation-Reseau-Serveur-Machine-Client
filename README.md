# Into
<p>
  Dans ce projet, on simule un réseau composé de serveurs, machines calculatrices et clients. Les machines calculatrices sont des machines utilisé par
  le réseau pour effectuer des calculs en vue de leur puissance de calcul, dans la suite on va appeler de telles machines des <i> workers </i>.  
</p>
<p>
  Les calculs effectués par les machines seront des calculs de <a href="https://fr.wikipedia.org/wiki/Persistance_d%27un_nombre">persistance 
  des nombres</a>. Pour ce faire, on commence par créer deux serveurs, un pour les <i>worker</i> et un autre pour les clients. Une fois la connexion entre 
  serveur et <i>worker</i> établie, le serveur envoi des tâches sous forme d'intervalles au <i>worker</i>, celui-ci à recéption de la tâche calcule la 
  persistance des nombre se trouvant dans l'intervalle et renvoi les résultats au serveur. Le client peut interagir avec le serveur (client) et accéder
  aux résultats calculés par les <i>workers</i>.
</p>

# Implémentation
<p> 
  On divise le projet en 4 gros packages : <i>config, server, worker</i> et <i>client</i>.
</p>
<p>
  le package <i>worker</i> réalise les fonctions suivantes :
  <ul>
    <li>Se connecter au serveur.</li>
    <li>Recevoir et calculer les taches envoyées par le serveur.</li>
    <li>Recevoir et répondre aux requetes envoyées par le serveur.</li>
  </ul>
  le package <i>server</i> réalise les fonctions suivantes :
  <ul>
    <li>Créer deux serveurs : <i>client</i> et <i>worker</i>.</li>
    <li>Evaluer le progress des <i>workers</i> afin de leur envoyer des tâches.</li>
    <li>Recevoir les requetes envoyé par le client et renvoyer des réponses.</li>
  </ul>
  le package <i>client</i> réalise les fonctions suivantes :
  <ul>
    <li>Se connecter au serveur.</li>
    <li>Superviser les résultats produites par les <i>workers</i></li>
    <li>Envoyer différentes requetes au serveur et recevoir les réponses</li>
  </ul>
</p>
