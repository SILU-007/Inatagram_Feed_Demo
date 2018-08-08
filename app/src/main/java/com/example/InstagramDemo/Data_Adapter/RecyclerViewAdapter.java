package com.example.InstagramDemo.Data_Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.InstagramDemo.Data_gettter_setters.feedData;
import com.example.InstagramDemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    ArrayList<feedData> feedDataFeelers;
    RecyclerViewClickListener recyclerViewClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<feedData> feedDataFeelers) {
        this.context = context;
        this.feedDataFeelers = feedDataFeelers;
    }

    public RecyclerViewAdapter(ArrayList<feedData> getDataAdapter, Context context, RecyclerViewClickListener recyclerViewClickListener) {
        super();
        this.feedDataFeelers = getDataAdapter;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_feed_tab_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position) {

        Viewholder.ImageCaption.setText(feedDataFeelers.get(position).getFeedDescription());

        Viewholder.itemView.setTag(feedDataFeelers.get(position).getFeedId());

        String path=feedDataFeelers.get(position).getFeedImagePath();
        Uri file=Uri.parse(path);
        Picasso.get().load(file).fit().centerCrop().into(Viewholder.PostedImage);
      //  Toast.makeText(context, feedDataFeelers.get(position).getFeedDescription(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(context, path, Toast.LENGTH_SHORT).show();


    }



    @Override
    public int getItemCount() {

        return feedDataFeelers.size();
    }

    public void swapCursorCall(Cursor newCursor) {
        // Always close the previous mCursor first
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
            //Toast.makeText(context, "changed", Toast.LENGTH_SHORT).show();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         private TextView ImageCaption;
         private ImageView PostedImage;
        public ViewHolder(View itemView) {
            super(itemView);
            ImageCaption=itemView.findViewById(R.id.feed_ImageCaption);
            PostedImage=itemView.findViewById(R.id.feed_Image);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }

    }
}