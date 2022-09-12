import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// the cd shelf is a list of cds that can be added to and removed from.
public class cd_shelf {
  // the array of cds
  public cd[] cds;
  // the number of cds in the shelf
  public int numcds;

  // the constructor of the cd shelf
  public cd_shelf(int n) {
    cds = new cd[n];
    numcds = 0;
  }

  // the method to add a cd to the shelf
  public void add(cd c) {
    cds[numcds] = c;
    numcds++;
  }

  // the method to remove a cd from the shelf
  public void remove(int i) {
    cds[i] = cds[numcds - 1];
    numcds--;
  }

  private void swap(int pIndex1, int pIndex2) {

    cd temp2 = cds[pIndex2];
    cds[pIndex2] = cds[pIndex1];
    cds[pIndex1] = temp2;
  }

  // the method to print the shelf
  public void print() {
    for (int i = 0; i < numcds; i++) {
      cds[i].print();
    }
  }

  public void addcd(String t, String a, int tr, double p, int y) {

    try {
      cd c = new cd(t, a, tr, p, y);
      add(c);
    } catch (Exception e) {

    }
  }

  // the method to search for a cd
  public int search(String t) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getTitle().equals(t) || cds[i].getArtist().equals(t)) {
        return i;
      }
    }
    return -1;
  }

  // we can also search for a cd by title, artist, tracks or price.
  // the method to search for a cd by artist
  public int searchArtist(String a) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getArtist().equals(a)) {
        return i;
      }
    }
    return -1;
  }

  // the method to search for a cd by tracksF
  public int searchTracks(int tr) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getTracks() == tr) {
        return i;
      }
    }
    return -1;
  }

  // the method to search for a cd by price
  public int searchPrice(double p) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getPrice() == p) {
        return i;
      }
    }
    return -1;
  }

  // save all the cds in the shelf to a file called cds.txt structured as follows:
  // title, artist, tracks, price on each line
  public void save() throws IOException {
    File file = new File("cds.txt");
    FileWriter fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
    for (int i = 0; i < numcds; i++) {
      bw.write(
          cds[i].getTitle()
              + ","
              + cds[i].getArtist()
              + ","
              + cds[i].getTracks()
              + ","
              + cds[i].getPrice()
              + ","
              + cds[i].getYear());

      bw.newLine();
    }
    bw.close();
  }

  // replace the cds in the shelf with the cds in the file cds.txt
  public void load() throws IOException {
    BufferedReader br = new BufferedReader(new java.io.FileReader("cds.txt"));
    String line = br.readLine();
    cds = new cd[100];
    numcds = 0;

    while (line != null) {
      String[] parts = line.split(",");
      add(
          new cd(
              parts[0],
              parts[1],
              Integer.parseInt(parts[2]),
              Double.parseDouble(parts[3]),
              Integer.parseInt(parts[4])));
      line = br.readLine();
    }
    br.close();
  }

  public int getNumcds() {
    return numcds;
  }

  // getCds returns the cds array
  public cd[] getCds() {
    return cds;
  }

  public void removeDuplicates() {
    for (int i = 0; i < numcds; i++) {
      for (int j = i + 1; j < numcds; j++) {
        if (cds[i].getTitle().equals(cds[j].getTitle())) {
          remove(j);
          j--;
        }
      }
    }

    // if a line in the file is a duplicate, it will be removed from the file
    try {
      File file = new File("cds.txt");
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      for (int i = 0; i < numcds; i++) {
        bw.write(
            cds[i].getTitle()
                + ","
                + cds[i].getArtist()
                + ","
                + cds[i].getTracks()
                + ","
                + cds[i].getPrice());
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // functions to sort the cds by title using quicksort
  public void quickSortTitle(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    String pivot = cds[low + (high - low) / 2].getTitle();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (cds[i].getTitle().compareTo(pivot) < 0) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (cds[j].getTitle().compareTo(pivot) > 0) {
        j--;
      }

      // If we have found a value in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j) quickSortTitle(low, j);
    if (i < high) quickSortTitle(i, high);
  }

  // functions to sort the cds by artist using quicksort
  public void quickSortArtist(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    String pivot = cds[low + (high - low) / 2].getArtist();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (cds[i].getArtist().compareTo(pivot) < 0) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (cds[j].getArtist().compareTo(pivot) > 0) {
        j--;
      }

      // If we have found a value in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j) quickSortArtist(low, j);
    if (i < high) quickSortArtist(i, high);
  }

  // functions to sort the cds by tracks using quicksort
  public void quickSortTracks(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = cds[low + (high - low) / 2].getTracks();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (cds[i].getTracks() < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (cds[j].getTracks() > pivot) {
        j--;
      }

      // If we have found a value in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j) quickSortTracks(low, j);
    if (i < high) quickSortTracks(i, high);
  }

  // functions to sort the cds by price as double using quicksort
  public void quickSortPrice(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    double pivot = cds[low + (high - low) / 2].getPrice();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (cds[i].getPrice() < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (cds[j].getPrice() > pivot) {
        j--;
      }

      // If we have found a value in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j) quickSortPrice(low, j);
    if (i < high) quickSortPrice(i, high);
  }

  // quick sort year
  public void quickSortYear(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = cds[low + (high - low) / 2].getYear();

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (cds[i].getYear() < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (cds[j].getYear() > pivot) {
        j--;
      }

      // If we have found a value in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        swap(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j) quickSortYear(low, j);
    if (i < high) quickSortYear(i, high);
  }

  public boolean login(String username, String password) {
    if (username != null && password != null) {

      // searches for the username and password in the json file users.json and returns true if
      // found using buffered reader
      try {
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = br.readLine()) != null) {
          if (line.contains(username) && line.contains(password)) {
            // if there is no other character in the line apart from the username and password
            // spaces, tabs and new lines then return true
            if (line.length() == (username.length() + password.length() + 2)) {
              return true;
            }
          }
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return false;
    }
    return false;
  }

  public boolean register(String username, String password) {

    // if the username is not found in the json file users.json then it is added to the file using
    // buffered writer
    try {
      BufferedReader br = new BufferedReader(new FileReader("users.txt"));
      String line;
      while ((line = br.readLine()) != null) {
        if (line.contains(username)) {
          return false;
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("users.json", true));
      bw.write(username + " " + password);
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return true;
  }
}
