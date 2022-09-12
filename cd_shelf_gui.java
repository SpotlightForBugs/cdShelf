import java.awt.Color;
import java.io.IOException;


import javax.swing.*;
import javax.swing.AbstractButton;


import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;


import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;


import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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
    // try {
    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    // } catch (Exception e) {

    // }

    frame.setSize(500, 650);
    // create a new JPanel
    JPanel panel = new JPanel();
    // create a new JLabel
    JLabel label = new JLabel("CD Shelf");
    // add the label to the panel
    panel.add(label);

    Color color = new Color(255, 239, 213);
    frame.getContentPane().setBackground(color);

    // JToggleBUtton to toggle dark mode
    JToggleButton darkMode = new JToggleButton("Dark Mode");
    panel.add(darkMode);

    // set the default state of the toggle button to be not selected
    darkMode.setSelected(false);

    // Change the panel background color when the toggle button is selected
    darkMode.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        AbstractButton abstractButton = (AbstractButton) e.getSource();
        ButtonModel buttonModel = abstractButton.getModel();

        boolean selected = buttonModel.isSelected();
        if (selected) {
          panel.setBackground(Color.BLACK);
          label.setForeground(Color.WHITE);
        } else {
          panel.setBackground(Color.WHITE);
          label.setForeground(Color.BLACK);
        }
      }
    });

    // create a new JTable
    JTable table = new JTable(100, 5);
    // remame the columns of the table
    table.getColumnModel().getColumn(0).setHeaderValue("Title");
    table.getColumnModel().getColumn(1).setHeaderValue("Artist");
    table.getColumnModel().getColumn(2).setHeaderValue("Tracks");
    table.getColumnModel().getColumn(3).setHeaderValue("Price");
    table.getColumnModel().getColumn(4).setHeaderValue("Year");

    // the table is ///not/// editable
    table.setEnabled(true);

    // create a new JScrollPane
    JScrollPane scroll = new JScrollPane(table);
    // add the scroll to the panel
    panel.add(scroll);
    // create a new JButton
    JButton add = new JButton("Add");
    // add the add button to the panel
    panel.add(add);
    // create a new JButton
    JButton addFromTable = new JButton("Add from table");
    // add the addFromTable button to the panel
    panel.add(addFromTable);
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
    JButton sortArtist = new JButton("Sort Artist");
    // add the sortArtist button to the panel
    panel.add(sortArtist);
    // create a new JButton
    JButton sortTitle = new JButton("Sort Name");
    panel.add(sortTitle);

    JButton sortYear = new JButton("Sort Year");
    panel.add(sortYear);

    // add the panel to the frame
    frame.add(panel);

    frame.setResizable(true);
    // make the frame half the size of the screen
    frame.setLocationRelativeTo(null);

    

    
    


    // make the frame visible
    frame.setVisible(true);






    //ask if the user returns or is new
    String[] options = new String[] { "New", "Returning" };
    int response = JOptionPane.showOptionDialog(null, "Are you new or returning?", "New or Returning",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    //if the user is new, ask for their name and create a new account
    if (response == 0) {
      String name = JOptionPane.showInputDialog("What is your name?");
      String password = JOptionPane.showInputDialog("What is your password?");
      String password2 = JOptionPane.showInputDialog("Please re-enter your password");
      if (password.equals(password2)) {
        shelf.register(name, password);
        JOptionPane.showMessageDialog(null, "Account created");
      } else {
        JOptionPane.showMessageDialog(null, "Passwords do not match");


      }
    }
    //if the user is returning, ask for their name and password
    if (response == 1) {
      String name = JOptionPane.showInputDialog("What is your name?");
      String password = JOptionPane.showInputDialog("What is your password?");
      //if the name and password match, allow the user to access the shelf
      if (shelf.login(name, password)) {
        JOptionPane.showMessageDialog(null, "Welcome back " + name);
      } else {
        JOptionPane.showMessageDialog(null, "Incorrect name or password");
        //when the message is closed, the program will exit
        System.exit(0);
      }
    }



    //if one of the above messages is closed, close the program
    if (response == -1) {
      System.exit(0);
    }

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
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });



    


    //after an user has finished editing a column, it will update the shelf
    table.getModel().addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (columnName.equals("Title")) {
          shelf.getCds()[row].setTitle((String) data);
        }
        if (columnName.equals("Artist")) {
          shelf.getCds()[row].setArtist((String) data);
        }
        if (columnName.equals("Tracks")) {
          shelf.getCds()[row].setTracks((int) data);
        }
        if (columnName.equals("Price")) {
          shelf.getCds()[row].setPrice((double) data);
        }
        if (columnName.equals("Year")) {
          shelf.getCds()[row].setYear((int) data);
        }
      }
    });







    add.addActionListener(
        new java.awt.event.ActionListener() {

          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // create a new cd
            String title = JOptionPane.showInputDialog("Enter the title of the cd");
            String artist = JOptionPane.showInputDialog("Enter the artist of the cd");
            int tracks = Integer.parseInt(
                JOptionPane.showInputDialog("Enter the number of tracks on the cd"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the cd"));
            int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year of publication of the cd"));

            cd c = new cd(title, artist, tracks, price, year);
            // add the cd to the shelf
            shelf.add(c);
            // update the table and write all the cds to the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });

    // button.actionPerformed
    remove.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // remove the cd from the shelf
            int i = Integer.parseInt(
                JOptionPane.showInputDialog("Enter the index of the cd you want to remove"));
            shelf.remove(i);
            // update the table
            table.setValueAt("", 0, 0);
            table.setValueAt("", 0, 1);
            table.setValueAt("", 0, 2);
            table.setValueAt("", 0, 3);
            table.setValueAt("", 0, 4);
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
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
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
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });

    // map the buttons to quicksort to the functions
    sortArtist.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf by title
            shelf.quickSortArtist(0, shelf.getNumcds() - 1);
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });
    // map the buttons to quicksort to the functions
    sortTitle.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf by title
            shelf.quickSortTitle(0, shelf.getNumcds() - 1);
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });
    // map the buttons to quicksort to the functions
    sortPrice.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf by title
            shelf.quickSortPrice(0, shelf.getNumcds() - 1);
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);

            }
          }
        });

    // map the buttons to quicksort to the functions
    sortYear.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            // sort the shelf by title
            shelf.quickSortYear(0, shelf.getNumcds() - 1);
            // refresh the table
            for (int i = 0; i < shelf.getNumcds(); i++) {
              table.setValueAt(shelf.getCds()[i].getTitle(), i, 0);
              table.setValueAt(shelf.getCds()[i].getArtist(), i, 1);
              table.setValueAt(shelf.getCds()[i].getTracks(), i, 2);
              table.setValueAt(shelf.getCds()[i].getPrice(), i, 3);
              table.setValueAt(shelf.getCds()[i].getYear(), i, 4);
            }
          }
        });

  }
}
