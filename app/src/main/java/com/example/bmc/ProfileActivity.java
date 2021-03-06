package com.example.bmc;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.rey.material.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import businessojects.StadiumDetails;
import butterknife.Bind;
import butterknife.ButterKnife;
import cache.UICache;

public class ProfileActivity extends AppCompatActivity {

    private static UICache uiCache = new UICache();
    ExpandableListView expandableListView;
    List<StadiumDetails> stadiumList;
    List<String> stadiumNames;
    Map<String,List<String>> stadiumDetailsMap = null;
    private StadiumDetails stadiumDetails;
    ExpandableListAdapter listAdapter;
    private Button addButton;
    private Button updateButton;
    private Button submitStadiumDetails;
    @Bind(R.id.fab_add)
    FloatingActionButton fab_add;
    @Bind(R.id.fab_update)
    FloatingActionButton fab_update;
    @Bind(R.id.fab_delete)
    FloatingActionButton fab_delete;
    @Bind(R.id.fab_plus)
    FloatingActionButton fab_plus;
    EditText stadiumName;
    Animation fabOpen,fabClose,fabClockWise,fabAntiClockWise;
    private android.widget.PopupWindow popupWindow;
    boolean isFabOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stadiumList = new ArrayList<StadiumDetails>();
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_profile);
        stadiumList.add(uiCache.getStadiumDetails());
        expandableListView = (ExpandableListView)findViewById(R.id.stadium_list);
        fillData();
        listAdapter = new adapters.ExpandableListAdapter(this,stadiumNames,stadiumDetailsMap);
        ButterKnife.bind(this);
        expandableListView.setAdapter(listAdapter);
        addButton = (Button)findViewById(R.id.button_bt_Add);
        updateButton = (Button)findViewById(R.id.button_bt_Update);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intiateAddPopupWindow();
            }
        });
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotate_clock_wise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotate_anticlock_wise);
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabOpen){
                    fab_add.startAnimation(fabClose);
                    fab_update.startAnimation(fabClose);
                    fab_delete.startAnimation(fabClose);
                    fab_plus.startAnimation(fabAntiClockWise);
                    fab_add.setClickable(false);
                    fab_update.setClickable(false);
                    fab_delete.setClickable(false);
                    isFabOpen = false;
                }else{
                    fab_add.startAnimation(fabOpen);
                    fab_update.startAnimation(fabOpen);
                    fab_delete.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabAntiClockWise);
                    fab_add.setClickable(true);
                    fab_update.setClickable(true);
                    fab_delete.setClickable(true);
                    isFabOpen = true;
                }
            }
        });
    }

    private void intiateAddPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) ProfileActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.add_popup, (ViewGroup) findViewById(R.id.add_popup));
        popupWindow = new PopupWindow(layout,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
    }

    private void initiateUpdatePopupWindow() {

    }

    private Map<String,List<String>> fillData(){
        stadiumNames = new ArrayList<String>();
        List<String> stadiumDetailsList= null;
        stadiumDetailsMap = new HashMap<String,List<String>>();
            stadiumDetailsList= new ArrayList<String>();
        stadiumDetails = stadiumList.get(0);
            stadiumNames.add(stadiumDetails.getStadiumName());
            stadiumDetailsList.add(stadiumDetails.getStadiumName());
            stadiumDetailsList.add(stadiumDetails.getNumberOfCourts());
            stadiumDetailsList.add(stadiumDetails.getSportName());
           stadiumDetailsList.add(stadiumDetails.getTimings());
            stadiumDetailsMap.put(stadiumDetails.getStadiumName(),stadiumDetailsList);
        return stadiumDetailsMap;
    }
    private void disableAllEditFields(int activity_profile) {
       /* try{
            for(int i = 0;i<activity_profile.)
        }catch(Exception e){

        }*/
    }

}
