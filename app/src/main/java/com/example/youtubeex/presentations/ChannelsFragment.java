package com.example.youtubeex.presentations;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeex.R;
import com.example.youtubeex.entities.channels.ChannelsInfo;
import com.example.youtubeex.entities.channels.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelsFragment extends Fragment {

    ChannelsViewModel channelsViewModel;
    ChannelsFactory channelsFactory;
    List<Item> channelsList;

    ChannelsRecyclerAdaptor channelsRecyclerAdaptor;

    @BindView(R.id.channels_recyclerView)
    RecyclerView channelsRecyclerView;

    public ChannelsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channels, container, false);
        ButterKnife.bind(this, view);


        String key, part, ids;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        channelsRecyclerView.setLayoutManager(layoutManager);
        channelsRecyclerView.setHasFixedSize(true);

        channelsList = new ArrayList<>();
        channelsRecyclerAdaptor = new ChannelsRecyclerAdaptor(this.getContext(),channelsList);
        channelsRecyclerView.setAdapter(channelsRecyclerAdaptor);

        key = "AIzaSyCIF9RV4eORH8fU-o6oRLVmw0JU4nqE7hA";
        part = "snippet";
        ids = "UCrqPrsKMVG2DsY9t5l3VWMQ," +
                "UC5MtCj2LVwX30SEF-dwXlww," +
                "UCoNZZLhPuuRteu02rh7bzsw," +
                "UCSwuCetC3YlO1Y7bqVW5GHg," +
                "UCWIA0jryy_aEd9u6JTe1S9Q";


        getChannels(key, part, ids);

        return view;
    }

    private void getChannels(String key, String part, String ids) {
        channelsFactory = new ChannelsFactory(this.getActivity().getApplication(), key, part, ids);
        channelsViewModel = new ViewModelProvider(this, channelsFactory).get(ChannelsViewModel.class);

        channelsViewModel.channelsInfoMutableLiveData.observe(this.getActivity(), new Observer<ChannelsInfo>() {
            @Override
            public void onChanged(ChannelsInfo channelsInfo) {


                sortChannelsByTitle(channelsInfo);

                channelsList.addAll(channelsInfo.getItems());
                channelsRecyclerAdaptor.notifyDataSetChanged();


            }
        });
    }

    private void sortChannelsByTitle(ChannelsInfo channelsInfo) {
        Collections.sort(channelsInfo.getItems(), new Comparator<Item>(){
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getSnippet().getTitle().compareToIgnoreCase(o2.getSnippet().getTitle()) ;
            }

        });
    }

}
