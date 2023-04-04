package com.example.tp6;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tp6.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class ListPokemonFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private SeekBar seekBar;
    private TextView seekBarValueTextView;
    private int seekBarValue = 0;

    private FragmentNotifiable mCallback;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListPokemonFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListPokemonFragment newInstance(int columnCount) {
        ListPokemonFragment fragment = new ListPokemonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);

        recyclerView = view.findViewById(R.id.pokemon_list);
        seekBar = view.findViewById(R.id.seek_bar);
        seekBarValueTextView = view.findViewById(R.id.seek_bar_text);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PokemonAdapter(getActivity(), Pokemon.getAll(), seekBarValue);
        recyclerView.setAdapter(adapter);

        // Set up SeekBar
        seekBar.setMax(60);
        seekBar.setProgress(30);
        seekBarValueTextView.setText(String.valueOf(seekBarValue));

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress - 30;
                seekBarValueTextView.setText(String.valueOf(seekBarValue));

                // Update rank of Pokemons
                Pokemon.boost(seekBarValue);
                adapter.notifyDataSetChanged();

                // Notify activity
                if (mCallback != null) {
                    mCallback.onFragmentNotify("ListPokemonFragment: SeekBar value changed");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set up item click listener
        adapter.setOnItemClickListener(new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                // Log Pokemon info
                Log.d("ListPokemonFragment", "Clicked on Pokemon " + pokemon.getName());
            }
        });

        return view;
    }
}

/*

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private SeekBar seekBar;
    private TextView seekBarValueTextView;
    private int seekBarValue = 0;

    private FragmentNotifiable mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNotifiable)
            mCallback = (FragmentNotifiable) context;
        else
            throw new RuntimeException("FragmentNotifiable not implemented in context");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        seekBar = view.findViewById(R.id.seekBar);
        seekBarValueTextView = view.findViewById(R.id.seekBarValueTextView);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PokemonAdapter(getActivity(), Pokemon.getAll(), seekBarValue);
        recyclerView.setAdapter(adapter);

        // Set up SeekBar
        seekBar.setMax(60);
        seekBar.setProgress(30);
        seekBarValueTextView.setText(String.valueOf(seekBarValue));

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress - 30;
                seekBarValueTextView.setText(String.valueOf(seekBarValue));

                // Update rank of Pokemons
                Pokemon.boost(seekBarValue);
                adapter.notifyDataSetChanged();

                // Notify activity
                if (mCallback != null) {
                    mCallback.onFragmentNotify("ListPokemonFragment: SeekBar value changed");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Set up item click listener
        adapter.setOnItemClickListener(new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                // Log Pokemon info
                Log.d("ListPokemonFragment", "Clicked on Pokemon " + pokemon.getName());
            }
        });

        return view;
    }
}*/