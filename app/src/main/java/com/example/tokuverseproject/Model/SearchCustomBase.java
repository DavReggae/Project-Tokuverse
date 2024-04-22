package com.example.tokuverseproject.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.List;

public class SearchCustomBase extends BaseAdapter {
    LayoutInflater inflater;
    List<User> searchList;
    ServerHandler serverHandler = new ServerHandler();
    public SearchCustomBase(Context ctx, List<User> searchList)
    {
        inflater = LayoutInflater.from(ctx);
        this.searchList = searchList;
    }
    @Override
    public int getCount() {
        return searchList.size();
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
        view = inflater.inflate(R.layout.search_item_list, null);
        ImageView img_Search_UserAvatar = view.findViewById(R.id.img_Search_UserAvatar);
        TextView lbl_Search_UserName = view.findViewById(R.id.lbl_Search_UserName);
        serverHandler.LoadImageFromURL(searchList.get(i).getAvatar(), img_Search_UserAvatar);
        lbl_Search_UserName.setText(searchList.get(i).getUsername());
        return view;
    }
}
