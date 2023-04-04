package com.example.tp6;

import com.squareup.picasso.Picasso;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> mDataset;
    private Context mContext;
    private int seekBarValue ;

    private HashMap<Pokemon, String> link = new HashMap<>();

    public PokemonAdapter(Context context, List<Pokemon> myDataset, int seekBarValue) {
        this.mContext = context;
        this.mDataset = myDataset;
        this.seekBarValue = seekBarValue;
    }

    private void generatePicture(){
        for (Pokemon pokemon : mDataset) {
            this.link.put(pokemon, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getId() + ".png");
            Picasso.get()
                    .load(link.get(pokemon));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mRankTextView;

        public ViewHolder(View v) {
            super(v);
            mNameTextView = v.findViewById(R.id.pokemon_name);
            mRankTextView = v.findViewById(R.id.pokemon_rank);
        }
    }

    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pokemon pokemon = mDataset.get(position);

        holder.mNameTextView.setText(pokemon.getName());
        holder.mRankTextView.setText(Integer.toString(pokemon.getRank()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PokemonAdapter", "Clicked on " + pokemon.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateDataset(List<Pokemon> newDataset) {
        mDataset = newDataset;
        notifyDataSetChanged();
    }
}