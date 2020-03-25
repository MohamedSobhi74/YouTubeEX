package com.example.youtubeex.presentations;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChannelsFactory  extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    String key,  part,  ids;

    public ChannelsFactory(@NonNull Application application,String key, String part, String ids) {

        this.application = application;
        this.key = key;
        this.part = part;
        this.ids = ids;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ChannelsViewModel.class)
            return (T) new ChannelsViewModel(application,key, part, ids);
        return null;
    }
}