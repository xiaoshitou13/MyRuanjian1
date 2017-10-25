package test.bwie.com.myruanjian1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by 白玉春 on 2017/10/25.
 */

public class MyRoundView extends android.support.v7.widget.AppCompatImageView {

    private Paint mpaint;
    private static Xfermode xfermode;
    private static Bitmap bitmap;
    private RectF rect;
    public MyRoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2  = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(20);
        int width = getWidth();
        int height = getHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Canvas canvas1=null;
        BitmapDrawable drawable = (BitmapDrawable)getDrawable();
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        if (bitmap == null){
            Log.i("porterduffviewimage","bitmap==null");
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rect = new RectF(0,0,width,height);
            bitmap = Bitmap.createBitmap(width, height, config);
            canvas1 = new Canvas(bitmap);
            canvas1.drawOval(rect, paint);

        }

        mpaint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap,0,0,mpaint);



    }
}
