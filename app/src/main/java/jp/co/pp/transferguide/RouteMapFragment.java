package jp.co.pp.transferguide;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class RouteMapFragment extends Fragment {

    public SubsamplingScaleImageView imageView;
    public RouteMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View routeMapLayout = inflater.inflate(R.layout.fragment_route_map, container, false);
        this.imageView = (SubsamplingScaleImageView)routeMapLayout.findViewById(R.id.routemap);
        this.imageView.setMinimumScaleType(2);
        this.imageView.setImage(ImageSource.asset("routemap/map_kanto.png"));
        //this.imageView.setOnTouchListener(getTouchListener());
        return routeMapLayout;
    }
}
