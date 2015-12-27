package com.wareshopc.app.truechalk.sportselector.basketball;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.wareshopc.app.truechalk.DatePickerFragment;
import com.wareshopc.app.truechalk.ImageFragment;
import com.wareshopc.app.truechalk.Photo;
import com.wareshopc.app.truechalk.PictureUtils;
import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.TimePickerFragment;
import com.wareshopc.app.truechalk.TrueChalkLab;
import com.wareshopc.app.truechalk.camera1.Camera1Fragment;
import com.wareshopc.app.truechalk.camera1.Camera1Activity;
import com.wareshopc.app.truechalk.camera2.Camera2Activity;

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
    private static final int REQUEST_CONTACT = 3;
    private static final String DIALOG_IMAGE = "image";
    private BasketballChalk mBasketballChalk;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Button mSuspectButton;
    private Callbacks mCallbacks;

    public static BasketballChalkFragment newInstance(UUID chalkId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TRUECHALK_ID, chalkId);

        BasketballChalkFragment fragment = new BasketballChalkFragment();
        fragment.setArguments(args);
        return fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID chalkId = (UUID) getArguments().getSerializable(EXTRA_TRUECHALK_ID);
        mBasketballChalk = TrueChalkLab.get(getActivity()).getChalk(chalkId);
        setHasOptionsMenu(true);
    }

    private void updateDateAndTime() {
        Date d = mBasketballChalk.getDate();
        CharSequence c = DateFormat.format("EEEE, MMM dd, yyyy", d);
        CharSequence t = DateFormat.format("h:mm a", d);
        mDateButton.setText(c);
        mTimeButton.setText(t);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chalk, parent, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                // http://forums.bignerdranch.com/viewtopic.php?f=416&t=7919
                ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mTitleField = (EditText) v.findViewById(R.id.chalk_event_title);
        mTitleField.setText(mBasketballChalk.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before,
                                      int count) {
                mBasketballChalk.setTitle(c.toString());
                mCallbacks.onChalkUpdated(mBasketballChalk);
                getActivity().setTitle(mBasketballChalk.getTitle());
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
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mBasketballChalk.getDate());
                dialog.setTargetFragment(BasketballChalkFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mTimeButton = (Button) v.findViewById(R.id.chalk_event_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mBasketballChalk.getDate());
                dialog.setTargetFragment(BasketballChalkFragment.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
            }
        });

        updateDateAndTime();

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.chalk_completed);
        mSolvedCheckBox.setChecked(mBasketballChalk.isSolved());
        mSolvedCheckBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mBasketballChalk.setSolved(isChecked);
                        mCallbacks.onChalkUpdated(mBasketballChalk);
                    }
                });

        mPhotoButton = (ImageButton) v.findViewById(R.id.chalk_imageButton);
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

        mPhotoView = (ImageView) v.findViewById(R.id.chalk_ImageView);
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Photo p = mBasketballChalk.getPhoto();
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

        Button reportButton = (Button) v.findViewById(R.id.chalk_event_reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getChalkEventReport());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.chalk_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });

        mSuspectButton = (Button) v.findViewById(R.id.chalk_eventButton);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
            }
        });

        if (mBasketballChalk.getEvent() != null) {
            mSuspectButton.setText(mBasketballChalk.getEvent());
        }

        return v;
    }

    private void showPhoto() {
        // (Re)set the image button's image based on our photo
        Photo p = mBasketballChalk.getPhoto();
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
            mBasketballChalk.setDate(date);
            mCallbacks.onChalkUpdated(mBasketballChalk);
            updateDateAndTime();
        } else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mBasketballChalk.setDate(date);
            mCallbacks.onChalkUpdated(mBasketballChalk);
            updateDateAndTime();
        } else if (requestCode == REQUEST_PHOTO) {
            // Create a new Photo object and attach it to the chalk event
            String filename = data.getStringExtra(Camera1Fragment.EXTRA_PHOTO_FILENAME);
            if (filename != null) {
                Photo p = new Photo(filename);
                mBasketballChalk.setPhoto(p);
                mCallbacks.onChalkUpdated(mBasketballChalk);
                showPhoto();
            }
        } else if (requestCode == REQUEST_CONTACT) {
            Uri contactUri = data.getData();
            // Specify which fields you want your query to return
            // values for.
            String[] queryFields = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            // Perform your query - the contactUri is like a "where"
            // clause here
            Cursor c = getActivity().getContentResolver()
                    .query(contactUri, queryFields, null, null, null);
            // Double-check that you actually got results
            if (c.getCount() == 0) {
                c.close();
                return;
            }
            // Pull out the first column of the first row of data -
            // that is your suspect's name.
            c.moveToFirst();
            String suspect = c.getString(0);
            mBasketballChalk.setSuspect(suspect);
            mCallbacks.onChalkUpdated(mBasketballChalk);
            mSuspectButton.setText(suspect);
            c.close();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
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
        TrueChalkLab.get(getActivity()).saveTrueChalks();
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

    private String getChalkEventReport() {
        String solvedString = null;
        if (mBasketballChalk.isSolved()) {
            solvedString = getString(R.string.chalk_report_solved);
        } else {
            solvedString = getString(R.string.chalk_report_unsolved);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mBasketballChalk.getDate()).toString();

        String suspect = mBasketballChalk.getEvent();
        if (suspect == null) {
            suspect = getString(R.string.chalk_report_no_suspect);
        } else {
            suspect = getString(R.string.chalk_report_event, suspect);
        }
        String report = getString(R.string.chalk_report,
                mBasketballChalk.getTitle(), dateString, solvedString, suspect);
        return report;
    }

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onChalkUpdated(BasketballChalk basketballChalk);
    }
}
