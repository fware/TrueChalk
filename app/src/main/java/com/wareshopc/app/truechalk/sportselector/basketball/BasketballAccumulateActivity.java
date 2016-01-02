package com.wareshopc.app.truechalk.sportselector.basketball;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wareshopc.app.truechalk.R;

public class BasketballAccumulateActivity extends AppCompatActivity {

    private ImageView mImageViewPlus1Arrow;
    private ImageView mImageViewPlus2Arrow;
    private ImageView mImageViewPlus3Arrow;

    private ImageView mImageViewMinus1Arrow;
    private ImageView mImageViewMinus2Arrow;
    private ImageView mImageViewMinus3Arrow;

    private TextView mPointsEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_accumulate);

        mPointsEntry = (TextView) findViewById(R.id.tv_points_entry);
        mPointsEntry.setText(Integer.toString(0));

        mImageViewPlus1Arrow = (ImageView) findViewById(R.id.imageview_plus1_arrow);
        mImageViewPlus1Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus1Arrow.setClickable(true);
        mImageViewPlus1Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus1Arrow");
            }
        });

        mImageViewMinus1Arrow = (ImageView) findViewById(R.id.imageview_minus1_arrow);
        mImageViewMinus1Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus1Arrow.setClickable(true);
        mImageViewMinus1Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus1Arrow");
            }
        });

        mImageViewPlus2Arrow = (ImageView) findViewById(R.id.imageview_plus2_arrow);
        mImageViewPlus2Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus2Arrow.setClickable(true);
        mImageViewPlus2Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus2Arrow");
            }
        });

        mImageViewMinus2Arrow = (ImageView) findViewById(R.id.imageview_minus2_arrow);
        mImageViewMinus2Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus2Arrow.setClickable(true);
        mImageViewMinus2Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus2Arrow");
            }
        });

        mImageViewPlus3Arrow = (ImageView) findViewById(R.id.imageview_plus3_arrow);
        mImageViewPlus3Arrow.setImageResource(R.drawable.grayuparrow);
        mImageViewPlus3Arrow.setClickable(true);
        mImageViewPlus3Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewPlus3Arrow");
            }
        });

        mImageViewMinus3Arrow = (ImageView) findViewById(R.id.imageview_minus3_arrow);
        mImageViewMinus3Arrow.setImageResource(R.drawable.graydownarrow);
        mImageViewMinus3Arrow.setClickable(true);
        mImageViewMinus3Arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ImageViewMinus3Arrow");
            }
        });

    }


}
