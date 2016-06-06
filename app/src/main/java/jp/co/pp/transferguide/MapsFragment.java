package jp.co.pp.transferguide;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private GoogleMap mMap;

    private MapView mapView;
    private GoogleMap map;
    private Context context;

    public MapsFragment() {
    }

    public MapsFragment(Context c) {
        context = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mapLayout = inflater.inflate(R.layout.maps_fragment, container, false);
        mapView = (MapView) mapLayout.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();// needed to get the map to display immediately

        MapsInitializer.initialize(getActivity());
        map = mapView.getMap();
        onMapReady(map);
        return mapLayout;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location loc;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng now = new LatLng(loc.getLatitude(),loc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(now).title("Here You Are"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(now,18));

    }
}
