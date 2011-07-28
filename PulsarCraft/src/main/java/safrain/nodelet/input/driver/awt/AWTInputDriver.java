package safrain.nodelet.input.driver.awt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import safrain.nodelet.input.InputEvent;
import safrain.nodelet.input.InputService;
import safrain.nodelet.input.driver.InputDriver;

public class AWTInputDriver implements InputDriver, MouseListener,
		MouseMotionListener {
	private InputService inputService;

	public AWTInputDriver(InputService inputService) {
		this.inputService = inputService;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		InputEvent event = inputService.newEvent();
		event.x = e.getX();
		event.y = e.getY();
		event.type = InputEvent.MOVE;
		inputService.addToQueue(event);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		InputEvent event = inputService.newEvent();
		event.x = e.getX();
		event.y = e.getY();
		event.type = InputEvent.MOVE;
		inputService.addToQueue(event);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		InputEvent event = inputService.newEvent();
		event.x = e.getX();
		event.y = e.getY();
		event.type = InputEvent.DOWN;
		inputService.addToQueue(event);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		InputEvent event = inputService.newEvent();
		event.x = e.getX();
		event.y = e.getY();
		event.type = InputEvent.UP;
		inputService.addToQueue(event);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}
}
