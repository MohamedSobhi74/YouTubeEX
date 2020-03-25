package com.example.youtubeex.presentations;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.youtubeex.entities.channels.ChannelsInfo;
import com.example.youtubeex.usecases.ChannelsUsecases;

public class ChannelsViewModel extends ViewModel {

    private Application application;
    String key,part,ids;

    MutableLiveData<ChannelsInfo> channelsInfoMutableLiveData;
    ChannelsUsecases channelsUsecases;


    public ChannelsViewModel(Application application,String key, String part, String ids) {

        this.application = application;
        this.key = key;
        this.part = part;
        this.ids = ids;
        this.channelsInfoMutableLiveData = new MutableLiveData<>();
        channelsUsecases = new ChannelsUsecases(channelsInfoMutableLiveData);

        getChannelsInfo();
    }




    private void getChannelsInfo() {

        if (!key.isEmpty() && !part.isEmpty() && !ids.isEmpty()) {

            channelsUsecases.getChannelsInfo(key, part, ids);

        }else {

            Log.e("empty parameter error","parameters does not have value");
        }

    }

}
