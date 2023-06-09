package com.example.tp6;

import java.util.List;

public interface PostExecuteActivity<T> {
    void onPostExecutePokemons(List<T> itemList);
    void runOnUiThread( Runnable runable);

    void onPostExecute(List<Pokemon> pokemonList);
}
