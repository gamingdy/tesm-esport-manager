package vue.common;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

@SuppressWarnings("serial")
public class CustomComboBox<E> extends JComboBox<E> {

	public CustomComboBox() {
		super();
		style();
	}

	public CustomComboBox(ComboBoxModel<E> model) {
		super(model);
		style();
	}

	public void style() {
		setBackground(CustomColor.BACKGROUND_MAIN);
		setForeground(CustomColor.BLANC);
		setBorder(BorderFactory.createLineBorder(CustomColor.ROSE_CONTOURS, 2));
		setUI(new CustomComboBoxUI());
		setFocusable(false);
		styleScrollBar();
	}

	private void styleScrollBar() {
		Object child = this.getAccessibleContext().getAccessibleChild(0);
		if (child instanceof BasicComboPopup) {
			BasicComboPopup popup = (BasicComboPopup) child;
			JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
			JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			verticalScrollBar.setUI(new CustomScrollBarUI());
		}
	}

	public void setActif(boolean actif) {
		if (actif) {
			setUI(new CustomComboBoxUI());
			styleScrollBar();
		} else {
			setUI(new UIDesactiver());
		}
	}
}

class UIDesactiver extends BasicComboBoxUI {

	@Override
	protected JButton createArrowButton() {
		return new JButton() {
			@Override
			public int getWidth() {
				return 0;
			}
		};
	}

	@Override
	protected ComboPopup createPopup() {
		return new BasicComboPopup(comboBox) {
			@Override
			protected JScrollPane createScroller() {
				return new JScrollPane() {
					@Override
					public int getWidth() {
						return 0;
					}
				};
			}
		};
	}
}

class CustomComboBoxUI extends BasicComboBoxUI {

	public CustomComboBoxUI() {
		super();
	}

	@Override
	protected JButton createArrowButton() {
		return new BasicArrowButton(
				javax.swing.SwingConstants.SOUTH,
				CustomColor.BACKGROUND_MAIN.brighter(), null,
				CustomColor.ROSE_CONTOURS.darker(), null);
	}
}
