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
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {
    View profileView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileView = inflater.inflate(R.layout.profile_layout, container, false);

        String type = "getPlayerInfo";

        BackgroundWorker backgroundWorker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
            @Override
            public void processFinish(String output) {

            }
        },getActivity());
        backgroundWorker.execute(type);

        return profileView;
    }

}
