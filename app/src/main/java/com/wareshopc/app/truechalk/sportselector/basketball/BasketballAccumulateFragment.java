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
    private ImageView mImageViewPlus1Arrow;
    private ImageView mImageViewPlus2Arrow;
    private ImageView mImageViewPlus3Arrow;

    private ImageView mImageViewMinus1Arrow;
    private ImageView mImageViewMinus2Arrow;
    private ImageView mImageViewMinus3Arrow;

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

        mImageViewPlus1Arrow = (ImageView) v.findViewById(R.id.imageview_plus1_arrow);
        mImageViewPlus1Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus1Arrow.setClickable(true);
        mImageViewPlus1Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewMinus1Arrow = (ImageView) v.findViewById(R.id.imageview_minus1_arrow);
        mImageViewMinus1Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus1Arrow.setClickable(true);
        mImageViewMinus1Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        mImageViewPlus2Arrow = (ImageView) v.findViewById(R.id.imageview_plus2_arrow);
        mImageViewPlus2Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus2Arrow.setClickable(true);
        mImageViewPlus2Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus2Arrow");
            }
        });

        mImageViewMinus2Arrow = (ImageView) v.findViewById(R.id.imageview_minus2_arrow);
        mImageViewMinus2Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus2Arrow.setClickable(true);
        mImageViewMinus2Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus2Arrow");
            }
        });

        mImageViewPlus3Arrow = (ImageView) v.findViewById(R.id.imageview_plus3_arrow);
        mImageViewPlus3Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus3Arrow.setClickable(true);
        mImageViewPlus3Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus3Arrow");
            }
        });

        mImageViewMinus3Arrow = (ImageView) v.findViewById(R.id.imageview_minus3_arrow);
        mImageViewMinus3Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus3Arrow.setClickable(true);
        mImageViewMinus3Arrow.setOnClickListener(new View.OnClickListener() {
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
