package com.example.youtubeex.usecases;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.youtubeex.entities.channelVideos.VideosChannelInfo;
import com.example.youtubeex.entities.channels.ChannelsInfo;
import com.example.youtubeex.usecases.repos.ChannelsRepo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChannelsUsecases {

    public MutableLiveData<ChannelsInfo> channelsInfoMutableLiveData;
    ChannelsRepo channelsRepo;

    public ChannelsUsecases(MutableLiveData<ChannelsInfo> channelsInfoMutableLiveData) {
        this.channelsInfoMutableLiveData = channelsInfoMutableLiveData;
        channelsRepo = new ChannelsRepo();
    }

    public void getChannelsInfo(String key,String part,String ids)
    {
        channelsRepo.getChannelsInfo(key,part,ids).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ChannelsInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ChannelsInfo channelsInfo) {

                channelsInfoMutableLiveData.postValue(channelsInfo);

            }

            @Override
            public void onError(Throwable e) {

                Log.e("observe error",e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
