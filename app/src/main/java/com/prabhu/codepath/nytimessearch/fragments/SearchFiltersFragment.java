package com.prabhu.codepath.nytimessearch.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.models.FilterOptions;

/**
 * Created by pbabu on 7/31/16.
 */
public class SearchFiltersFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public static final String FILTER_OPTIONS_KEY = "filterOptionsKey";
    private FilterOptions mFilterOptions;
    private EditText mBeginDate;
    private Spinner mSpinner;
    private CheckBox mArts;
    private CheckBox mFashion;
    private CheckBox mSports;
    private Button mSaveButton;
    public static final int DEFAULT_SORT_POSITION = 0;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private boolean dateSelected;
    private FilterOptionsUpdateListener mFilterOptionsUpdateListener;

    public SearchFiltersFragment() {
    }

    public static SearchFiltersFragment newInstance(String title){
        SearchFiltersFragment fragment = new SearchFiltersFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_filters, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFilterOptions = getArguments().getParcelable(SearchFiltersFragment.FILTER_OPTIONS_KEY);
        mFilterOptionsUpdateListener = (FilterOptionsUpdateListener)getActivity();
        mBeginDate = (EditText)view.findViewById(R.id.etBeginDate);
        mSpinner = (Spinner)view.findViewById(R.id.spinnerSortOrder);
        mArts = (CheckBox)view.findViewById(R.id.checkbox_arts);
        mFashion = (CheckBox)view.findViewById(R.id.checkbox_fashion);
        mSports = (CheckBox)view.findViewById(R.id.checkbox_sports);
        mSaveButton = (Button)view.findViewById(R.id.btnSave);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBeginDate.getText().length() == 0) {
                    mFilterOptions.setDateSelected(false);
                }
                final String sortOrder = mSpinner.getSelectedItem().toString();
                mFilterOptions.setSortOrder(FilterOptions.SortOrder.valueOf(sortOrder));
                final String arts = getString(R.string.arts);
                if(mArts.isChecked()) {
                    mFilterOptions.addNewsDesk(arts);
                }else {
                    mFilterOptions.removeNewsDesk(arts);
                }
                final String fashionStyle = getString(R.string.fashion_style);
                if(mFashion.isChecked()) {
                    mFilterOptions.addNewsDesk(fashionStyle);
                }else {
                    mFilterOptions.removeNewsDesk(fashionStyle);
                }
                final String sports = getString(R.string.sports);
                if(mSports.isChecked()) {
                    mFilterOptions.addNewsDesk(sports);
                }else {
                    mFilterOptions.removeNewsDesk(sports);
                }
                mFilterOptions.setYear(SearchFiltersFragment.this.year);
                mFilterOptions.setMonthOfYear(SearchFiltersFragment.this.monthOfYear);
                mFilterOptions.setDayOfMonth(SearchFiltersFragment.this.dayOfMonth);
                mFilterOptions.setDateSelected(SearchFiltersFragment.this.dateSelected);
                if(mFilterOptionsUpdateListener != null) {
                    mFilterOptionsUpdateListener.onFilterOptionsChanged(mFilterOptions);
                    getDialog().dismiss();
                }
            }
        });
        mSpinner.setSelection(DEFAULT_SORT_POSITION);
        mBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        populateUI(mFilterOptions);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        mBeginDate.setText(String.format("%d/%d/%d", monthOfYear+1, dayOfMonth, year));
        this.year = year;
        this.monthOfYear=monthOfYear;
        this.dayOfMonth = dayOfMonth;
        dateSelected = true;
    }

    public void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.mListener = this;
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public interface FilterOptionsUpdateListener {
        void onFilterOptionsChanged(FilterOptions filterOptions);
    }

    private void populateUI(FilterOptions mFilterOptions) {
        if(mFilterOptions.getDate() != null) {
            mBeginDate.setText(mFilterOptions.getDate());
        }
        if(mFilterOptions.getSortOrder() != null) {
            switch(mFilterOptions.getSortOrder()) {
                case Newest:
                    mSpinner.setSelection(FilterOptions.SortOrder.Newest.ordinal());
                    break;
                default:
                    mSpinner.setSelection(FilterOptions.SortOrder.Oldest.ordinal());
            }
        }
        mArts.setChecked(false);
        mFashion.setChecked(false);
        mSports.setChecked(false);
        if(mFilterOptions.getNewsDeskValues() != null) {
            for(String newsDeskValue : mFilterOptions.getNewsDeskValues()) {
                if(newsDeskValue.equals(getString(R.string.arts))) {
                    mArts.setChecked(true);
                }
                if(newsDeskValue.equals(getString(R.string.sports))) {
                    mSports.setChecked(true);
                }
                if(newsDeskValue.equals(getString(R.string.fashion_style))) {
                    mFashion.setChecked(true);
                }
            }
        }
    }
}
