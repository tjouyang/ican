package cn.hnust.icanjiuye.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import cn.hnust.icanjiuye.R;

/**
 * Created by tjouyang on 2017/5/23.
 * 实现了爱心点赞动画
 */

public class PeriscopeLayout extends RelativeLayout {
//    private final String TAG = PeriscopeLayout.class.getSimpleName();


    private int dHeight; //爱心的高度
    private int dWidth; //爱心的宽度
    private int mHeight; //自定义布局的高度
    private int mWidth;  //自定义布局的宽度

    private LayoutParams layoutParams;
    private Random random = new Random();  //用于获取随机心的随机数
    private Drawable[] drawables;  //存放初始化图片的数组

    /**
     * 是在java代码创建视图的时候被调用，如果是从xml填充的视图，就不会调用这个
     */
    public PeriscopeLayout(Context context) {
        super(context);
        init();
    }

    /**
     * 这个是在xml创建但是没有指定style的时候被调用
     */
    public PeriscopeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 这个是在xml创建但是 有指定style的时候被调用
     */
    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }


    public  Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return zoomDrawable(wrappedDrawable, 100, 100);
    }

    private Drawable getNewHeart(){
        return ContextCompat.getDrawable(getContext(), R.drawable.heart);
    }
    /**
     * 初始化布局和随机心型图片
     */
    private void init(){

        //初始化显示的图片,暂时使用3 种图片
        drawables = new Drawable[3];

        //getResources().getDrawable 过期的替代方法 ContextCompat.getDrawable(getContext(),R.drawable.heart3);
//        Drawable red = getResources().getDrawable(R.drawable.heart3);
        drawables[0] = tintDrawable(getNewHeart(), ColorStateList.valueOf(Color.RED));
        drawables[1] = tintDrawable(getNewHeart(), ColorStateList.valueOf(Color.GREEN));
        drawables[2] = tintDrawable(getNewHeart(), ColorStateList.valueOf(Color.BLUE));

        //获取图的宽高 用于后面的计算
        dHeight = drawables[0].getIntrinsicHeight();
        dWidth = drawables[0].getIntrinsicWidth();

        //定义心型图片出现的位置，底部 水平居中
        layoutParams = new LayoutParams(dWidth, dHeight);
        layoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
    }

    /**
     * 自定义布局 onMeasure 的作用
     * 获取控件的实际高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //注意!!  获取本身的宽高 需要在测量之后才有宽高
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

//    /**
//     * 通过属性动画 实现爱心图片的缩放和透明度变化的动画效果
//     * target 就是爱心图片的view
//     */
//    private AnimatorSet getEnterAnimtor(final View target){
//
//
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
//
//        AnimatorSet enter = new AnimatorSet();
//        enter.setDuration(500);
//        enter.setInterpolator(new LinearInterpolator());//线性变化
//        enter.playTogether(scaleX,scaleY);
//        enter.setTarget(target);
//
//        return enter;
//    }

    /**
     * 贝塞尔曲线的动画实现
     */

    private ValueAnimator getBezierValueAnimator(View target) {
        //初始化一个BezierEvaluator
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));

        //第一个PointF传入的是初始点的位置
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF((mWidth - dWidth) / 2, mHeight - dHeight-20), new PointF(random.nextInt(getWidth()), 0));//随机
        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 获取中间的两个点
     */
    private PointF getPointF(int scale) {

        PointF pointF = new PointF();
//        Log.e(TAG, mWidth + " " +mHeight);
        pointF.x = random.nextInt((mWidth - 50));//减去50 是为了控制 x轴活动范围,看效果 随意~~
        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些  也可以用其他方法
        pointF.y = random.nextInt((mHeight)) / scale;
        return pointF;
    }

    /**
     * 只有在回调里使用了计算的值,才能真正做到曲线运动
     */
    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {
        private View target;

        BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);

            // alpha动画
            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    /**
     * 动画结束后，remove
     */
    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            //因为不停的add 导致子view数量只增不减,所以在view动画结束后remove掉
            removeView((target));
        }
    }

    public void addFavor() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(layoutParams);

        addView(imageView);

        Animator set = getBezierValueAnimator(imageView);
        set.addListener(new AnimEndListener(imageView));
        set.start();
    }


}
