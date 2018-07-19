package cn.hnust.icanjiuye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tjouyang on 2017/5/21.
 *
 */
public class ImageWithText extends LinearLayout {

    private ImageView imageViewbutton;

    private  TextView   textView;

    public ImageWithText(Context context,AttributeSet attrs) {
        super(context,attrs);

        imageViewbutton = new ImageView(context, attrs);

        imageViewbutton.setPadding(0, 0, 0, 0);

        imageViewbutton.setClickable(false);

        imageViewbutton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 5.0f));

        textView = new TextView(context, attrs);

        textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);

        textView.setPadding(0, 0, 0, 0);

        textView.setClickable(false);

        textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 2.0f));

        setClickable(true);

        setFocusable(true);

//		setBackgroundResource(android.R.drawable.btn_default);

        setOrientation(LinearLayout.VERTICAL);

        addView(imageViewbutton);

        addView(textView);

    }

    public String getText(){
        return textView.getText().toString();
    }
}