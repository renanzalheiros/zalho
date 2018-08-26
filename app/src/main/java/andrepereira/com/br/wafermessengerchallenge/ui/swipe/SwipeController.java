package andrepereira.com.br.wafermessengerchallenge.ui.swipe;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import andrepereira.com.br.wafermessengerchallenge.R;
import andrepereira.com.br.wafermessengerchallenge.ui.countries.adapter.CountriesListAdapter;

public class SwipeController extends ItemTouchHelper.SimpleCallback {

    private boolean swipeBack = false;
    private boolean fastSwipe = false;
    private BackgroundState backgroundState = BackgroundState.GONE;
    private static final int backgroundWidth = 180;
    private final RecyclerView mRecyclerView;
    private RecyclerView.ViewHolder currentItemViewHolder;

    private VelocityTracker velocityTracker;
    private Canvas canvas;

    private AfterSwipedCallback mAfterSwipedCallback;

    public SwipeController(RecyclerView recyclerView, AfterSwipedCallback afterSwipedCallback) {
        super(0, ItemTouchHelper.LEFT);
        this.mRecyclerView = recyclerView;
        velocityTracker = VelocityTracker.obtain();
        this.mAfterSwipedCallback = afterSwipedCallback;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mAfterSwipedCallback.afterSwiped(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {

        if(viewHolder != null && currentItemViewHolder != null) {
            swipeBack = false;
            backgroundState = BackgroundState.GONE;
            if(viewHolder != currentItemViewHolder) {
                View viewById = ((CountriesListAdapter.CountryViewHolder) currentItemViewHolder).itemView.findViewById(R.id.card_country);
                getDefaultUIUtil().onDraw(canvas, mRecyclerView, viewById, 0, 0, actionState, false);
            } else {
                View viewById = ((CountriesListAdapter.CountryViewHolder) viewHolder).itemView.findViewById(R.id.card_country);
                getDefaultUIUtil().onDraw(canvas, mRecyclerView, viewById, backgroundWidth, 0, actionState, false);
            }
            currentItemViewHolder = null;
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack && backgroundState != BackgroundState.SWIPED) {
            swipeBack = backgroundState != BackgroundState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View row = viewHolder.itemView;
        View viewById = ((CountriesListAdapter.CountryViewHolder) viewHolder).itemView.findViewById(R.id.card_country);

        int xAxis = row.getRight() - row.getLeft();
        setOnTouchListenerToRecyclerView(recyclerView, dX, xAxis/2);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (backgroundState != BackgroundState.GONE) {
                dX = Math.min(dX, -backgroundWidth);
                getDefaultUIUtil().onDraw(canvas, recyclerView, viewById, dX, dY, actionState, isCurrentlyActive);
            }
        }

        if (backgroundState == BackgroundState.GONE) {
            getDefaultUIUtil().onDraw(canvas, recyclerView, viewById, dX, dY, actionState, isCurrentlyActive);
        }
        currentItemViewHolder = viewHolder;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchListenerToRecyclerView(final RecyclerView recyclerView,final float dX, final int xAxis) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean releaseMove = false;
                float xVelocity = 0;
                if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    velocityTracker.addMovement(motionEvent);
                    velocityTracker.computeCurrentVelocity(1);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    releaseMove = true;
                    xVelocity = velocityTracker.getXVelocity();
                }
                boolean cancelMove = motionEvent.getAction() == MotionEvent.ACTION_CANCEL;
                fastSwipe = xVelocity < -10;
                swipeBack = (cancelMove || releaseMove) && !fastSwipe;
                if (swipeBack) {
                    if (dX < -backgroundWidth && dX > -xAxis) {
                        backgroundState = BackgroundState.VISIBLE;
                    }
                    if(dX < -xAxis) {
                        backgroundState = BackgroundState.SWIPED;
                    }
                }
                return false;
            }
        });
    }

    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
    }

}
