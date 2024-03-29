package com.wareshopc.app.truechalk.sportselector.basketball;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wareshopc.app.truechalk.DatePickerFragment;
import com.wareshopc.app.truechalk.ImageFragment;
import com.wareshopc.app.truechalk.Photo;
import com.wareshopc.app.truechalk.PictureUtils;
import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.TimePickerFragment;
import com.wareshopc.app.truechalk.camera1.Camera1Fragment;
import com.wareshopc.app.truechalk.camera1.Camera1Activity;
import com.wareshopc.app.truechalk.camera2.Camera2Activity;
import com.wareshopc.app.truechalk.sportselector.basketball.db.BasketballDatabaseHandler;

import java.util.Date;
import java.util.UUID;

public class BasketballChalkFragment extends Fragment {
    public static final String EXTRA_TRUECHALK_ID = "com.wareshopc.app.truechalk.chalk_id";
    private static final String TAG = "BasketballChalkFragment";
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final int REQUEST_PHOTO = 2;
    //private static final int REQUEST_CONTACT = 3;
    private static final String DIALOG_IMAGE = "image";
    private UUID mChalkId;
    private BasketballChalk mChalk;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private ImageButton mPhotoButton;
    private Button mCollectStatsButton;
    private ImageView mPhotoView;
    private Callbacks mCallbacks;
    BasketballDatabaseHandler mDb;

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onBasketballChalkUpdated(BasketballChalk basketballChalk);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public static BasketballChalkFragment newInstance(UUID chalkId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TRUECHALK_ID, chalkId);

        BasketballChalkFragment fragment = new BasketballChalkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChalkId = (UUID) getArguments().getSerializable(EXTRA_TRUECHALK_ID);
            mDb = new BasketballDatabaseHandler(getActivity());
            //mChalk = BasketballChalkLab.get(getActivity()).getChalk(mChalkId);
            mChalk = mDb.getBasketballChalk(mChalkId);
        }

        setHasOptionsMenu(true);
    }

    private void updateDateAndTime() {
        Date d = mChalk.getDate();
        CharSequence c = DateFormat.format("EEEE, MMM dd, yyyy", d);
        CharSequence t = DateFormat.format("h:mm a", d);
        mDateButton.setText(c);
        mTimeButton.setText(t);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_basketballchalk_1st_screen, parent, false);

        LinearLayout lLayout = (LinearLayout) v.findViewById(R.id.llayout);
        lLayout.setBackgroundColor(getResources().getColor(R.color.lighter_rustlike));

        getActivity().setTitle(R.string.title_basketball_chalk);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                // http://forums.bignerdranch.com/viewtopic.php?f=416&t=7919
                ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mTitleField = (EditText) v.findViewById(R.id.chalk_event_title);
        mTitleField.setText(mChalk.getEventName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mChalk.setEventName(c.toString());
                mCallbacks.onBasketballChalkUpdated(mChalk);
                getActivity().setTitle(mChalk.getEventName());
            }

            public void beforeTextChanged(CharSequence c, int start, int count,
                                          int after) {
                // This space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // This one too
            }
        });

        mDateButton = (Button) v.findViewById(R.id.chalk_event_date);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mChalk.getDate());
                dialog.setTargetFragment(BasketballChalkFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mTimeButton = (Button) v.findViewById(R.id.chalk_event_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mChalk.getDate());
                dialog.setTargetFragment(BasketballChalkFragment.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
            }
        });

        updateDateAndTime();

        mPhotoButton = (ImageButton) v.findViewById(R.id.chalk_ImageButton);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent i = new Intent(getActivity(), Camera2Activity.class);
                    startActivityForResult(i, REQUEST_PHOTO);
                }
                else {
                    Intent i = new Intent(getActivity(), Camera1Activity.class);
                    startActivityForResult(i, REQUEST_PHOTO);
                }


            }
        });
        //mPhotoButton.setVisibility(View.INVISIBLE);

        mPhotoView = (ImageView) v.findViewById(R.id.chalk_ImageView);
        mPhotoView.setImageResource(R.drawable.basketball_chalk_event_default);
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Photo p = mChalk.getPhoto();
                if (p == null)
                    return;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                String path = getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
                ImageFragment.newInstance(path).show(fm, DIALOG_IMAGE);
            }
        });

        // If camera is not available, disable camera functionality
        PackageManager pm = getActivity().getPackageManager();
        boolean hasACamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
                pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD &&
                        Camera.getNumberOfCameras() > 0);
        if (!hasACamera) {
            mPhotoButton.setEnabled(false);
        }

        mCollectStatsButton = (Button) v.findViewById(R.id.chalk_event_finishButton);
        mCollectStatsButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mDb.updateBasketballChalk(mChalk);
                    Intent i = new Intent(getActivity(), BasketballAccumulateActivity.class);
                    i.putExtra(EXTRA_TRUECHALK_ID, mChalkId);
                    startActivity(i);
                }
            });

        return v;
    }

    private void showPhoto() {
        // (Re)set the image button's image based on our photo
        Photo p = mChalk.getPhoto();
        BitmapDrawable b = null;
        if (p != null) {
            String path = getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
            b = PictureUtils.getScaledDrawable(getActivity(), path);
        }
        mPhotoView.setImageDrawable(b);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mChalk.setDate(date);
            mCallbacks.onBasketballChalkUpdated(mChalk);
            updateDateAndTime();
        } else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mChalk.setDate(date);
            mCallbacks.onBasketballChalkUpdated(mChalk);
            updateDateAndTime();
        } else if (requestCode == REQUEST_PHOTO) {
            // Create a new Photo object and attach it to the chalk event
            String filename = data.getStringExtra(Camera1Fragment.EXTRA_PHOTO_FILENAME);
            if (filename != null) {
                Photo p = new Photo(filename);
                mChalk.setPhoto(p);
                mCallbacks.onBasketballChalkUpdated(mChalk);
                showPhoto();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    mDb.updateBasketballChalk(mChalk);
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mDb.updateBasketballChalk(mChalk);
        //BasketballChalkLab.get(getActivity()).saveBasketballChalks();
    }

    @Override
    public void onStart() {
        super.onStart();
        showPhoto();
    }

    @Override
    public void onStop() {
        super.onStop();
        PictureUtils.cleanImageView(mPhotoView);
    }

}
