package com.rhodonite.fragmentviewpager.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rhodonite.fragmentviewpager.MainActivity;
import com.rhodonite.fragmentviewpager.R;

import androidx.fragment.app.Fragment;

public class Fragmrnt_2 extends Fragment {

    MainActivity mainActivity;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2,container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("Fragment_2", "onResume");
    }

}

