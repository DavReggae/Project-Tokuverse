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

public class NewFeedCustomBase extends BaseAdapter {
    LayoutInflater inflater;
    ServerHandler serverHandler = new ServerHandler();

    List<NewFeeds>  newFeedsList;

    public NewFeedCustomBase(Context ctx, List<NewFeeds> newFeedsList)
    {
        this.newFeedsList = newFeedsList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return newFeedsList.size();
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
        view = inflater.inflate(R.layout.new_feeds_item_list, null);
        ImageView img_PostUserAvatar = view.findViewById(R.id.img_PostUserAvatar);
        TextView lbl_PostUserName = view.findViewById(R.id.lbl_PostUserName);
        TextView lbl_PostConent = view.findViewById(R.id.lbl_PostContent);
        TextView lbl_likeCount = view.findViewById(R.id.lbl_PostLikeCount);
        TextView lbl_commentCount = view.findViewById(R.id.lbl_CommentCount);
        NewFeeds newFeeds = newFeedsList.get(i);
        serverHandler.LoadImageFromURL(newFeeds.getUser_avatar(), img_PostUserAvatar);
        lbl_PostUserName.setText(newFeeds.getUser_name());
        lbl_PostConent.setText(newFeeds.getContent());
        lbl_likeCount.setText("   " + newFeeds.getLike_count());
        lbl_commentCount.setText(newFeeds.getComment_count() +"   ");

        return view;
    }
}
