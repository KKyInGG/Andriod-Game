package fall2018.csc2017.gamecentre.slidingtiles;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

import fall2018.csc2017.gamecentre.GameManager;
import fall2018.csc2017.gamecentre.MovementController;

public class GestureDetectGridView extends GridView {
    /**
     * A final static Swipe_Min_Distance.
     */
    public static final int SWIPE_MIN_DISTANCE = 100;

    /**
     * A GestrueDetector
     */
    private GestureDetector gDetector;

    /**
     * A MovementContorller
     */
    private MovementController mController;

    /**
     * A mFlingConfimed
     */
    private boolean mFlingConfirmed = false;

    /**
     * A float about the X value when touching screen
     */
    private float mTouchX;

    /**
     * A float about the Y value when touching screen
     */
    private float mTouchY;

    /**
     * A Game Manager
     */
    private GameManager gameManager;

    /**
     * Initialize the GestureDetectGridView
     * @param context the context
     */
    public GestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * Set up the Controller.
     * @param mController
     */
    public void setmController(MovementController mController) {
        this.mController = mController;
    }

    /**
     * Initialize the the View with context.
     * @param context the context
     */
    private void init(final Context context) {
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processTapMovement(context, position, true);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }

            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    /**
     * A Setter of gameManager.
     * @param gameManager GameManager
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        mController.setGameManager(gameManager);
    }

    /**
     * A getter for Contoller
     * @return Movement Contoller
     */
    public MovementController getmController() {
        return mController;
    }
}
