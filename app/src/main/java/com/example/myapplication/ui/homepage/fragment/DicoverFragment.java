package com.example.myapplication.ui.homepage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class DicoverFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.discover_fragment, container, false);
        return rootView;
    }


    // TODO: Rename and change types and number of parameters
    public static DicoverFragment newInstance() {
        DicoverFragment fragment = new DicoverFragment();
        Bundle args = new Bundle();
        //        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}
