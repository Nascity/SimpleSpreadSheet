package com.tripleS.window;

import javax.swing.*;
import java.awt.*;

public class VersionFrame extends Frame {
	public VersionFrame() {
		super("Version info", 300, 200);
		containerInit();
	}
	private void containerInit() {
		Container c = getContentPane();

		c.setLayout(new GridLayout(5, 1));
		c.add(new JLabel("Simple Spread Sheet"));
		c.add(new JLabel("Made by"));
		c.add(new JLabel("* 2023920029 신승리"));
		c.add(new JLabel("* 2023920063 황수진"));
	}
}
