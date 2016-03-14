package jp.co.pp.transferguide;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
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
import android.widget.AdapterView;
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
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public NaviListAdapter mDrawerAdapter;

    private FragmentManager fragmentManager;

    private TransferFragment transferFragment;

    private String contentTitle = "";

    private void setTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 0; i < 3; i++)
            tabLayout.addTab(tabLayout.newTab().setText("选项卡" + i));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initContentFragment() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transferFragment == null) {
            // 如果TransferFragment为空，则创建一个并添加到界面上
            transferFragment = new TransferFragment();
            transaction.add(R.id.content, transferFragment);
        } else {
            // 如果TransferFragment不为空，则直接将它显示出来
            //transaction.show(transferFragment);
            transaction.replace(R.id.content, transferFragment);
        }
        transaction.commit();
    }

    private  void setTransferFragment() {
        final Button srchButton = (Button) findViewById(R.id.srchButton);
        final EditText text1 = (EditText)findViewById(R.id.startInput);
        final EditText text2 = (EditText)findViewById(R.id.arriveInput);

        final GridLayout g1= (GridLayout)findViewById(R.id.g1);

        final TextView txtVia = (TextView) findViewById(R.id.via);

        final ImageButton reverseBtn = (ImageButton)findViewById(R.id.reverseBtn);

        reverseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = text1.getText().toString();
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
                for (int i = 0; i <= viaCount; i++) {
                    LinearLayout delLine = (LinearLayout) findViewById(10 + i);
                    g1.removeView(delLine);
                }
                for (int i = 0; i <= viaCount; i++) {
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
                            LinearLayout delLine = (LinearLayout) findViewById(10 + clickLineIdx);
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

        srchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Text1:" + text1.getText(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Text2:" + text2.getText(), Toast.LENGTH_LONG).show();

                Intent toRlstInt = new Intent(getApplicationContext(), SrchResultActivity.class);
                startActivityForResult(toRlstInt,0);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();

        toolbarSet();

        //setTab();

        initContentFragment();

        //setTransferFragment();

    }

    private void initDrawer(){

         //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         //getSupportActionBar().setHomeButtonEnabled(true);

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


        mDrawerAdapter = new NaviListAdapter(this, R.layout.listnaviitem, dataList);
        mDrawerList.setAdapter(new NaviListAdapter(this, R.layout.listnaviitem, dataList));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
            selectItem(position);
        }

        public void selectItem(int position) {

            switch (position) {
                case 0:
                    initContentFragment();
                    break;
                case 1:
                    RouteMapFragment routeFragment = new RouteMapFragment();
                    FragmentManager fragmentManager1 = getFragmentManager();
                    FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                    transaction1.replace(R.id.content, routeFragment).commit();
                    break;
                case 2:
                    StationFragment stationFragment = new StationFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, stationFragment).commit();
                    break;
                default:
                    break;
            }

            mDrawerList.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mDrawerList);
            contentTitle = mDrawerAdapter.getItem(position).text;
        }
    }

    private void toolbarSet(){
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
       // Set an OnMenuItemClickListener to handle menu item clicks
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                return true;
            }
        });
// Inflate a menu to be displayed in the toolbar

        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //把toolbar和drawerlayout连起来
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("请选择");
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                if(contentTitle.isEmpty()) {
                    getSupportActionBar().setTitle(R.string.title);
                }else {
                    getSupportActionBar().setTitle(contentTitle);
                }
                invalidateOptionsMenu();
            }
        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
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
