package com.example.myapplication.ui.homepage.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.ModelProfile;
import com.example.myapplication.ui.homepage.adapters.ProfileAdapter;
import com.example.myapplication.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvitesFragment extends Fragment {

    //region view variable
    @BindView(R.id.recycler_view)
     RecyclerView recyclerView;
    //endregion

    HashMap invites = new HashMap();
    ArrayList<ModelProfile> profiles;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_invites, container, false);
        ButterKnife.bind(this,rootView);

        setUpDataFromBundle();
        setUpRecyclerView();

        Utils.showToast(getContext(),"Please tap Image for more details");
        return rootView;
    }

    private void setUpRecyclerView() {
        ProfileAdapter adapter = new ProfileAdapter(profiles, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void setUpDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            invites= (HashMap) bundle.getSerializable("invites");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        profiles = objectMapper.convertValue(invites.get("profiles"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ModelProfile.class));


    }

    // TODO: Rename and change types and number of parameters
    public static InvitesFragment newInstance(HashMap invites) {
        InvitesFragment fragment = new InvitesFragment();
        Bundle extras = new Bundle();
        extras.putSerializable("invites",invites);
        fragment.setArguments(extras);
        return fragment;
    }
}
