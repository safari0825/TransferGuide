package jp.co.pp.transferguide;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected int viaCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button srchButton = (Button) findViewById(R.id.srchButton);
        final EditText text1 = (EditText)findViewById(R.id.startInput);
        final EditText text2 = (EditText)findViewById(R.id.arriveInput);

        final GridLayout g1= (GridLayout)findViewById(R.id.g1);

        final TextView txtVia = (TextView) findViewById(R.id.via);

        final ImageButton reverseBtn = (ImageButton)findViewById(R.id.reverseBtn);

        reverseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = text1.getText().toString() ;
                text1.setText(text2.getText());
                text2.setText(tmp);
            }
        });

        txtVia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viaCount >= 3) {
                    return;
                }

                if (viaCount == 2) {
                    txtVia.setVisibility(View.GONE);
                }
                for(int i = 0; i <= viaCount;i++) {
                    LinearLayout delLine = (LinearLayout)findViewById(10 + i);
                    g1.removeView(delLine);
                }
                for (int i = 0 ; i <= viaCount;i++) {
                    TextView viaTitle = new TextView(MainActivity.this);
                    viaTitle.setText(getApplication().getString(R.string.via) + (i + 1));
                    viaTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                    viaTitle.setGravity(Gravity.CENTER_VERTICAL);

                    EditText viaInput = new EditText(MainActivity.this);
                    viaInput.setHint(getApplication().getString(R.string.InputVia) + (i + 1));
                    viaInput.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                    viaInput.setEms(10);

                    ImageButton delBtn = new ImageButton(MainActivity.this);
                    delBtn.setId(i);
                    delBtn.setImageResource(R.mipmap.del);
                    delBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    delBtn.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                    delBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int clickLineIdx = v.getId();
                            LinearLayout delLine = (LinearLayout) findViewById(10 + clickLineIdx );
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

                    LinearLayout line1Via = new LinearLayout(MainActivity.this);
                    line1Via.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
                    line1Via.setId(10+i);
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

        srchButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Text1:" + text1.getText() ,Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"Text2:" + text2.getText() ,Toast.LENGTH_LONG).show();
            }
        });
    }

//    OnClickListener addViaBtnClick = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            final GridLayout g1= (GridLayout)findViewById(R.id.g1);
//            final TextView txtVia = (TextView) findViewById(R.id.via);
//
//
//        }
//    };
//
//    OnClickListener delViaBtnClick = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            final GridLayout g1= (GridLayout)findViewById(R.id.g1);
//
//            final TextView txtVia = (TextView) findViewById(R.id.via);
//
//            RedrawViaGrid(g1);
//        }
//    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
