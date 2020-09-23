package com.example.my_hw2_v1;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LinearLayout mButtons;
    private boolean clicked = false;

    private File file;
    private Accelerometer accelerometer;
    private String outcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        FloatingActionButton fabStart = findViewById(R.id.floatingActionButtonStart);
        FloatingActionButton fabStop = findViewById(R.id.floatingActionButtonStop);
        Button clearButton = findViewById(R.id.button_clear);
        final TextView acText = findViewById(R.id.acText);

        file = new File(getApplicationContext().getFilesDir(),"coordinates.json");

        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {

                outcome = "x: "+tx+" y: "+ty;
                acText.setText(outcome);
            }
        });

        mButtons = findViewById(R.id.ani_but);
        mButtons.setVisibility(View.GONE);
        fabStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){

                    clicked=false;
                    acText.setVisibility(View.GONE);

                }else{
                    clicked=true;
                    acText.setVisibility(View.VISIBLE);

                }
            }
        });
        fabStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=false;
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.hide_buttons);
                mButtons.startAnimation(animFadeOut);
                mButtons.setVisibility(View.GONE);
                acText.setVisibility(View.GONE);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                file.delete();
            }
        });
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

       // mMap.setOnMapLongClickListener(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera


        LatLng home = new LatLng(52.347169, 16.864962);
        try {
            ReadFile(file);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
    }

    public void onZoom(View view) {
        if(view.getId() == R.id.imageButtonZoomIn){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.imageButtonZoomOut){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    @Override
    public void onMapLongClick(LatLng point) {
        double x2 = point.latitude;
        double y2 = point.longitude;
        mMap.addMarker(new MarkerOptions().position(point).title("Position:("+Math.floor(point.latitude * 100 )/100+", "+Math.floor(point.longitude* 100 )/100+")").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("x",x2);
            jsonObject.put("y",y2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        WriteFile(jsonObject);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(mButtons.getVisibility()==View.GONE){
            mButtons.setVisibility(View.VISIBLE);
            Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.show_buttons);
            mButtons.startAnimation(animFadeIn);
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
    }
    public void ReadFile(File strFile) throws JSONException {

        String strBuffer;
        BufferedReader buffRead;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            buffRead = new BufferedReader(new FileReader(strFile));

            while ((strBuffer = buffRead.readLine())!=null){
                stringBuilder.append(strBuffer).append("\n");
                JSONObject jsonObject = new JSONObject(strBuffer);
                Double x = (Double) jsonObject.get("x");
                Double y = (Double) jsonObject.get("y");
                LatLng point = new LatLng(x,y);
                mMap.addMarker(new MarkerOptions().position(point).title("Position:("+Math.floor(point.latitude * 100 )/100+", "+Math.floor(point.longitude * 100 )/100+")").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Log.i("TEST", jsonObject +" String x: "+x+" String y: "+y);
            }
            buffRead.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        String response = stringBuilder.toString();
        Log.i("Check",response);

        }
     public void WriteFile( JSONObject jsonObject){

         String coordinates = jsonObject.toString()+"\n";
         Log.i("MyActivity","Json object "+coordinates);

         FileWriter fileWriter  = null;
         try {
             fileWriter = new FileWriter(file, true);
         } catch (IOException e) {
             e.printStackTrace();
         }
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         try {
             bufferedWriter.write(coordinates);
             bufferedWriter.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


}