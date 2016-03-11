package jp.co.pp.transferguide;

import android.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;


public class TransferFragment extends Fragment {

    protected int viaCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View transferLayout = inflater.inflate(R.layout.content_main,container, false);

        setTransferFragment(transferLayout);
        return transferLayout;
    }

    private  void setTransferFragment(final View rootView) {
        final Button srchButton = (Button) rootView.findViewById(R.id.srchButton);
        final EditText text1 = (EditText)rootView.findViewById(R.id.startInput);
        final EditText text2 = (EditText)rootView.findViewById(R.id.arriveInput);

        final GridLayout g1= (GridLayout)rootView.findViewById(R.id.g1);

        final TextView txtVia = (TextView) rootView.findViewById(R.id.via);

        final ImageButton reverseBtn = (ImageButton)rootView.findViewById(R.id.reverseBtn);

        reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = text1.getText().toString();
                text1.setText(text2.getText());
                text2.setText(tmp);
            }
        });

        txtVia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viaCount >= 3) {
                    return;
                }

                if (viaCount == 2) {
                    txtVia.setVisibility(View.GONE);
                }
                for (int i = 0; i <= viaCount; i++) {
                    LinearLayout delLine = (LinearLayout) rootView.findViewById(10 + i);
                    g1.removeView(delLine);
                }
                for (int i = 0; i <= viaCount; i++) {
                    TextView viaTitle = new TextView(getActivity());
                    viaTitle.setText(getResources().getString(R.string.via) + (i + 1));
                    viaTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    viaTitle.setGravity(Gravity.CENTER_VERTICAL);

                    EditText viaInput = new EditText(getActivity());
                    viaInput.setHint(getResources().getString(R.string.InputVia) + (i + 1));
                    viaInput.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    viaInput.setEms(10);

                    ImageButton delBtn = new ImageButton(getActivity());
                    delBtn.setId(i);
                    delBtn.setImageResource(R.mipmap.del);
                    delBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    delBtn.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                    delBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int clickLineIdx = v.getId();
                            LinearLayout delLine = (LinearLayout) rootView.findViewById(10 + clickLineIdx);
                            g1.removeView(delLine);
//                            for ( int i = clickLineIdx ; i < viaCount - 1; i++) {
//                                LinearLayout nextLine = (LinearLayout) findViewById(10 + (i + 1));
//                                g1.removeView(nextLine) ;
//                                g1.addView(nextLine,i);
//                            }
                            if (viaCount >= 3) {
                                txtVia.setVisibility(View.VISIBLE);
                            }
                            viaCount--;
                        }
                    });

                    LinearLayout line1Via = new LinearLayout(getActivity());
                    line1Via.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
                    line1Via.setId(10 + i);
                    line1Via.addView(viaTitle);
                    line1Via.addView(viaInput);
                    line1Via.addView(delBtn);

                    GridLayout.Spec rowSpec = GridLayout.spec(i, 1, GridLayout.UNDEFINED);
                    GridLayout.Spec colSpec = GridLayout.spec(0, 2, GridLayout.UNDEFINED);

                    GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
                    g1.addView(line1Via, params);
                }
                viaCount++;
            }
        });

        srchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Text1:" + text1.getText(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "Text2:" + text2.getText(), Toast.LENGTH_LONG).show();

                Intent toRlstInt = new Intent(getActivity(), SrchResultActivity.class);
                startActivityForResult(toRlstInt,0);
            }
        });
    }
}
