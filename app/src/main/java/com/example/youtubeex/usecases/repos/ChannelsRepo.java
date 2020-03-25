package com.example.youtubeex.usecases.repos;

import com.example.youtubeex.entities.channelVideos.VideosChannelInfo;
import com.example.youtubeex.entities.channels.ChannelsInfo;
import com.example.youtubeex.usecases.network.RetrofitClient;
import com.example.youtubeex.usecases.network.YouTubeApi;

import io.reactivex.Observable;

public class ChannelsRepo {

    YouTubeApi youTubeApi;
    public ChannelsRepo() {
        youTubeApi = RetrofitClient.getClient().create(YouTubeApi.class);
    }

    public Observable<ChannelsInfo> getChannelsInfo(String key,String part,String ids)
    {
        return youTubeApi.getChannels(key,part,ids);

    }


}
