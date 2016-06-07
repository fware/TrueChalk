package com.wareshopc.app.truechalk.sportselector.basketball;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.sportselector.basketball.db.BasketballDatabaseHandler;

import java.util.UUID;


public class BasketballReportFragment extends Fragment {


    private TextView tvEventName;
    private TextView tvMIN;
    private TextView tvPTS;
    private TextView tvAST;
    private TextView tvOREB;
    private TextView tvDREB;
    private TextView tvBLK;
    private TextView tvTO;
    private BasketballChalk mChalk;
    private UUID mChalkId;
    private BasketballDatabaseHandler mDb;

    // TODO: Rename and change types and number of parameters
    public static BasketballReportFragment newInstance(UUID chalkId) {
        Bundle args = new Bundle();
        args.putSerializable(BasketballChalkFragment.EXTRA_TRUECHALK_ID, chalkId);
        BasketballReportFragment fragment = new BasketballReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public BasketballReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChalkId = (UUID) getArguments().getSerializable(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
            mDb = new BasketballDatabaseHandler(getActivity());
            mChalk = mDb.getBasketballChalk(mChalkId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_basketball_report, container, false);

        tvMIN = (TextView) v.findViewById(R.id.tvMIN_Results);
        tvMIN.setText("20.4");

        tvPTS = (TextView) v.findViewById(R.id.tvPTS_Results);
        tvPTS.setText(String.valueOf(mChalk.getPTS()));

        tvAST = (TextView) v.findViewById(R.id.tvAST_Results);
        tvAST.setText(String.valueOf(mChalk.getAST()));

        tvOREB = (TextView) v.findViewById(R.id.tvOREB_Results);
        tvOREB.setText(String.valueOf(mChalk.getOREB()));

        tvDREB = (TextView) v.findViewById(R.id.tvDREB_Results);
        tvDREB.setText(String.valueOf(mChalk.getDREB()));

        tvBLK = (TextView) v.findViewById(R.id.tvBLK_Results);
        tvBLK.setText(String.valueOf(mChalk.getBLK()));

        tvTO = (TextView) v.findViewById(R.id.tvTO_Results);
        tvTO.setText(String.valueOf(mChalk.getTO()));

        return v;
    }


}
