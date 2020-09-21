package com.arikamat.ftcultimategoalscorer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
public class MainActivity  extends AppCompatActivity implements ElegantNumberButton.OnClickListener, CompoundButton.OnCheckedChangeListener  {
    private int score =0;
    private int autoscore =0;
    private int teleScore =0;
    private int endScore=0;
    private boolean autoWobbleGoal= false;
    private int autoHigh=0;
    private int autoMed=0;
    private int autoLow=0;
    private int autoPower=0;
    private int autoDelivery =0;
    private int teleHigh=0;
    private int teleMed=0;
    private int teleLow=0;
    private int endGameWobbleGoal=0;
    private int endGamePower=0;
    private int endGameRingsOnWobble=0;
    private int autoParkLaunchLine =0;
    int previousState =0;
    TextView scoreText;
    Switch delivery;
    Switch autoParking;
    RadioButton start,out,na;
    ElegantNumberButton autoHighButton,autoMedButton,autoLowButton, autoPowerButton, teleHighButton,teleMedButton, teleLowButton, endGamePowerButton, endGameRingsOnWobbleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.score);

        autoHighButton = findViewById(R.id.autoHighButton);
        autoHighButton.setOnClickListener(this);
        autoHighButton.setRange(0,7);
        autoMedButton = findViewById(R.id.autoMedButton);
        autoMedButton.setOnClickListener(this);
        autoMedButton.setRange(0,7);
        autoLowButton = findViewById(R.id.autoLowButton);
        autoLowButton.setOnClickListener(this);
        autoLowButton.setRange(0,7);
        autoPowerButton = findViewById(R.id.autoPowerButton);
        autoPowerButton.setOnClickListener(this);
        autoPowerButton.setRange(0,3);
        delivery = findViewById(R.id.deliverWobble);
        delivery.setChecked(false);
        delivery.setOnCheckedChangeListener(this);
        autoParking = findViewById(R.id.parking);
        autoParking.setChecked(false);
        autoParking.setOnCheckedChangeListener(this);

        teleHighButton = findViewById(R.id.teleHighButton);
        teleHighButton.setOnClickListener(this);
        teleHighButton.setRange(0,150);
        teleMedButton = findViewById(R.id.teleMedButton);
        teleMedButton.setOnClickListener(this);
        teleMedButton.setRange(0,150);
        teleLowButton = findViewById(R.id.teleLowButton);
        teleLowButton.setOnClickListener(this);
        teleLowButton.setRange(0,150);

        endGamePowerButton = findViewById(R.id.endPowerButton);
        endGamePowerButton.setOnClickListener(this);
        endGamePowerButton.setRange(0,3);

        endGameRingsOnWobbleButton = findViewById(R.id.endRingButton);
        endGameRingsOnWobbleButton.setOnClickListener(this);
        endGameRingsOnWobbleButton.setRange(0,20);
        start = findViewById(R.id.startLine);
        out = findViewById(R.id.outsideField);
        na = findViewById(R.id.nan);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.autoHighButton:
                autoHigh= Integer.parseInt(autoHighButton.getNumber());
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
                break;
            case R.id.autoMedButton:
                autoMed= Integer.parseInt(autoMedButton.getNumber());
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
                break;
            case R.id.autoLowButton:
                autoLow= Integer.parseInt(autoLowButton.getNumber());
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
                break;
            case R.id.autoPowerButton:
                autoPower= Integer.parseInt(autoPowerButton.getNumber());
                autoscore=(autoHigh*12) + (autoMed*6) +(autoLow*3) + (autoPower*15)+autoDelivery;
                update();
                break;
            case R.id.teleHighButton:
                teleHigh= Integer.parseInt(teleHighButton.getNumber());
                teleScore = (teleLow*2) + (teleMed*4) + (teleHigh*6);
                update();
                break;
            case R.id.teleMedButton:
                teleMed= Integer.parseInt(teleMedButton.getNumber());
                teleScore = (teleLow*2) + (teleMed*4) + (teleHigh*6);
                update();
                break;
            case R.id.teleLowButton:
                teleLow= Integer.parseInt(teleLowButton.getNumber());
                teleScore = (teleLow*2) + (teleMed*4) + (teleHigh*6);
                update();
                break;
            case R.id.endPowerButton:
                endGamePower= Integer.parseInt(endGamePowerButton.getNumber());
                endScore = (endGamePower*15) + (endGameRingsOnWobble*5) + previousState;
                update();
                break;
            case R.id.endRingButton:
                endGameRingsOnWobble= Integer.parseInt(endGameRingsOnWobbleButton.getNumber());
                endScore = (endGamePower*15) + (endGameRingsOnWobble*5) + previousState;
                update();
                break;
        }
    }
    public void onRadioButtonClicked(View v){
        if(v.getId() == R.id.startLine){
            previousState = 5;
            endScore = (endGamePower*15) + (endGameRingsOnWobble*5) + previousState;
            update();
        }
        else if(v.getId() == R.id.outsideField){
            previousState = 20;
            endScore = (endGamePower*15) + (endGameRingsOnWobble*5) + previousState;
            update();
        }
        else if(v.getId() == R.id.nan){
            previousState = 0;
            endScore = (endGamePower*15) + (endGameRingsOnWobble*5) + previousState;
            update();
        }
    }
    public void update(){
        int score = autoscore+teleScore+endScore;
        String scoreStr = "Score: "+ score;
        scoreText.setText(scoreStr);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.deliverWobble) {
            if (isChecked) {
                autoDelivery = 15;
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
            } else {
                autoDelivery = 0;
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
            }
        }
        else if(buttonView.getId() == R.id.parking) {
            if (isChecked) {
                autoParkLaunchLine = 5;
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
            } else {
                autoParkLaunchLine = 0;
                autoscore = (autoHigh * 12) + (autoMed * 6) + (autoLow * 3) + (autoPower * 15) + autoDelivery+autoParkLaunchLine;
                update();
            }
        }
    }

    public void reset(){
        autoHighButton.setNumber("0");
        autoMedButton.setNumber("0");
        autoLowButton.setNumber("0");
        autoPowerButton.setNumber("0");
        teleHighButton.setNumber("0");
        teleMedButton.setNumber("0");
        teleLowButton.setNumber("0");
        endGamePowerButton.setNumber("0");
        endGameRingsOnWobbleButton.setNumber("0");
        autoscore =0;
        teleScore=0;
        endScore=0;
        autoDelivery=0;
        start.setChecked(false);
        out.setChecked(false);
        na.setChecked(true);
        delivery.setChecked(false);
        start.setChecked(false);
        out.setChecked(false);
        na.setChecked(true);
        delivery.setChecked(false);
        update();
    }
}

