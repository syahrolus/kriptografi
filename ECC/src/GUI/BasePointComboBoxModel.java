package GUI;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import algo.Point;

public class BasePointComboBoxModel implements ComboBoxModel<Point>{
	private ArrayList<Point> points;
	private int selectedIndex = 0;
	
	public BasePointComboBoxModel(ArrayList<Point> points) {
		this.points = points;
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public Point getElementAt(int index) {
		return points.get(index);
	}

	@Override
	public int getSize() {
		return points.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		return points.get(selectedIndex);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedIndex = points.indexOf(anItem);
	}

}
