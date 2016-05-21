package cn.edu.uestc.task2;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WuQishan on 2016/5/21.
 */
public class DrawView extends View {
    public static final int STATE_ORIGINAL = 0;
    public static final int STATE_DIGING = 1;
    public static final int STATE_STOP = 2;
    private int textColor = 0XFFFFFFFF;
    private Context context;
    private long currentTime = (long) 10000000;
    private long totalTime = 12 * 60 * 60 * 1000;
    float angle = currentTime * 360 / totalTime;
    private int borderColor = 0xFF414550;
    private int circleColor = borderColor;
    private int numberColor = 0xFF6B6E76;
    private int fgColor = 0xFFFD4760;
    float angleMi = currentTime % (3600 * 1000) * 360 / 1000 / 60;
    int currentState=STATE_DIGING;
    public  DrawView (Context context, AttributeSet set)
    {
        super(context, set);
    }

    public DrawView(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);
        int width=canvas.getWidth();
        int height=canvas.getHeight()-48;
        float dx=width/2;
        float dy=height/2;
        float R=width/5*2;
        canvas.translate(dx,dy-300);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF2D313D);

        canvas.drawCircle(0,0,R,paint);

        if (currentState == STATE_DIGING) {
           paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        }
        paint.setColor(0xFFFF4760);
        canvas.drawCircle(0, 0, R/2-20, paint);



        //绘制文字
        Paint paintIn1 = new Paint();
        paintIn1.setStyle(Paint.Style.FILL);
        paintIn1.setColor(textColor);
        paintIn1.setTextSize(100);

        paintIn1.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("挖矿", 0,-(paintIn1.descent() + paintIn1.ascent()) / 2, paintIn1);


        //画时间以及线条
        Paint textPaint = new Paint();
        textPaint.setColor(numberColor);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(2);
        canvas.save();
        canvas.rotate(30);
        for (int i = 1; i < 13; i++) {

            int y = -width * 15 / 50;

            canvas.drawLine(0, y-40, 0, y -70, textPaint);
            canvas.drawText("" + i, 0, y, textPaint);
            canvas.rotate(30);
        }
        canvas.restore();


        //圈圈，和转动圈
        canvas.save();
        Paint paintIn2 = new Paint();
        paintIn2.setColor(0xFFFF4760);
        paintIn2.setStyle(Paint.Style.STROKE);
        paintIn2.setStrokeWidth(15);
        int d2 = width * 15 / 42;

        canvas.drawArc(new RectF(-d2, -d2, d2, d2), -90, angle, false, paintIn2);
        paintIn2.setColor(borderColor);
        canvas.drawArc(new RectF(-d2, -d2, d2, d2), angle - 90, 360 - angle, false, paintIn2);


        paintIn2.setColor(0xFFFF4760);

        canvas.rotate(angle);
        paintIn2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, -d2, 40, paintIn2);
        paintIn2.setStyle(Paint.Style.STROKE);
        paintIn2.setColor(0xFF2D313D);
        paintIn2.setStrokeWidth(15);
        canvas.drawCircle(0, -d2, 20, paintIn2);
        canvas.restore();


        //绘制秒针线
        Paint paintIn3 = new Paint();
        paintIn3.setColor(numberColor);
        paintIn3.setStrokeWidth(5);
        canvas.save();
        for (int i = 0; i < 120; i++) {

            canvas.drawLine(0, width * 100 / 380, 0, width * 100 / 430, paintIn3);
            canvas.rotate(3);
        }
        canvas.restore();
        if (currentTime > 0) {
            canvas.save();

            int an = ((int) angleMi / 3) * 3;
            canvas.rotate(an + 3);
            for (int i = 0; i < 60; i++) {
                paintIn3.setARGB(i * 255 / 60, Color.red(fgColor), Color.green(fgColor), Color.blue(fgColor));
                canvas.drawLine(0, width * 100 / 380, 0, width * 100 / 430, paintIn3);
                canvas.rotate(3);
            }
            canvas.restore();



            //绘制三角形指针
            canvas.save();
            canvas.rotate(angleMi);
            canvas.translate(0, -width * 80 / 390);

            Paint paintIn4 = new Paint();
            paintIn4.setColor(fgColor);
            paintIn4.setStyle(Paint.Style.FILL);
            Path path = new Path();
            int line = 12;
            path.moveTo(0, -line * 2);
            path.lineTo((float) (line * Math.sqrt(3)), line);
            path.lineTo(-(float) (line * Math.sqrt(3)), line);
            path.close();
            canvas.drawPath(path, paintIn4);
            canvas.restore();

        }
        }
    }








