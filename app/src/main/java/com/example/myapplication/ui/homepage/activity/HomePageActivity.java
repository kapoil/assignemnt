package com.example.myapplication.ui.homepage.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.myapplication.R;
import com.example.myapplication.ui.homepage.fragment.DicoverFragment;
import com.example.myapplication.ui.homepage.fragment.InvitesFragment;
import com.example.myapplication.ui.homepage.fragment.LikesFragment;
import com.example.myapplication.ui.homepage.fragment.ProfileFragment;
import com.example.myapplication.util.TabInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePageActivity extends AppCompatActivity {

    //region view variable
    @BindView(R.id.navigationView)
    AHBottomNavigation bottomNavigationView;
    //endregion

    //region member variable
    ArrayList<TabInfo> tabInfos = new ArrayList<>();
    FragmentManager fm;
    HashMap invites = new HashMap();
    HashMap likes = new HashMap();
    int invitesCount = 0;
    int likesCount = 0;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        //setting up variables for view
        setUpDataFromBundle();
        setUpTabInfoList();
        setUpBottomBar("D");
    }

    //region helper methods
    private void setUpTabInfoList() {
        tabInfos = new ArrayList<>();
        tabInfos.add(new TabInfo("D", "discover", "ic_discover_state"));
        tabInfos.add(new TabInfo("L", "Likes", "notes_24"));
        tabInfos.add(new TabInfo("I", "Invites", "ic_message_state"));
        tabInfos.add(new TabInfo("P", "Profile", "ic_contact_state"));
    }


    private void setUpDataFromBundle() {
        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            HashMap hashMap = (HashMap) bundle.getSerializable("HashMap");
            invites = (HashMap) hashMap.get("invites");
            likes = (HashMap) hashMap.get("likes");
        }

        invitesCount = (int) invites.get("pending_invitations_count");
        likesCount = (int) likes.get("likes_received_count");


    }

    private void setUpBottomBar(String landingTabId) {

        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        bottomNavigationView.setInactiveColor(getResources().getColor(R.color.inActive));

        bottomNavigationView.setAccentColor(getResources().getColor(R.color.Active));

        bottomNavigationView.setNotificationBackgroundColor(getResources().getColor(R.color.purple_500));
        fm = getSupportFragmentManager();
        for (TabInfo tabInfo : tabInfos) {
            int resourceId = getResId(tabInfo.getIcon(), R.drawable.class);
            String title = tabInfo.getTitle();

            AHBottomNavigationItem item = new AHBottomNavigationItem(title, resourceId);
            bottomNavigationView.addItem(item);

        }

        Fragment fragment = DicoverFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, fragment).commit();
        int index = getTabIndex(landingTabId, tabInfos);
        bottomNavigationView.setNotification(invitesCount + "", 2);
        bottomNavigationView.setNotification(likesCount + "", 1);
        bottomNavigationView.setCurrentItem(index);
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    default:
                        if (position == 0) {
                            Fragment fragment = DicoverFragment.newInstance();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, fragment).commit();

                        } else if (position == 1) {
                            Fragment fragment = LikesFragment.newInstance(likes);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, fragment).commit();

                        } else if (position == 2) {
                            Fragment fragment = InvitesFragment.newInstance(invites);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, fragment).commit();

                        } else {
                            Fragment fragment = ProfileFragment.newInstance();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, fragment).commit();
                        }
                }

                return true;
            }
        });
    }

    public int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getTabIndex(String landingTabId, ArrayList<TabInfo> tabs) {
        int result = 0;

        for (int tab_index = 0; tab_index < tabs.size(); ++tab_index) {
            if (landingTabId.equalsIgnoreCase(tabs.get(tab_index).getId())) {
                result = tab_index;
                break;
            }
        }

        return result;
    }
    //endregion

}