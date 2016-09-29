package team.unstudio.jblockly.ui.component;

import java.awt.Point;

import javax.swing.JComboBox;

import team.unstudio.jblockly.block.IInput;
import team.unstudio.jblockly.block.InputDescriber;

public class BlockComboBox extends JComboBox<String> implements IInput{

	private static final long serialVersionUID = 1L;

	@Override
	public String value() {
		return (String) getSelectedItem();
	}

	@Override
	public InputDescriber getDescriber() {
		// TODO 自动生成的方法存根
		return null;
	}

	private Point textPoint;

	@Override
	public Point getTextPoint() {
		return textPoint;
	}

	@Override
	public void setTextPoint(Point point) {
		textPoint = point;
	}
}