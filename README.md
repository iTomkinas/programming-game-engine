# Programming game 


## How the game works?

1. Map is generated with bases randomly populated in it. There are different distances between each base.
2. User gets assigned one of the base with certain amount of units in the base.


## How to win the game?
none

## How to write bot

To write bot - Player interface has to be implemented:
```Java
interface Player {  
  
    fun executeActions(map: Map): List<PlayerAction>  
  
    fun getId(): String  
  
    fun getName(): String  
}
```

Each turn `executeActions` is invoked where player gets `map` object which contains all the details what's happening in the game. It is expected to get back a list of actions player could take.

test
