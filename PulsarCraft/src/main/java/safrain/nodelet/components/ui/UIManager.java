package safrain.nodelet.components.ui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import safrain.nodelet.components.position.Position;
import safrain.nodelet.game.Game;
import safrain.nodelet.game.ManagerSupport;
import safrain.nodelet.input.InputEvent;
import safrain.nodelet.util.MathUtil;

public class UIManager extends ManagerSupport<UI> {

	private UI dragLock;
	private double dragStartX;
	private double dragStartY;
	private int state;
	// finger is not on the screen
	private final int IDLE = 0;
	// finger pressed the screen, but not moving
	private final int DOWN = 1;
	// finger drag on the screen
	private final int DRAG = 2;

	public void dispatch(InputEvent event) {
		if (dragLock != null) {
			doDispatch(dragLock, event, true);
			return;
		}
		List<UI> uiList = getObjects();
		int size = uiList.size();
		for (int i = 0; i < size; i++) {
			UI ui = uiList.get(i);
			if (doDispatch(ui, event, false)) {
				return;
			}
		}
	}

	private boolean doDispatch(UI ui, InputEvent event, boolean isDragLock) {
		Position absolute = Game.instance.poolService.get(Position.class);
		ui.position.getAbsolutePosition(absolute);
		double absoluteX = absolute.x;
		double absoluteY = absolute.y;
		if (!isDragLock) {
			if (!MathUtil.inBound(event.x, absoluteX + ui.width, absoluteX)
					|| !MathUtil.inBound(event.y, absoluteY + ui.width,
							absoluteY)) {
				return false;
			}
		}
		double relativeX = event.x - absolute.x;
		double relativeY = event.y - absolute.y;
		switch (event.type) {
		case InputEvent.DOWN:
			switch (state) {
			case IDLE:
				state = DOWN;
				ui.onDown(relativeX, relativeY);
				break;
			case DOWN:
			case DRAG:
			}
			break;
		case InputEvent.UP:
			switch (state) {
			case IDLE:
				break;
			case DOWN:// TAP
				if (dragLock == ui || dragLock == null) {
					state = IDLE;
					dragLock = null;
					ui.onUp(relativeX, relativeY);
					ui.onTap(relativeX, relativeY);
				}
				break;
			case DRAG:// DROP
				if (dragLock == ui) {// 如果是当前正在拖动的
					state = IDLE;
					dragLock = null;
					ui.onUp(relativeX, relativeY);
					Object dropObject = ui.onDrop(relativeX, relativeY);
					if (!dispatchDrop(absoluteX, absoluteY, dropObject)) {
						ui.onDropRejected(dropObject, relativeX, relativeY);
					}
				}
				break;
			}
			break;
		case InputEvent.MOVE:
			switch (state) {
			case IDLE:
				// confuse,how to move when idle??????
				break;
			case DOWN:// DRAG
				if (dragLock == null) {
					state = DRAG;
					dragLock = ui;
					dragStartX = relativeX;
					dragStartY = relativeY;
				}
				break;
			case DRAG:// DRAG
				ui.onDrag(relativeX, relativeY, dragStartX, dragStartY);
				break;
			}
			break;
		}
		return true;
	}

	private boolean dispatchDrop(double x, double y, Object object) {
		List<UI> uiList = getObjects();
		int size = uiList.size();
		for (int i = 0; i < size; i++) {
			UI ui = uiList.get(i);
			if (ui != dragLock) {
				Position absolute = Game.instance.poolService
						.get(Position.class);
				ui.position.getAbsolutePosition(absolute);
				double absoluteX = absolute.x;
				double absoluteY = absolute.y;
				if (MathUtil.inBound(x, absoluteX + ui.width, absoluteX)
						&& MathUtil.inBound(y, absoluteY + ui.width, absoluteY)) {
					if (ui.acceptDrop(object, x - absoluteX, y - absoluteY)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private final Comparator<UI> SORT_COMPARATOR = new Comparator<UI>() {

		@Override
		public int compare(UI o1, UI o2) {
			if (o1.layer >= o2.layer) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	@Override
	public void addObject(UI t) {
		super.addObject(t);
		sortUI();
	}

	/**
	 * 根据层级sortUI 次序
	 */
	private void sortUI() {
		Collections.sort(getObjects(), SORT_COMPARATOR);
	}

	@Override
	protected boolean isToRemove(UI t) {
		return false;
	}

	@Override
	protected void onTick(UI t) {
		t.tick();
	}

	@Override
	protected void onRemove(UI t) {

	}

}
