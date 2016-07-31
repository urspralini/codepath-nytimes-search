package com.prabhu.codepath.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.models.FilterOptions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class SearchFiltersActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static final String FILTER_OPTIONS_KEY = "filterOptionsKey";
    private FilterOptions mFilterOptions;
    private EditText mBeginDate;
    private Spinner mSpinner;
    private CheckBox mArts;
    private CheckBox mFashion;
    private CheckBox mSports;
    private DatePickerDialog mDPD;
    public static final int DEFAULT_SORT_POSITION = 0;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private boolean dateSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFilterOptions = getIntent().getParcelableExtra(FILTER_OPTIONS_KEY);
        setContentView(R.layout.activity_search_filters);
        mBeginDate = (EditText)findViewById(R.id.etBeginDate);
        mSpinner = (Spinner)findViewById(R.id.spinnerSortOrder);
        mArts = (CheckBox)findViewById(R.id.checkbox_arts);
        mFashion = (CheckBox)findViewById(R.id.checkbox_fashion);
        mSports = (CheckBox)findViewById(R.id.checkbox_sports);
        Calendar now = Calendar.getInstance();
        mDPD = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort_order_array,
                android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setSelection(DEFAULT_SORT_POSITION);
        mBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDPD.show(getFragmentManager(),"Datepickerdialog");
            }
        });
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                final String spinnerText = spinnerAdapter.getItem(position).toString();
                mFilterOptions.setSortOrder(FilterOptions.SortOrder.valueOf(spinnerText));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        populateUI(mFilterOptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filters_search, menu);
        MenuItem saveItem = menu.findItem(R.id.action_save);
        saveItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent data = new Intent();
                if(mBeginDate.getText().length() == 0) {
                    mFilterOptions.setDateSelected(false);
                }
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
                mFilterOptions.setYear(SearchFiltersActivity.this.year);
                mFilterOptions.setMonthOfYear(SearchFiltersActivity.this.monthOfYear);
                mFilterOptions.setDayOfMonth(SearchFiltersActivity.this.dayOfMonth);
                mFilterOptions.setDateSelected(SearchFiltersActivity.this.dateSelected);
                data.putExtra(FILTER_OPTIONS_KEY, mFilterOptions);
                setResult(RESULT_OK, data);
                finish();
                return true;
            }
        });
        return true;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mBeginDate.setText(String.format("%d/%d/%d", monthOfYear+1, dayOfMonth, year));
        this.year = year;
        this.dayOfMonth = dayOfMonth;
        dateSelected = true;
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
