package com.kplo.scheduler.decorators;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.kplo.scheduler.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Decorate several days with a dot
 */
public class EventDecorator implements DayViewDecorator {

    private int color;
    private CalendarDay date;

    public EventDecorator(int color, CalendarDay date, Activity context) {
        this.color = color;
        this.date = date;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomMultipleDotSpan(5f, color)); // 날자밑에 점
    }
}