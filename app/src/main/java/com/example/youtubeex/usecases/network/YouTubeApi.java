package com.example.youtubeex.usecases.network;

import com.example.youtubeex.entities.channelVideos.VideosChannelInfo;
import com.example.youtubeex.entities.channels.ChannelsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApi {

    @GET("channels")
    Observable<ChannelsInfo> getChannels(@Query("key") String key,
                                                @Query("part") String part,
                                                @Query("id") String ids);

    @GET("search")
    Observable<VideosChannelInfo> getChannelVideos(@Query("key") String key,
                                           @Query("channelId") String channelId,
                                           @Query("part") String part,
                                           @Query("order") String order,
                                           @Query("maxResults") String maxResults);
}
