package org.sunger.net.ui.widget.comment;

import android.graphics.Color;
import android.os.Handler;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunger on 2015/11/12.
 */
public class NameTouchMovementMethod extends LinkMovementMethod {

    private Handler handler = new Handler();
    private List<ClickTask> tasks = new ArrayList<>();

    private void newClickTask(ClickTask currentTask) {
        removeAllTask();
        tasks.add(currentTask);
        handler.postDelayed(currentTask, 300);
    }

    private void removeAllTask() {
        for (ClickTask task : tasks) {
            handler.removeCallbacks(task);
        }
    }

    private int getOffsetForHorizontal(TextView widget,
                                       MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        x -= widget.getTotalPaddingLeft();
        y -= widget.getTotalPaddingTop();
        x += widget.getScrollX();
        y += widget.getScrollY();
        Layout layout = widget.getLayout();
        int line = layout.getLineForVertical(y);
        return layout.getOffsetForHorizontal(line, x);
    }

    private void setSapnBackgroundGrayEffect(ClickableSpan span, Spannable buffer) {
        buffer.setSpan(new BackgroundColorSpan(Color.GRAY),
                buffer.getSpanStart(span), buffer.getSpanEnd(span),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void resetSapnBackground(ClickableSpan span, Spannable buffer) {
        buffer.setSpan(new BackgroundColorSpan(Color.parseColor("#00000000")),
                buffer.getSpanStart(span), buffer.getSpanEnd(span),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    public boolean onTouchEvent(TextView widget, Spannable buffer,
                                MotionEvent event) {
        int offSet = getOffsetForHorizontal(widget, event);
        ClickableSpan[] spanName = buffer.getSpans(offSet, offSet, ClickableSpan.class);
        if (spanName.length == 0) {
            Selection.removeSelection(buffer);
            return Touch.onTouchEvent(widget, buffer, event);
        }
        ClickableSpan span = spanName[0];
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setSapnBackgroundGrayEffect(span, buffer);
                Selection.setSelection(buffer,
                        buffer.getSpanStart(span),
                        buffer.getSpanEnd(span));
                break;
            case MotionEvent.ACTION_UP:
                newClickTask(new ClickTask(span, widget));
                resetSapnBackground(span, buffer);
                break;
            case MotionEvent.ACTION_CANCEL:
                removeAllTask();
                resetSapnBackground(span, buffer);
                break;
        }
        return Touch.onTouchEvent(widget, buffer, event);
    }

    private class ClickTask implements Runnable {
        private ClickableSpan span;
        private View widget;

        public ClickTask(ClickableSpan span, View widget) {
            this.span = span;
            this.widget = widget;
        }

        @Override
        public void run() {
            span.onClick(widget);

        }
    }


}
