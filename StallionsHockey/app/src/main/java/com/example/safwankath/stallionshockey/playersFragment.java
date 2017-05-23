package com.example.safwankath.stallionshockey;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Safwan Kath on 31/07/2016.
 */
public class playersFragment extends Fragment {
    View playersView;
    FloatingActionButton fab;
   String[] mobileArray = {""};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        playersView = inflater.inflate(R.layout.players_layout, container, false);
        fab = (FloatingActionButton) playersView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // after click, the floating button will disappear
                //fab.setVisibility(View.GONE);
                Snackbar.make(v, "Player Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent addPlayerIntent = new Intent (getActivity(), addPlayer.class);
                startActivity(addPlayerIntent);
            }
        });

        String type = "getPlayers";

        BackgroundWorker backgroundWorker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                mobileArray=output.split(",");
                Log.i("hereasdfsdf:", mobileArray[0]);
                ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.activity_listview, mobileArray);

                ListView listView = (ListView) playersView.findViewById(R.id.mobile_list);
                listView.setAdapter(adapter);
            }
        },getActivity());
        backgroundWorker.execute(type);

        return playersView;
    }

}
