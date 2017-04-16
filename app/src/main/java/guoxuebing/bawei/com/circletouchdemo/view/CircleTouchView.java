package guoxuebing.bawei.com.circletouchdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by acer on 2017/4/12.
 */
public class CircleTouchView extends View {

    //小球的半径
    private int radius = -1;

    //画笔
    private Paint paint;

    //圆心的x偏移量
    private int centerOffsetx = 0;

    //圆心的y偏移量
    private int centerOffsety = 0;

    //画笔的颜色
    private int circleColor;

    private Context context;

    private int center;
    private int innerCircle;

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setCenterOffset(int centerOffsetx,int centerOffsety) {
        this.centerOffsetx = centerOffsetx;
        this.centerOffsety = centerOffsety;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public CircleTouchView(Context context) {
        super(context);
    }

    public CircleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        //消除锯齿,AntiAlias:抗锯齿
        paint.setAntiAlias(true);
        //默认设置空心
        paint.setStyle(Paint.Style.STROKE);
        //默认画笔的颜色
        circleColor = Color.argb(205,245,2,51);
    }

    public CircleTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取圆心的坐标
        center = getWidth()/2;
        //默认半径为86，单位为px
        innerCircle = 86;
        //当半径>0时，证明有自定义半径，此时让innerCircle = radius
        if (radius > 0){
            innerCircle = dip2px(context,radius);
        }
        paint.setColor(circleColor);
        //设置宽度
        paint.setStrokeWidth(2.5f);
        //绘制圆
        canvas.drawCircle(center +centerOffsetx, center +centerOffsety, innerCircle,paint);
    }

    //px与Dp的相互转换，此方法从dp转换为px
    private int dip2px(Context context,float dpValue) {
        //获取屏幕的相对密度
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                moveY = event.getY();
                centerOffsetx = (int)(moveX - downX);
                centerOffsety = (int)(moveY - downY);
                //invalidate();
                break;
            case MotionEvent.ACTION_UP:
                centerOffsetx = (int)(moveX - downX);
                centerOffsety = (int)(moveY - downY);
                Log.e("=========",centerOffsetx+"");
                downX = moveX;
                downY = moveY;
                break;
        }*/

        float x = event.getX();
        float y = event.getY();
        centerOffsetx = (int)(x - center);
        centerOffsety = (int)(y - center);

        //获取屏幕宽高
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();

        if (x >= innerCircle && y >= innerCircle && x <= width-innerCircle
                && y <= height-innerCircle*2){
            invalidate();
        }
        return true;
    }
}
