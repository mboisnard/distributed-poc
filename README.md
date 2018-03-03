# Distributed POC

## Installation

- docker-compose -p memoire up -d --build
- docker-compose -p memoire stop
- docker ps -a

## Lancement

Lancer les conteneurs manuellement afin de ne pas avoir de problèmes d'initialisation (agent initialisé avant eureka, ...) 

- docker start memoire_poc-kafka_1
- docker start memoire_poc-eureka_1
- docker start memoire_poc-agent_1
- docker start memoire_poc-customer1_1
- docker start memoire_poc-customer2_1
- docker start memoire_poc-customer3_1
- docker start memoire_poc-order1_1
- docker start memoire_poc-order2_1
- docker start memoire_poc-proposal1_1
- docker start memoire_poc-proposal2_1

## Arrêt

- docker-compose -p memoire stop
- docker-compose -p memoire rm