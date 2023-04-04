package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostExecuteActivity<Pokemon>, AdapterView.OnItemSelectedListener {
    private final String TAG = "frallo "+getClass().getSimpleName();

    private Spinner languageSpinner;
    private Button getPokemonsButton;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageSpinner = findViewById(R.id.langue);
        getPokemonsButton = findViewById(R.id.jouer);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(this);

        getPokemonsButton.setOnClickListener( clic -> {
            String url = "https://raw.githubusercontent.com/fanzeyi/pokemon.json/17d33dc111ffcc12b016d6485152aa3b1939c214/pokedex.json";
            new HttpAsyncGet<>(url, Pokemon.class, this, new ProgressDialog(clic.getContext()) );
        });
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String language = (String) parent.getItemAtPosition(position);
        Log.d("MainActivity", "Selected language: " + language);
        // Do something with selected language
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onPostExecutePokemons(List<Pokemon> itemList) {
        Pokemon pokemonFirst = itemList.get(0);
        Log.d(TAG,"First pokemon = " + pokemonFirst.getName());
    }


    @Override
    public void onPostExecute(List<Pokemon> pokemonList) {
        super.onPostExecute(pokemonList);
        MainActivity.this.pokemonList = pokemonList;
        if (pokemonList != null && pokemonList.size() > 0) {
            Pokemon firstPokemon = pokemonList.get(0);
            Log.d("MainActivity", "First Pokemon: " + firstPokemon.getName());
            String selectedLanguage = (String) languageSpinner.getSelectedItem();
            Log.d("MainActivity", "Selected language: " + selectedLanguage);
        }
    }

}


/*

    public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        private Spinner languageSpinner;
        private Button getPokemonsButton;
        private List<Pokemon> pokemonList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Get references to UI elements
            languageSpinner = findViewById(R.id.languageSpinner);
            getPokemonsButton = findViewById(R.id.getPokemonsButton);

            // Set up language spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.languages_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            languageSpinner.setAdapter(adapter);
            languageSpinner.setOnItemSelectedListener(this);

            // Set up button click listener
            getPokemonsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GetPokemonsTask().execute();
                }
            });
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String language = (String) parent.getItemAtPosition(position);
            Log.d("MainActivity", "Selected language: " + language);
            // Do something with selected language
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }

        private class GetPokemonsTask extends AsyncTask<Void, Void, List<Pokemon>> {

            private static final String POKEMON_API_URL = "https://pokeapi.co/api/v2/pokemon?limit=151";

            @Override
            protected List<Pokemon> doInBackground(Void... voids) {
                try {
                    URL url = new URL(POKEMON_API_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Gson gson = new Gson();
                    JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                    JsonArray resultsArray = jsonObject.getAsJsonArray("results");
                    List<Pokemon> pokemonList = new ArrayList<>();
                    for (JsonElement element : resultsArray) {
                        JsonObject pokemonObject = element.getAsJsonObject();
                        String name = pokemonObject.get("name").getAsString();
                        String urlStr = pokemonObject.get("url").getAsString();
                        URL pokemonUrl = new URL(urlStr);
                        HttpURLConnection pokemonConnection = (HttpURLConnection) pokemonUrl.openConnection();
                        pokemonConnection.setRequestMethod("GET");

                        BufferedReader pokemonIn = new BufferedReader(new InputStreamReader(pokemonConnection.getInputStream()));
                        String pokemonInputLine;
                        StringBuilder pokemonResponse = new StringBuilder();
                        while ((pokemonInputLine = pokemonIn.readLine()) != null) {
                            pokemonResponse.append(pokemonInputLine);
                        }
                        pokemonIn.close();


 */