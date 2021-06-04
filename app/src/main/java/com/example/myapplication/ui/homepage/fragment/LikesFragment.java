package com.example.myapplication.ui.homepage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.homepage.adapters.PhotosAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikesFragment extends Fragment {

    //region view variables
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //endregion

    //region member variable
    HashMap likes = new HashMap();
    ArrayList<HashMap> arrayList;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this,rootView);

        setUpDataFromBundle();
        setUpRecyclerView();

        return rootView;
    }

    //region helper methods
    private void setUpRecyclerView() {
        PhotosAdapter adapter = new PhotosAdapter(arrayList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    private void setUpDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            likes = (HashMap) bundle.getSerializable("likes");
        }
       arrayList = (ArrayList<HashMap>) likes.get("profiles");
    }

    //endregion

    // TODO: Rename and change types and number of parameters
    public static LikesFragment newInstance(HashMap likes) {

        LikesFragment fragment = new LikesFragment();
        Bundle extras = new Bundle();
        extras.putSerializable("likes", likes);
        fragment.setArguments(extras);
        return fragment;
    }
}
