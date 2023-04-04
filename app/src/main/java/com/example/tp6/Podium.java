package com.example.tp6;

import java.util.ArrayList;
import java.util.List;

public class Podium {

    private List<Pokemon> pokemons;

    public Podium() {
        this.pokemons = rankPodium();
    }

    public List<Pokemon> rankPodium(){
        List<Pokemon> result = new ArrayList<>();
        List<Pokemon> allPokemons = Pokemon.getCompleteList();
        result.add(allPokemons.get(0));
        result.add(allPokemons.get(1));
        result.add(allPokemons.get(2));
        for(int i=3; i<allPokemons.size(); i++){
            if(result.get(0).compareTo(allPokemons.get(i)) >0){
                result.set(0,allPokemons.get(i));
            }
            else if(result.get(1).compareTo(allPokemons.get(i)) >0){
                    result.set(1,allPokemons.get(i));
            }
            else if(result.get(2).compareTo(allPokemons.get(i)) >0){
                    result.set(2,allPokemons.get(i));
            }
        }
        return result;
    }

    public void UpdatePodium(){
        this.pokemons = rankPodium();
    }
}
