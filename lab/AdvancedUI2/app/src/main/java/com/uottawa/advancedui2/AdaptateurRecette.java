package com.uottawa.advancedui2;

/**
 * Created by cedric on 11/7/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class AdaptateurRecette extends ArrayAdapter<Recette> {
    private final Context context;
    private final List<Recette> values;

    public AdaptateurRecette(Context context, List<Recette> values){
        super(context, R.layout.item_recette, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_recette, parent, false);
        
        /**
         * TODO (1): Obtain references to the UI elements using rowView.
         */
        ImageView img = (ImageView) rowView.findViewById(R.id.imageView);
        //propriété dans la page

        Recette recette = this.getItem(position);
        
        /**
         * TODO (2): Set the values for the UI elements to the information provided by the recipe
         */
        img.setImageResource(recette.getImage());
        //même chose avec les autres
        
        return rowView;
    }

}

