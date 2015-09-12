package love.smartalk.game2048.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import love.smartalk.game2048.R;

/**
 * 2048游戏中的小方块
 * Created by panl on 15/9/5.
 */
public class Item2048 extends View {
    /**
     * 当前方块的数值
     */
    private int number;
    /**
     * 显示的数值字符串
     */
    private String numStr;
    private Paint paint;
    private Rect square;
    /**
     * 数字的大小
     */
    private float textSize = 25 * getResources().getDisplayMetrics().density;

    public Item2048(Context context) {
        this(context, null);
    }

    public Item2048(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Item2048(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bgColor;
        switch (number) {
            case 0:
                bgColor = R.color.white;
                break;
            case 2:
                bgColor = R.color.yellow;
                break;
            case 4:
                bgColor = R.color.orange;
                break;
            case 8:
                bgColor = R.color.light_green;// #F2B179
                break;
            case 16:
                bgColor = R.color.green;
                break;
            case 32:
                bgColor = R.color.cyan;
                break;
            case 64:
                bgColor = R.color.blue;
                break;
            case 128:
                bgColor = R.color.dark_blue;
                break;
            case 256:
                bgColor = R.color.purple;
                break;
            case 512:
                bgColor = R.color.light_pink;
                break;
            case 1024:
                bgColor = R.color.pink;
                break;
            case 2048:
                bgColor = R.color.red;
                break;
            default:
                bgColor = R.color.white;
                break;
        }
        paint.setColor(getResources().getColor(bgColor));
        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        //绘制圆角矩形的背景
        canvas.drawRoundRect(rectF, 8, 8, paint);
        if (number != 0)
            drawText(canvas);

    }

    /**
     * 绘制数字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        paint.setColor(Color.WHITE);
        float x = (getWidth() - square.width()) / 2;
        float y = getHeight() / 2 + square.height() / 2;
        canvas.drawText(numStr, x, y, paint);
    }

    public void setNumber(int number) {
        this.number = number;
        numStr = number + "";
        paint.setTextSize(textSize);
        square = new Rect();
        paint.getTextBounds(numStr, 0, numStr.length(), square);
        invalidate();
    }

    public int getNumber() {
        return number;
    }

    /**
     * 为view添加缩放动画效果
     */
    public void scaleIn() {
        ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f).setDuration(200).start();
        ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f).setDuration(200).start();
    }
}
