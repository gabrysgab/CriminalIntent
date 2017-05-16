package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private static final String EXTRA_CRIME_POSITION = "crime_position";

    public static Intent newIntent(Context context, UUID crimeId, int crimePosition) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_CRIME_POSITION, crimePosition);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        int crimePosition = (int) getIntent().getSerializableExtra(EXTRA_CRIME_POSITION);
        return CrimeFragment.newInstance(crimeId,crimePosition );
    }
}