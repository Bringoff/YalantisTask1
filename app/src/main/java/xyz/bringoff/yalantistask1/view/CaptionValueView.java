package xyz.bringoff.yalantistask1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import xyz.bringoff.yalantistask1.R;

public class CaptionValueView extends LinearLayout {

    private TextView mTitleTextView;
    private TextView mValueTextView;

    private String mCaption;
    private String mValue;

    public CaptionValueView(Context context) {
        super(context);
        initViews(context);
    }

    public CaptionValueView(Context context, AttributeSet attrs) {
        super(context, attrs);

        obtainAttributes(context, attrs);

        initViews(context);
    }

    public CaptionValueView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        obtainAttributes(context, attrs);

        initViews(context);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CaptionValueView, 0, 0);
        try {
            mCaption = typedArray.getString(R.styleable.CaptionValueView_caption);
            mValue = typedArray.getString(R.styleable.CaptionValueView_value);
        } finally {
            typedArray.recycle();
        }
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.captionvalue_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTitleTextView = (TextView) findViewById(R.id.type_text_view);
        mValueTextView = (TextView) findViewById(R.id.value_text_view);

        setCaption(mCaption);
        setValue(mValue);
    }

    public void setCaption(String text) {
        mCaption = text;
        mTitleTextView.setText(mCaption);
    }

    public void setValue(String text) {
        mValue = text;
        mValueTextView.setText(mValue);
    }
}
