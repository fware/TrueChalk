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
    private ImageView mImageViewPoints1Up;
    private ImageView mImageViewPoints2Up;
    private ImageView mImageViewPoints3Up;

    private ImageView mImageViewPoints1Down;
    private ImageView mImageViewPoints2Down;
    private ImageView mImageViewPoints3Down;

    private EditText mPointsEntry;

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

        mPointsEntry = (EditText) v.findViewById(R.id.tv_points_entry);
        mPointsEntry.setText(Integer.toString(0));
        mPointsEntry.addTextChangedListener(new TextWatcher() {
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

        mImageViewPoints1Up = (ImageView) v.findViewById(R.id.imageview_points_1up);
        mImageViewPoints1Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPoints1Up.setClickable(true);
        mImageViewPoints1Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewPoints1Down = (ImageView) v.findViewById(R.id.imageview_points_1down);
        mImageViewPoints1Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPoints1Down.setClickable(true);
        mImageViewPoints1Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        mImageViewPoints2Up = (ImageView) v.findViewById(R.id.imageview_points_2up);
        mImageViewPoints2Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPoints2Up.setClickable(true);
        mImageViewPoints2Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus2Arrow");
            }
        });

        mImageViewPoints2Down = (ImageView) v.findViewById(R.id.imageview_points_2down);
        mImageViewPoints2Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPoints2Down.setClickable(true);
        mImageViewPoints2Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus2Arrow");
            }
        });

        mImageViewPoints3Up = (ImageView) v.findViewById(R.id.imageview_points_3up);
        mImageViewPoints3Up.setImageResource(R.drawable.grayuparrow);
        mImageViewPoints3Up.setClickable(true);
        mImageViewPoints3Up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus3Arrow");
            }
        });

        mImageViewPoints3Down = (ImageView) v.findViewById(R.id.imageview_points_3down);
        mImageViewPoints3Down.setImageResource(R.drawable.graydownarrow);
        mImageViewPoints3Down.setClickable(true);
        mImageViewPoints3Down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus3Arrow");
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
