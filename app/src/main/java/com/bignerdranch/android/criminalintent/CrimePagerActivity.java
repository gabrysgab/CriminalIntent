package com.bignerdranch.android.criminalintent;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mateusz on 2017-05-09.
 */

public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int currentCrimePostion;
    private List<Crime> crimes;
    private static final String EXTRA_CRIME_POSITION = "extra_crime_position";

    public static Intent newIntent(Context context, int crimePosition) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_POSITION, crimePosition);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        currentCrimePostion = getIntent().getIntExtra(EXTRA_CRIME_POSITION, -1);

        viewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        crimes = CrimeLab.getInstance(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimes.get(position);
                return CrimeFragment.newInstance(crime.getId(), position);
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });
        viewPager.setCurrentItem(currentCrimePostion);

    }
}
