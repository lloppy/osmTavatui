package fr.insa.openstreetmapapp;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load( getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_main);
        map =  findViewById(R.id.map);


        map.setTileSource(TileSourceFactory.MAPNIK);    //render
        map.setBuiltInZoomControls(true);               //zoomable
        GeoPoint startPoint = new GeoPoint(57.174103, 60.204252);
        IMapController mapController = map.getController();
        mapController.setZoom(20.0);
        mapController.setCenter(startPoint);


//0
        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem("Таватуй", "Загородный оздоровительный центр", startPoint);
        items.add(home);
        //items.add(new OverlayItem("qlqpart", "lol", new GeoPoint(43.570815, 1.466282)));

//1 денежкин камень
        ArrayList<OverlayItem> items1 = new ArrayList<>();
        items1.add(new OverlayItem("Денежкин камень", "Заповедник", new GeoPoint(60.422222, 59.541389)));

//2
        ArrayList<OverlayItem> items2 = new ArrayList<>();
        OverlayItem home2 = new OverlayItem("qlqpart", "lol", new GeoPoint(44.570815, 12.466282));
        items2.add(home2);



//1
        ItemizedOverlayWithFocus<OverlayItem> mOverlay1 = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items1, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem home1) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });


//0
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem home) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });


//2
        ItemizedOverlayWithFocus<OverlayItem> mOverlay2 = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items2, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem home2) {
                Intent intent = new Intent(MainActivity.this, MainActivity4
                        .class);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

//0
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
//1
        mOverlay1.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay1);
//2
        mOverlay2.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay2);

    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }



}