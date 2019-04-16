package com.spark.biben.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class CustomeSeekbar extends LinearLayout implements SeekBar.OnSeekBarChangeListener{

    private TextView mTvCurrent;
    private TextView mTvMax;
    private TextView mTvMin;
    private SeekBar mSbProgress;
    private int shapecircle;
    private int back;
    private int max;
    private Context mcontext;

    public CustomeSeekbar(Context context){
        this(context, null);
    }

    public CustomeSeekbar(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public CustomeSeekbar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.mcontext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.skb);
        shapecircle = array.getResourceId(R.styleable.skb_shapeCircle, 0);
        back = array.getResourceId(R.styleable.skb_pback, 0);
        max = array.getInt(R.styleable.skb_pmax, 0);
        array.recycle();
        initview(context);
        initevent();
    }

    private void initview(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.custome_seekbar, this, true);
        mTvCurrent = view.findViewById(R.id.tv_current);
        mTvMax = view.findViewById(R.id.tv_max);
        mTvMin = view.findViewById(R.id.tv_min);
        mSbProgress = view.findViewById(R.id.sb_progress);
        mSbProgress.setMax(max == 0 ? 0 : max);
        if(back != 0){
            mSbProgress.setProgressDrawable(getResources().getDrawable(back));
        }
        if(shapecircle != 0){
            mSbProgress.setThumb(getResources().getDrawable(shapecircle));
        }
    }

    private void initevent(){
        mSbProgress.setOnSeekBarChangeListener(this);
    }

    /**
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        int w = mSbProgress.getMeasuredWidth()-WonderfulDpPxUtils.dip2px(mcontext, 10);
        int twidth = mTvCurrent.getMeasuredWidth() / 2;
        int left = (progress * w) / seekBar.getMax() - twidth;
        int leftmargin = left < 0 ? 0 : left;
        LayoutParams params = (LayoutParams) mTvCurrent.getLayoutParams();
        params.leftMargin = leftmargin + WonderfulDpPxUtils.dip2px(mcontext, 10);
        mTvCurrent.setLayoutParams(params);
        mTvCurrent.setText(progress + "");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
    }
}
