package jp.co.pp.transferguide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private String[] mRltLine1;
    private String[] mRltLine2;
    private String[] mRltLine3;

    public ListAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mRltLine1 = context.getResources().getStringArray(R.array.resultLine1);
        mRltLine2 = context.getResources().getStringArray(R.array.resultLine2);
        mRltLine3 = context.getResources().getStringArray(R.array.resultLine3);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.list_basic_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.mRltNo.setText(mTitles[position]);
        holder.mRltL1.setText(mRltLine1[position]);
        holder.mRltL2.setText(mRltLine2[position]);
        holder.mRltL3.setText(mRltLine3[position]);
    }

    @Override
    public int getItemCount() {

        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView mRltNo;
        TextView mRltL1;
        TextView mRltL2;
        TextView mRltL3;
        NormalTextViewHolder(View view) {
            super(view);
            mRltNo = (TextView)view.findViewById(R.id.rltNo);
            mRltL1 = (TextView)view.findViewById(R.id.rltL1);
            mRltL2 = (TextView)view.findViewById(R.id.rltL2);
            mRltL3 = (TextView)view.findViewById(R.id.rltL3);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }
}
