package jp.co.pp.transferguide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class SrchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srch_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        final LinearLayoutManager listItemManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(listItemManager);

        final ListAdapter sampleRecyclerAdapter = new ListAdapter(getApplicationContext());
        // 填充Adapter
        recyclerView.setAdapter(sampleRecyclerAdapter);

    }
}
