package com.wareshopc.app.truechalk.sportselector.basketball;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.wareshopc.app.truechalk.R;

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
    private ImageView mImageViewPTS1Up;  private ImageView mImageViewPTS1Down;
    private ImageView mImageViewPTS2Up;  private ImageView mImageViewPTS2Down;
    private ImageView mImageViewPTS3Up;  private ImageView mImageViewPTS3Down;
    private EditText mPTSEntry;

    private ImageView mImageViewOREB1Up;  private ImageView mImageViewREB1Down;
    private EditText mOREBEntry;

    private ImageView mImageViewAST1Up;  private ImageView mImageViewAST1Down;
    private EditText mASTEntry;

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
        if (getArguments() != null) {
            mChalkId = (UUID) getArguments()
                    .getSerializable(BasketballChalkFragment.EXTRA_TRUECHALK_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_basketball_accumulate, container, false);

        mPTSEntry = (EditText) v.findViewById(R.id.tv_points_entry);
        mPTSEntry.setText(Integer.toString(0));
        mPTSEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // this space intentionally left blank
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this one too
            }
        });

        mImageViewPTS1Up = (ImageView) v.findViewById(R.id.imageview_points_1up);
        mImageViewPTS1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS1Up.setClickable(true);
        mImageViewPTS1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewPTS1Down = (ImageView) v.findViewById(R.id.imageview_points_1down);
        mImageViewPTS1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS1Down.setClickable(true);
        mImageViewPTS1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        mImageViewPTS2Up = (ImageView) v.findViewById(R.id.imageview_points_2up);
        mImageViewPTS2Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS2Up.setClickable(true);
        mImageViewPTS2Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus2Arrow");
            }
        });

        mImageViewPTS2Down = (ImageView) v.findViewById(R.id.imageview_points_2down);
        mImageViewPTS2Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS2Down.setClickable(true);
        mImageViewPTS2Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus2Arrow");
            }
        });

        mImageViewPTS3Up = (ImageView) v.findViewById(R.id.imageview_points_3up);
        mImageViewPTS3Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPTS3Up.setClickable(true);
        mImageViewPTS3Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus3Arrow");
            }
        });

        mImageViewPTS3Down = (ImageView) v.findViewById(R.id.imageview_points_3down);
        mImageViewPTS3Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPTS3Down.setClickable(true);
        mImageViewPTS3Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus3Arrow");
            }
        });

        mOREBEntry = (EditText) v.findViewById(R.id.tv_oreb_entry);
        mOREBEntry.setText(Integer.toString(0));
        mOREBEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // this space intentionally left blank
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this one too
            }
        });

        mImageViewOREB1Up = (ImageView) v.findViewById(R.id.imageview_oreb_1up);
        mImageViewOREB1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewOREB1Up.setClickable(true);
        mImageViewOREB1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewREB1Down = (ImageView) v.findViewById(R.id.imageview_oreb_1down);
        mImageViewREB1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewREB1Down.setClickable(true);
        mImageViewREB1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        mASTEntry = (EditText) v.findViewById(R.id.tv_ast_entry);
        mASTEntry.setText(Integer.toString(0));
        mASTEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // this space intentionally left blank
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this one too
            }
        });

        mImageViewAST1Up = (ImageView) v.findViewById(R.id.imageview_ast_1up);
        mImageViewAST1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewAST1Up.setClickable(true);
        mImageViewAST1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewAST1Down = (ImageView) v.findViewById(R.id.imageview_ast_1down);
        mImageViewAST1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewAST1Down.setClickable(true);
        mImageViewAST1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        return v;
    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    //public interface OnFragmentInteractionListener {
    //    // TODO: Update argument type and name
    //    public void onFragmentInteraction(Uri uri);
    //}

}
