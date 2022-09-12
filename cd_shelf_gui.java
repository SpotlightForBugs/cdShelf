import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.*;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class cd_shelf_gui {
  // the cd_shelf has a gui that allows the user to add, remove, search and print
  // cds. The cds are diplayed in a table and there are
  // Buttons to add, remove, search and print cds. The user can also sort the cds
  // by price or artist.
  public static void main(String[] args) {
    // create a new cd_shelf
    cd_shelf shelf = new cd_shelf(100);
    // create a new JFrame
    JFrame frame = new JFrame("CD Shelf");
    // set the size of the frame
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {

    }

    frame.setSize(500, 650);
    // create a new JPanel
    JPanel panel = new JPanel();
    // create a new JLabel
    JLabel label = new JLabel("CD Shelf");
    // add the label to the panel
    panel.add(label);

    // create a new JToggleButton
    JToggleButton darkMode = new JToggleButton("DarkMode", true);
    panel.add(darkMode);

    ChangeListener changeListener =
        new ChangeListener() {
          public void stateChanged(ChangeEvent changeEvent) {
            AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
            ButtonModel buttonModel = abstractButton.getModel();
            boolean armed = buttonModel.isArmed();
            boolean pressed = buttonModel.isPressed();
            boolean selected = buttonModel.isSelected();
            if (!armed && !pressed && !selected) {
              System.out.println("Whitemode");
              frame.getContentPane().setBackground(Color.WHITE);
              frame.setBackground(Color.white);

              // Color class with color red

            } else {
              System.out.println("Darkmode");
              frame.getContentPane().setBackground(Color.BLACK);
              frame.setBackground(Color.black);
            }
          }
        };

    // create a new JTable
    JTable table = new JTable(100, 4);
    // remame the columns of the table
    table.getColumnModel().getColumn(0).setHeaderValue("Title");
    table.getColumnModel().getColumn(1).setHeaderValue("Artist");
    table.getColumnModel().getColumn(2).setHeaderValue("Tracks");
    table.getColumnModel().getColumn(3).setHeaderValue("Price");

    // the table is ///not/// editable
    table.setEnabled(false);

    // create a new JScrollPane
    JScrollPane scroll = new JScrollPane(table);
    // add the scroll to the panel
    panel.add(scroll);
    // create a new JButton
    JButton add = new JButton("Add");
    // add the add button to the panel
    panel.add(add);
    // create a new JButton
    JButton load = new JButton("Load from file");
    // add the load button to the panel
    panel.add(load);
    // create a new JButton
    JButton save = new JButton("Save to file");
    // add the save button to the panel
    panel.add(save);

    // create a new JButton
    JButton remove = new JButton("Remove");
    // add the remove button to the panel
    panel.add(remove);
    // create a new JButton
    JButton search = new JButton("Search");
    // add the search button to the panel
    panel.add(search);
    // create a new JButton
    JButton print = new JButton("Print");
    // add the print button to the panel
    panel.add(print);
    // create a new JButton
    JButton refresh = new JButton("Refresh");
    // add the refresh button to the panel
    panel.add(refresh);
    // create a new JButton
    JButton sortPrice = new JButton("Sort by price");
    // add the sortPrice button to the panel
    panel.add(sortPrice);
    // create a new JButton
    JButton removeDuplicates = new JButton("Remove duplicates");
    // add the removeDuplicates button to the panel
    panel.add(removeDuplicates);

    // create a new JButton
    JButton sort = new JButton("Sort by Price");
    // add the sort button to the panel
    panel.add(sort);
    // create a new JButton
    JButton sortArtist = new JButton("Sort Artist");
    // add the sortArtist button to the panel
    panel.add(sortArtist);
    // create a new JButton
    JButton sortTitle = new JButton("Sort Name");
    panel.add(sortTitle);

    // add the panel to the frame
    frame.add(panel);

    frame.setResizable(true);
    // make the frame half the size of the screen

    // make the frame visible
    frame.setVisible(true);

    // on every click of the table, it refreshes the table
    table.addMouseListener(
        new java.awt.event.MouseAdapter() {
          @Override
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    //    // if you fill a row in the table, it will add a cd to the shelf if it is not
    //    // already there
    //    table
    //        .getModel()
    //        .addTableModelListener(
    //            new javax.swing.event.TableModelListener() {
    //              @Override
    //              public void tableChanged(javax.swing.event.TableModelEvent evt) {
    //                if (!table.isEditing()) {
    //
    //                  int row = table.getSelectedRow();
    //                  int column = table.getSelectedColumn();
    //                  for (int i = 0; i < table.getRowCount(); i++) {
    //                    for (int j = 0; j < table.getColumnCount(); j++) {
    //
    //                      String value = table.getValueAt(i, j).toString();
    //                      if (value.trim().length() == 0) {
    //                        System.out.println("not filled");
    //                      }
    //                    }
    //
    //                    if (shelf.search(table.getValueAt(row, 0).toString()) == -1) {
    //                      shelf.add(
    //                          new cd(
    //                              table.getValueAt(row, 0).toString(),
    //                              table.getValueAt(row, 1).toString(),
    //                              Integer.parseInt(table.getValueAt(row, 2).toString()),
    //                              Double.parseDouble(table.getValueAt(row, 3).toString())));
    //                    }
    //                  }
    //
    //                } // end of if
    //                else {
    //
    //                } // end of if-else
    //              }
    //            });

    // button.actionPerformed
    add.addActionListener(
        new java.awt.event.ActionListener() {

          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // create a new cd
            String title = JOptionPane.showInputDialog("Enter the title of the cd");
            String artist = JOptionPane.showInputDialog("Enter the artist of the cd");
            int tracks =
                Integer.parseInt(
                    JOptionPane.showInputDialog("Enter the number of tracks on the cd"));
            double price =
                Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the cd"));

            cd c = new cd(title, artist, tracks, price);
            // add the cd to the shelf
            shelf.add(c);
            // update the table and write all the cds to the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    // button.actionPerformed
    remove.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // remove the cd from the shelf
            int i =
                Integer.parseInt(
                    JOptionPane.showInputDialog("Enter the index of the cd you want to remove"));
            shelf.remove(i);
            // update the table
            table.setValueAt("", 0, 0);
            table.setValueAt("", 0, 1);
            table.setValueAt("", 0, 2);
            table.setValueAt("", 0, 3);
          }
        });

    sortTitle.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // remove the cd from the shelf

            shelf.sortTitle();
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    // button.actionPerformed
    search.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // search for a cd by title
            int result = shelf.search(JOptionPane.showInputDialog("Enter your search query"));
            // if the cd is found, display the cd
            if (result != -1) {
              JOptionPane.showMessageDialog(
                  null,
                  shelf.getCds()[result].getTitle()
                      + " "
                      + shelf.getCds()[result].getArtist()
                      + " "
                      + shelf.getCds()[result].getTracks()
                      + " "
                      + shelf.getCds()[result].getPrice());
            }
          }
        });

    // button.actionPerformed
    print.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // print the shelf
            shelf.print();
          }
        });

    // button.actionPerformed
    sort.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf
            shelf.sort();
            // update the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    // button.actionPerformed
    sortArtist.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf by artist
            shelf.sortArtist();
            // update the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    // button.actionPerformed
    load.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // load the shelf from a file
            try {
              shelf.load();
              shelf.print();
            } catch (IOException e) {

              e.printStackTrace();
            }
          }
        });

    // button.actionPerformed
    save.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // save the shelf to a file
            try {
              shelf.save();
              // print the shelf
              shelf.print();
            } catch (IOException e) {

              e.printStackTrace();
            }
          }
        });

    refresh.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    removeDuplicates.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // remove duplicates
            shelf.removeDuplicates();
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
            }
          }
        });

    darkMode.addChangeListener(changeListener);
    frame.add(darkMode, BorderLayout.NORTH);

    frame.setVisible(true);
  }
}
