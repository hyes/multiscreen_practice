package hyes.multiscreen.practice.multiscreenpractice.VisualizerCustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

/**
 * Created by hyes on 2015. 9. 7..
 */
public class VisualizerView2 extends View{
    public int[] mWave;
    private float[] mPoints;
    private float[] results;
    private Rect mRect = new Rect();

    private Paint mForePaint = new Paint();

    public VisualizerView2(Context context) {
        super(context);
        init();
    }

    private void init() {
        mWave = null;
        mForePaint.setStrokeWidth(1f);
        mForePaint.setAntiAlias(true);
        mForePaint.setColor(Color.rgb(0, 128, 255));
    }

    public void updateVisualizer(int[] wave) {
        Log.i("test", "visualizer-~~~~~~~~!!!!!!!!!");
        mWave = wave;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mWave == null) {
            return;
        }

        if (mPoints == null || mPoints.length < mWave.length * 4) {
            mPoints = new float[mWave.length * 4];
        }

        mRect.set(0, 0, getWidth(), getHeight());

        int maxval = 0;
        for (int i = 0; i < mWave.length - 1; i++) {
            if( maxval < Math.abs( mWave[ i ] ) )
                maxval = Math.abs( mWave[ i ] );
        }

        for (int i = 0; i < mWave.length - 1; i++) {

            mPoints[i * 4] = mRect.width() * i / (mWave.length - 1);
            mPoints[i * 4 + 1] = mRect.height() / 2
                    + (mWave[i]) * (mRect.height() / 2) / maxval;
            mPoints[i * 4 + 2] = mRect.width() * (i + 1) / (mWave.length - 1);
            mPoints[i * 4 + 3] = mRect.height() / 2
                    + (mWave[i + 1]) * (mRect.height() / 2) / maxval;
        }

        canvas.drawLines(mPoints, mForePaint);

    }
}
