package com.tripleS.window;

import com.tripleS.sheet.Sheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

enum MenuItemEnum {
    File, Help
}

public class MainFrame extends com.tripleS.window.Frame {
    final private JMenu[] menuItems = {
            new JMenu("File"), new JMenu("Help")
    };
    final private LinkedList<SimpleEntry<String, AbstractAction>> fileMenuItemNames, helpMenuItemNames;
    final private JMenuBar menuBar = new JMenuBar();
    private TableSheetSynchronizer tss;
    public MainFrame() {
        super("Simple Spread Sheet", 1500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileMenuItemNames = new LinkedList<>();
        helpMenuItemNames = new LinkedList<>();
        tss = new TableSheetSynchronizer();

        menuItemArrayInit();
        containerInit();
    }
    public void makeNewSheet() {
        tss.clearTable();
    }
    public void SaveSheet() {

    }
    private void menuItemArrayInit() {
        fileMenuItemNames.add(new SimpleEntry<>("New", new NewMenuItemHandler(this)));
        fileMenuItemNames.add(new SimpleEntry<>("Open", new OpenMenuItemHandler(this)));
        fileMenuItemNames.add(new SimpleEntry<>("Save", new SaveMenuItemHandler(this)));
        fileMenuItemNames.add(new SimpleEntry<>("Save As", new SaveAsMenuItemHandler(this)));
        fileMenuItemNames.add(new SimpleEntry<>("Exit", new ExitMenuItemHandler()));
        helpMenuItemNames.add(new SimpleEntry<>("Version", new VersionMenuItemHandler()));
    }
    private JMenuBar menuInit() {
        for (var p : fileMenuItemNames)
            menuItems[MenuItemEnum.File.ordinal()].add(p.getKey()).addActionListener(p.getValue());
        for (var p : helpMenuItemNames)
            menuItems[MenuItemEnum.Help.ordinal()].add(p.getKey()).addActionListener(p.getValue());
        for (JMenu item : menuItems)
            menuBar.add(item);
        return menuBar;
    }
    private void containerInit() {
        Container c = getContentPane();
        JTable table = new JTable(Main.TABLE_HEIGHT, Main.TABLE_WIDTH);
        JScrollPane scrollPane = new JScrollPane(table);
        JTextArea textArea = new JTextArea(2, 0);

        c.setLayout(new BorderLayout());
        c.add(textArea, BorderLayout.SOUTH);
        c.add(scrollPane, BorderLayout.CENTER);
        table.setColumnSelectionAllowed(true);
        tss.addTable(table);

        setJMenuBar(menuInit());
    }
}

class NewMenuItemHandler extends AbstractAction {
    MainFrame mf;
    public NewMenuItemHandler(MainFrame mf) {
        this.mf = mf;
    }
    public void actionPerformed(ActionEvent e) {
        mf.makeNewSheet();
    }
}

class OpenMenuItemHandler extends AbstractAction {
    MainFrame mf;
    public OpenMenuItemHandler(MainFrame mf) {
        this.mf = mf;
    }
    public void actionPerformed(ActionEvent e) {

    }
}

class SaveMenuItemHandler extends AbstractAction {
    MainFrame mf;
    public SaveMenuItemHandler(MainFrame mf) {
        this.mf = mf;
    }
    public void actionPerformed(ActionEvent e) {

    }
}

class SaveAsMenuItemHandler extends AbstractAction {
    MainFrame mf;
    public SaveAsMenuItemHandler(MainFrame mf) {
        this.mf = mf;
    }
    public void actionPerformed(ActionEvent e) {

    }
}

class ExitMenuItemHandler extends AbstractAction {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

class VersionMenuItemHandler extends AbstractAction {
    public void actionPerformed(ActionEvent e) {
        new VersionFrame().setVisible();
    }
}