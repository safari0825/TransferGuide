package jp.co.pp.transferguide;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected int viaCount = 0;

    private DrawerLayout layDrawer;
    private ListView lstDrawer;

    private ActionBarDrawerToggle drawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 0; i < 3; i++)
            tabLayout.addTab(tabLayout.newTab().setText("选项卡" + i));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

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

                Intent toRlstInt = new Intent(getApplicationContext(),SrchResultActivity.class);
                startActivityForResult(toRlstInt,0);
            }
        });
    }

    private void initDrawer(){

         //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         //getSupportActionBar().setHomeButtonEnabled(true);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        String[] drawer_menu = this.getResources().getStringArray(R.array.drawer_menu);
        List<NaviListAdapter.BindData> dataList = new ArrayList<NaviListAdapter.BindData>();
        for(int i =0 ; i < drawer_menu.length;i++) {
            NaviListAdapter.BindData tmpData = new NaviListAdapter.BindData(0,drawer_menu[i]);
            dataList.add(tmpData);
        }


        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerLayout.setDrawerTitle(GravityCompat.START,"Drawer Title");

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,
        //        android.R.layout.simple_expandable_list_item_1, drawer_menu));

        mDrawerList.setAdapter(new NaviListAdapter(this,R.layout.listnaviitem,dataList));

        //layDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //lstDrawer = (ListView) findViewById(R.id.left_drawer);

        //NaviListAdapter adapter = new NaviListAdapter(this, null);
        //lstDrawer.setAdapter(adapter);

       // layDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

//        mTitle = mDrawerTitle = getTitle();
//        drawerToggle = new ActionBarDrawerToggle(
//                this,
//                layDrawer,
//                //R.mipmap.ic_drawer,
//                R.string.drawer_open,
//                R.string.drawer_close) {
//
//            @Override
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(mTitle);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle(mDrawerTitle);
//            }
//        };
//        drawerToggle.syncState();
//
//        layDrawer.setDrawerListener(drawerToggle);
    }

    private void initDrawerList(){
        //String[] drawer_menu = this.getResources().getStringArray(R.array.drawer_menu);

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
