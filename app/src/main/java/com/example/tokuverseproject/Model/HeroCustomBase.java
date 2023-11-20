package com.example.tokuverseproject.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tokuverseproject.R;

import java.net.MalformedURLException;
import java.net.URL;

public class HeroCustomBase extends BaseAdapter {
    Context context;
    String hero_Name[];
    String hero_Description[];
    String hero_pic[];
    LayoutInflater inflater;

    public HeroCustomBase(Context ctx, String[] heroName_List, String[] heroDes_List, String[] heroPic_list )
    {
        this.context = ctx;
        this.hero_Name = heroName_List;
        this.hero_Description = heroDes_List;
        this.hero_pic = heroPic_list;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return hero_Name.length;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.hero_item_list, null);
        TextView lbl_HeroName = view.findViewById(R.id.lbl_HeroName);
        TextView lbl_HeroDes = view.findViewById(R.id.lbl_HeroDescription);
        lbl_HeroName.setText(hero_Name[i]);
        lbl_HeroDes.setText(hero_Description[i]);
        try
        {
            URL url = new URL(hero_pic[i]);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return view;
    }
}
