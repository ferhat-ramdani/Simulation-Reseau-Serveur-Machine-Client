# Into
<p>
  Dans ce projet, on simule un réseau composé de serveurs, machines calculatrices et clients. Les machines calculatrices sont des machines utilisé par
  le réseau pour effectuer des calculs en vue de leur puissance de calcul, dans la suite on va appeler de telle machines des <i> workers </i>.  
</p>
<p>
  Les calculs effectués par les machines seront des calculs de <a href="https://fr.wikipedia.org/wiki/Persistance_d%27un_nombre">persistance 
  des nombres</a>. Pour ce faire, on commence par créer deux serveurs, un pour les _workers_ et un autre pour les clients. Une fois la connexion entre 
  serveur et worker établie, le serveur envoi des tâches sous forme d'intervalles au <i> worker</i>, celui-ci à recéption de la tâche calcule la 
  persistance des nombre se trouvant dans l'intervalle et renvoi les résultats au serveur. Le client peut interagir avec le serveur (client) et accéder
  aux résultats calculés par les <i> workers </i>
</p>

# Implémentation
<p>
  
