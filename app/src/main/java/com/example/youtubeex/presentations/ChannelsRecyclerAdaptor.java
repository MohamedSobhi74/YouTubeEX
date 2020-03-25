package com.example.youtubeex.presentations;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.youtubeex.R;
import com.example.youtubeex.entities.channels.Item;
import com.example.youtubeex.entities.channels.Snippet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChannelsRecyclerAdaptor extends RecyclerView.Adapter<ChannelsRecyclerAdaptor.ChannelViewHolder> {

    Context context;
    List<Item> channelsList;


    public ChannelsRecyclerAdaptor(Context context, List<Item> channelsList) {
        this.context = context;
        this.channelsList = channelsList;
    }


    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.channel_raw, parent,false);

        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {

        Snippet snippet = channelsList.get(position).getSnippet();

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_launcher_foreground);

        Glide.with(context).setDefaultRequestOptions(options).load(snippet.getThumbnails().getHigh().getUrl())
                .thumbnail(Glide.with(context).load(snippet.getThumbnails().getDefault().getUrl()))
                .into(holder.channelImage);

        holder.channelTitle.setText(snippet.getTitle());

        holder.subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.subscribeBtn.getText().equals("Subscribed"))
                {
                    Toast.makeText(context, "you subscribed "+snippet.getTitle(), Toast.LENGTH_SHORT).show();
                    holder.subscribeBtn.setText("Subscribed");
                    holder.subscribeBtn.setTextColor(context.getResources().getColor(R.color.grayColor));

                }else {
                    Toast.makeText(context, "you Unsubscribed "+snippet.getTitle(), Toast.LENGTH_SHORT).show();
                    holder.subscribeBtn.setText("subscribe");
                    holder.subscribeBtn.setTextColor(context.getResources().getColor(R.color.redColor));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e("size aaa",""+channelsList.size());

        if (channelsList == null)
        {
            return 0;
        }else
        {
            return channelsList.size();
        }

    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.channel_image)
        CircleImageView channelImage;
        @BindView(R.id.channel_title)
        TextView channelTitle;
        @BindView(R.id.subscribe_btn)
        TextView subscribeBtn;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


        }
    }


}
