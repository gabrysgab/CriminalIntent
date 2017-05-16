package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-12.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView crimeRecyclerView;
    private CrimeAdapter crimeAdapter;


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        crimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void updateUI() {

        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        if (crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimeLab.getCrimes());
            crimeRecyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private ImageView crimeSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            dateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            crimeSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            titleTextView.setText(crime.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            dateTextView.setText(sdf.format(crime.getDate()));
            crimeSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            int crimePosition = getAdapterPosition();
            Intent intent = CrimePagerActivity.newIntent(getContext(),crimePosition);
            startActivity(intent);
        }
    }

    private class PoliceCrimeHolder extends RecyclerView.ViewHolder {

        public PoliceCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime_req_police, parent, false));
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Crime> crimes;
        private static final int NORMAL_CRIME = 0;
        private static final int REQUIRES_POLICE_CRIME = 1;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            switch (viewType) {
                case NORMAL_CRIME:
                    return new CrimeHolder(layoutInflater, parent);
                case REQUIRES_POLICE_CRIME:
                    return new PoliceCrimeHolder(layoutInflater, parent);
            }

            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public int getItemViewType(int position) {
            if (crimes.get(position).isRequiresPolice()) {
                return REQUIRES_POLICE_CRIME;
            } else {
                return NORMAL_CRIME;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int viewType = holder.getItemViewType();
            switch (viewType) {
                case NORMAL_CRIME:
                    ((CrimeHolder) holder).bind(crimes.get(position));
                case REQUIRES_POLICE_CRIME:

            }
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}
