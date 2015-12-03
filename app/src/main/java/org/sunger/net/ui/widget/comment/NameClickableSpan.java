package org.sunger.net.ui.widget.comment;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * 设置ClickableSpan常态字体颜色
 */
public abstract class NameClickableSpan extends ClickableSpan {
    public String getName() {
        return name;
    }

    private String name;

    public NameClickableSpan(String name) {
        this.name = name;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#214780"));
        ds.setUnderlineText(false);
    }
}
