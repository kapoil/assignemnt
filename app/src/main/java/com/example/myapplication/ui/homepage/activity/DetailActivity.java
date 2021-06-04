package com.example.myapplication.ui.homepage.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.ModelProfile;
import com.example.myapplication.ui.homepage.adapters.PhotosAdapter;
import com.example.myapplication.util.Field;
import com.example.myapplication.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.workTitle)
    TextView workTitle;

    @BindView(R.id.general_Title)
    TextView generalTitle;

    @BindView(R.id.work)
    RelativeLayout workRelativeLayout;

    @BindView(R.id.general)
    RelativeLayout generalRelativeLayout;

    @BindView(R.id.section_container_work)
    LinearLayout workContainer;

    @BindView(R.id.section_container)
    LinearLayout generalContainer;

    HashMap preferences = new HashMap();
    HashMap general = new HashMap();
    HashMap work = new HashMap();
    ModelProfile modelProfile;
    boolean isCollapsed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            modelProfile = (ModelProfile) bundle.getSerializable("Profile");
        }

        general = modelProfile.getGeneral_information();
        work = modelProfile.getWork();


        PhotosAdapter adapter = new PhotosAdapter(modelProfile.getPhotos(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        for (Object key : general.keySet()) {
            if (general.get(key) instanceof String) {
                String ans = (String) general.get(key);
                Field field = new Field(this, (String) key, ans);
                generalContainer.addView(field);
            }
        }

        for (Object key : work.keySet()) {
            if (work.get(key) instanceof String) {
                String ans = (String) work.get(key);
                Field field = new Field(this, (String) key, ans);
                workContainer.addView(field);
            }
        }

        workRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickExpandCollapse(workContainer);
            }
        });
        generalRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickExpandCollapse(generalContainer);
            }
        });
    }

    private void OnClickExpandCollapse(View container) {
        if (isCollapsed) {
            expand(container);
            isCollapsed = false;
        } else {
            collapse(container);
            isCollapsed = true;
        }
    }

    public static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return false;
            }
        };

        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return false;
            }
        };

        v.startAnimation(a);
    }

}