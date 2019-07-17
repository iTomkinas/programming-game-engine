# Programming game 
sa

## How the game works?

1. Map is generated with bases randomly populated in it. There are different distances between each base.
2. User gets assigned one of the base with certain amount of units in the base.


## How to win the game?
impossible

## How to write bot

To write bot - Player interface has to be implemented:
```Java
interface Player {  
  
    fun executeActions(map: Map): List<PlayerAction>  
  
    fun getId(): String  
  
    fun getName(): String  
}
```


testukas
