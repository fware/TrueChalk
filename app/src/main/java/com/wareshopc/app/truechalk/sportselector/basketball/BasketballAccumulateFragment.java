package com.wareshopc.app.truechalk.sportselector.basketball;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.sportselector.basketball.db.BasketballDatabaseHandler;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link BasketballAccumulateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketballAccumulateFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    private UUID mChalkId;
    private BasketballChalk mChalk;
    BasketballDatabaseHandler mDb;

    private ImageView mImageViewPTS1Up;  private ImageView mImageViewPTS1Down;
    private ImageView mImageViewPTS2Up;  private ImageView mImageViewPTS2Down;
    private ImageView mImageViewPTS3Up;  private ImageView mImageViewPTS3Down;
    private EditText mPTSEntry;

    private ImageView mImageViewOREB1Up;  private ImageView mImageViewOREB1Down;
    private EditText mOREBEntry;

    private ImageView mImageViewDREB1Up;  private ImageView mImageViewDREB1Down;
    private EditText mDREBEntry;

    private ImageView mImageViewAST1Up;  private ImageView mImageViewAST1Down;
    private EditText mASTEntry;

    private ImageView mImageViewBLK1Up;  private ImageView mImageViewBLK1Down;
    private EditText mBLKEntry;

    private ImageView mImageViewTO1Up;  private ImageView mImageViewTO1Down;
    private EditText mTOEntry;

    private Button mSaveButton;

    //Callbacks mCallbacks;

    /**
     * Required interface for hosting activities.
     */
    /*public interface Callbacks {
        void onBasketballChalkUpdated(BasketballChalk basketballChalk);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }*/

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static BasketballAccumulateFragment newInstance(UUID chalkId) {
        BasketballAccumulateFragment fragment = new BasketballAccumulateFragment();
        Bundle args = new Bundle();
        args.putSerializable(BasketballChalkFragment.EXTRA_TRUECHALK_ID, chalkId);
        fragment.setArguments(args);
        return fragment;
    }

    public BasketballAccumulateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = new BasketballDatabaseHandler(getActivity());

        if (getArguments() != null) {
            mChalkId = (UUID) getArguments()
                    .getSerializable(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
            //mChalk = BasketballChalkLab.get(getActivity()).getChalk(mChalkId);
            mChalk = mDb.getBasketballChalk(mChalkId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basketball_accumulate, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                // http://forums.bignerdranch.com/viewtopic.php?f=416&t=7919
                ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mPTSEntry = (EditText) v.findViewById(R.id.et_pts);
        mPTSEntry.setText(Integer.toString(0));

        mImageViewPTS1Up = (ImageView) v.findViewById(R.id.iv_pts_1up);
        mImageViewPTS1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS1Up.setClickable(true);
        mImageViewPTS1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts += 1;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
                //mCallbacks.onBasketballChalkUpdated(mChalk);
            }
        });

        mImageViewPTS1Down = (ImageView) v.findViewById(R.id.iv_pts_1down);
        mImageViewPTS1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS1Down.setClickable(true);
        mImageViewPTS1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts -= 1;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
            }
        });

        mImageViewPTS2Up = (ImageView) v.findViewById(R.id.iv_pts_2up);
        mImageViewPTS2Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS2Up.setClickable(true);
        mImageViewPTS2Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts += 2;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
            }
        });

        mImageViewPTS2Down = (ImageView) v.findViewById(R.id.iv_pts_2down);
        mImageViewPTS2Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS2Down.setClickable(true);
        mImageViewPTS2Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts -= 2;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
            }
        });

        mImageViewPTS3Up = (ImageView) v.findViewById(R.id.iv_pts_3up);
        mImageViewPTS3Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS3Up.setClickable(true);
        mImageViewPTS3Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts += 3;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
            }
        });

        mImageViewPTS3Down = (ImageView) v.findViewById(R.id.iv_pts_3down);
        mImageViewPTS3Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS3Down.setClickable(true);
        mImageViewPTS3Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pts = mChalk.getPTS();
                pts -= 3;
                mChalk.setPTS(pts);
                mPTSEntry.setText(Integer.toString(pts));
            }
        });

        mASTEntry = (EditText) v.findViewById(R.id.et_ast);
        mASTEntry.setText(Integer.toString(0));

        mImageViewAST1Up = (ImageView) v.findViewById(R.id.iv_ast_1up);
        mImageViewAST1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewAST1Up.setClickable(true);
        mImageViewAST1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ast = mChalk.getAST();
                ast += 1;
                mChalk.setAST(ast);
                mASTEntry.setText(Integer.toString(ast));
            }
        });

        mImageViewAST1Down = (ImageView) v.findViewById(R.id.iv_ast_1down);
        mImageViewAST1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewAST1Down.setClickable(true);
        mImageViewAST1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ast = mChalk.getAST();
                ast -= 1;
                mChalk.setAST(ast);
                mASTEntry.setText(Integer.toString(ast));
            }
        });

        mOREBEntry = (EditText) v.findViewById(R.id.et_oreb);
        mOREBEntry.setText(Integer.toString(0));

        mImageViewOREB1Up = (ImageView) v.findViewById(R.id.iv_oreb_1up);
        mImageViewOREB1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewOREB1Up.setClickable(true);
        mImageViewOREB1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int oreb = mChalk.getOREB();
                oreb += 1;
                mChalk.setOREB(oreb);
                mOREBEntry.setText(Integer.toString(oreb));
            }
        });

        mImageViewOREB1Down = (ImageView) v.findViewById(R.id.iv_oreb_1down);
        mImageViewOREB1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewOREB1Down.setClickable(true);
        mImageViewOREB1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int oreb = mChalk.getOREB();
                oreb -= 1;
                mChalk.setOREB(oreb);
                mOREBEntry.setText(Integer.toString(oreb));
            }
        });

        mBLKEntry = (EditText) v.findViewById(R.id.et_blk);
        mBLKEntry.setText(Integer.toString(0));

        mImageViewBLK1Up = (ImageView) v.findViewById(R.id.iv_blk_1up);
        mImageViewBLK1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewBLK1Up.setClickable(true);
        mImageViewBLK1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int blk = mChalk.getBLK();
                blk += 1;
                mChalk.setBLK(blk);
                mBLKEntry.setText(Integer.toString(blk));
            }
        });

        mImageViewBLK1Down = (ImageView) v.findViewById(R.id.iv_blk_1down);
        mImageViewBLK1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewBLK1Down.setClickable(true);
        mImageViewBLK1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int blk = mChalk.getBLK();
                blk -= 1;
                mChalk.setBLK(blk);
                mBLKEntry.setText(Integer.toString(blk));
            }
        });

        mDREBEntry = (EditText) v.findViewById(R.id.et_dreb);
        mDREBEntry.setText(Integer.toString(0));

        mImageViewDREB1Up = (ImageView) v.findViewById(R.id.iv_dreb_1up);
        mImageViewDREB1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewDREB1Up.setClickable(true);
        mImageViewDREB1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int dreb = mChalk.getDREB();
                dreb += 1;
                mChalk.setDREB(dreb);
                mDREBEntry.setText(Integer.toString(dreb));
            }
        });

        mImageViewDREB1Down = (ImageView) v.findViewById(R.id.iv_dreb_1down);
        mImageViewDREB1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewDREB1Down.setClickable(true);
        mImageViewDREB1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int dreb = mChalk.getDREB();
                dreb -= 1;
                mChalk.setDREB(dreb);
                mDREBEntry.setText(Integer.toString(dreb));
            }
        });

        mTOEntry = (EditText) v.findViewById(R.id.et_to);
        mTOEntry.setText(Integer.toString(0));

        mImageViewTO1Up = (ImageView) v.findViewById(R.id.iv_to_1up);
        mImageViewTO1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewTO1Up.setClickable(true);
        mImageViewTO1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int to = mChalk.getTO();
                to += 1;
                mChalk.setTO(to);
                mTOEntry.setText(Integer.toString(to));
            }
        });

        mImageViewTO1Down = (ImageView) v.findViewById(R.id.iv_to_1down);
        mImageViewTO1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewTO1Down.setClickable(true);
        mImageViewTO1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int to = mChalk.getTO();
                to -= 1;
                mChalk.setTO(to);
                mTOEntry.setText(Integer.toString(to));
            }
        });

        mSaveButton = (Button) v.findViewById(R.id.chalk_save_stats);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDb.updateBasketballChalk(mChalk);
                Intent intent = new Intent(getActivity(), BasketballChalkListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mDb.updateBasketballChalk(mChalk);
        //BasketballChalkLab.get(getActivity()).saveBasketballChalks();
    }

}
