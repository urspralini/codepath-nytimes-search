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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.prabhu.codepath.nytimessearch.R;
import com.prabhu.codepath.nytimessearch.models.FilterOptions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class SearchFiltersActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static final String FILTER_OPTIONS_KEY = "filterOptionsKey";
    private FilterOptions mFilterOptions = new FilterOptions();
    private EditText mBeginDate;
    private Spinner mSpinner;
    private CheckBox mArts;
    private CheckBox mFashion;
    private CheckBox mSports;
    private DatePickerDialog mDPD;
    public static final int DEFAULT_SORT_POSITION = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean b) {
                switch (view.getId()) {
                    case R.id.checkbox_arts:
                        final String arts = getString(R.string.arts);
                        if(view.isChecked()) {
                            mFilterOptions.addNewsDesk(arts);
                        }else {
                            mFilterOptions.removeNewsDesk(arts);
                        }
                        break;
                    case R.id.checkbox_fashion:
                        final String fashionStyle = getString(R.string.fashion_style);
                        if(view.isChecked()) {
                            mFilterOptions.addNewsDesk(fashionStyle);
                        }else {
                            mFilterOptions.removeNewsDesk(fashionStyle);
                        }
                        break;
                    case R.id.checkbox_sports:
                        final String sports = getString(R.string.sports);
                        if(view.isChecked()) {
                            mFilterOptions.addNewsDesk(sports);
                        }else {
                            mFilterOptions.removeNewsDesk(sports);
                        }
                        break;
                }
            }
        };
        mBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDPD.show(getFragmentManager(),"Datepickerdialog");
            }
        });
        mArts.setOnCheckedChangeListener(checkedChangeListener);
        mFashion.setOnCheckedChangeListener(checkedChangeListener);
        mSports.setOnCheckedChangeListener(checkedChangeListener);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filters_search_menu, menu);
        MenuItem saveItem = menu.findItem(R.id.action_save);
        saveItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent data = new Intent();
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
        mFilterOptions.setDate(String.format("%d%02d%02d",year, monthOfYear+1, dayOfMonth));
    }
}
