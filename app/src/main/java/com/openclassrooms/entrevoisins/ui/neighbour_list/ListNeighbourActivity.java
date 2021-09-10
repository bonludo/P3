package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity implements OnNeighbourClickedListener {
// on implement le OnNeighboutClickedListener

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }



    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    @Override
    public void onNeighbourClicked(Neighbour neighbour) {
        Intent intentDetailNeighbourActivity = new Intent (this, DetailNeighbourActivity.class);
//      Log.d("ListNeighbourActivity","Test trace debug");
//      Log.e("ListNeighbourActivity","erreur factice");
        intentDetailNeighbourActivity.putExtra("Name", neighbour.getName());
        intentDetailNeighbourActivity.putExtra("Phone",neighbour.getPhoneNumber());
        intentDetailNeighbourActivity.putExtra("Adress",neighbour.getAddress());
        intentDetailNeighbourActivity.putExtra("Social",neighbour.getSocial());
        intentDetailNeighbourActivity.putExtra("AboutMe",neighbour.getAboutMe());
        intentDetailNeighbourActivity.putExtra("AvatarUrl",neighbour.getAvatarUrl());
        intentDetailNeighbourActivity.putExtra("favorite",neighbour.isFavorite());
        startActivity(intentDetailNeighbourActivity);
    }
}