package jp.co.pp.transferguide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TransferFragment extends Fragment {

    protected int viaCount = 0;

    TextView txtFrom ;

    TextView txtTo;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("txtFrom",(String)txtFrom.getText());
        outState.putString("txtTo",(String)txtTo.getText());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 再生成時にはsavedInstanceStateがnullじゃない
        if (savedInstanceState != null) {
            txtFrom.setText(savedInstanceState.getString("txtFrom"));
            txtTo.setText(savedInstanceState.getString("txtTo"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View transferLayout = inflater.inflate(R.layout.content_main,container, false);

        setTransferFragment(transferLayout);
        return transferLayout;
    }

    private  void setTransferFragment(final View rootView) {
        final Button srchButton = (Button) rootView.findViewById(R.id.btnSearch);
        final RelativeLayout btnFrom = (RelativeLayout)rootView.findViewById(R.id.btnFrom);
        final RelativeLayout btnTo = (RelativeLayout)rootView.findViewById(R.id.btnTo);

        txtFrom = (TextView) btnFrom.findViewById(R.id.txtFrom);

        txtTo = (TextView)  btnTo.findViewById(R.id.txtTo);

        Bundle para = getArguments();
        if(para != null) {
            String stationFrom = getArguments().getString("StationNameFrom");
            String stationTo = getArguments().getString("StationNameTo");

            if (stationFrom != null && !stationFrom.isEmpty()) {
                txtFrom.setText(stationFrom);
            }
            if (stationTo != null && !stationTo.isEmpty()) {
                txtTo.setText(stationTo);
            }
        }
        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationFragment stationFragment = new StationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                Bundle para = new Bundle();
                para.putString("FromPara", "fromBtnFrom");
                stationFragment.setArguments(para);
                transaction.replace(R.id.content, stationFragment).commit();
            }
        });

        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationFragment stationFragment = new StationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                Bundle para = new Bundle();
                para.putString("FromPara", "fromBtnTo");
                stationFragment.setArguments(para);
                transaction.replace(R.id.content, stationFragment).commit();
            }
        });

        //final GridLayout g1= (GridLayout)rootView.findViewById(R.id.g1);

        //final TextView txtVia = (TextView) rootView.findViewById(R.id.via);

        final ImageView reverseBtn = (ImageView)rootView.findViewById(R.id.btnSwap);

        reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String tmp = text1.getText().toString();
//                text1.setText(text2.getText());
//                text2.setText(tmp);
            }
        });

        srchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Text1:" + text1.getText(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), "Text2:" + text2.getText(), Toast.LENGTH_LONG).show();

                Intent toRlstInt = new Intent(getActivity(), SrchResultActivity.class);
                startActivityForResult(toRlstInt,0);
            }
        });
    }
}
