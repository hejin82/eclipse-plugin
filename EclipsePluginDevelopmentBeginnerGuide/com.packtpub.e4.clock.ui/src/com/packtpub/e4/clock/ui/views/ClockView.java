package com.packtpub.e4.clock.ui.views;

import java.time.LocalTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ClockView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		final Canvas clock = new Canvas(parent,SWT.NONE);
		clock.addPaintListener(this::drawClock);
		Runnable redraw = () -> {
			while (!clock.isDisposed()) {
				clock.redraw();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}
			}
		};
		new Thread(redraw, "TickTock").start();
	}
	private void drawClock(PaintEvent e) {
//		e.gc.drawArc(e.x, e.y, e.width-1, e.height-1, 0, 360);
		e.gc.drawArc(e.x, e.y, e.width-1, e.height-1, 0, 360);
		int seconds = LocalTime.now().getSecond();
		int arc = (15 - seconds) * 6 % 360;
		Color blue = e.display.getSystemColor(SWT.COLOR_BLUE);
		e.gc.setBackground(blue);
		e.gc.fillArc(e.x, e.y, e.width-1, e.height-1, arc-1, 2);
		}
	@Override
	public void setFocus() {

	}

}
