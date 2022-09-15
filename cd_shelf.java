import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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

  /**
   * The add function adds a cd to the array of cds.
   *
   * @param c Pass the cd object to the add function
   * @return Nothing
   */
  // the method to add a cd to the shelf
  public void add(cd c) {
    cds[numcds] = c;
    numcds++;
  }

  /**
   * The remove function removes the CD at index i from the array.
   *
   * @param i Specify which element to remove
   * @return The removed object
   */
  // the method to remove a cd from the shelf
  public cd remove(int i) {
    cd c = cds[i];
    cds[i] = cds[numcds - 1];
    cds[numcds - 1] = null;
    numcds--;
    return c;
  }

  /**
   * The swap function swaps the values of two elements in an array.
   *
   * @param pIndex1 Store the index of the first element in the array
   * @param int Specify the index of the array to be sorted
   * @return Nothing
   */
  private void swap(int pIndex1, int pIndex2) {

    cd temp2 = cds[pIndex2];
    cds[pIndex2] = cds[pIndex1];
    cds[pIndex1] = temp2;
  }

  /**
   * The print function prints out the contents of the cds array.
   *
   * @return The number of cds in the array
   */
  // the method to print the shelf
  public void print() {
    for (int i = 0; i < numcds; i++) {
      cds[i].print();
    }
  }

  /**
   * The addcd function adds a cd to the list of cds.
   *
   * @param t Store the title of the cd
   * @param String Store the title of the cd
   * @param int Specify the number of cds to be added
   * @param double Represent the price of a cd
   * @param int Specify the number of tracks on a cd
   * @return Nothing
   */
  public void addcd(String t, String a, int tr, double p, int y) {

    try {
      cd c = new cd(t, a, tr, p, y);
      add(c);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  /**
   * The search function searches for a CD in the array of CDs.
   *
   * @param t Search for a title or artist
   * @return The index of the cd in the array if it is found, or - 1 otherwise
   */
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

  /**
   * The searchArtist function searches through the cds array for a specific artist.
   *
   * @param a Search for the artist in the array of cd objects
   * @return The index of the first occurrence of a given artist in the array
   */
  // the method to search for a cd by artist
  public int searchArtist(String a) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getArtist().equals(a)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * The searchTracks function searches through the cds array for a CD with the given track number.
   *
   * @param tr Search for a track in the cds array
   * @return - 1 if the track is not found
   */
  // the method to search for a cd by tracksF
  public int searchTracks(int tr) {
    for (int i = 0; i < numcds; i++) {
      if (cds[i].getTracks() == tr) {
        return i;
      }
    }
    return -1;
  }

  /**
   * The searchPrice function searches through the array of CDs to find a CD with a given price.
   *
   * @param p Search for a price in the array
   * @return The index of the cd in cds that has a price equal to p
   */
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

  /**
   * The save function writes the contents of the cds array to a file.
   *
   * @return Nothing
   */
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

  /**
   * The load function reads in the data from a file and stores it into an array.
   *
   * @return A cd array
   */
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

  /**
   * The getNumcds function returns the number of CDs in the database.
   *
   * @return The value of the numcds variable
   */
  public int getNumcds() {
    return numcds;
  }

  /**
   * The getCds function returns the array of CDs.
   *
   * @return The array of cds
   */
  // getCds returns the cds array
  public cd[] getCds() {
    return cds;
  }

  /**
   * The removeDuplicatesFromArray function removes duplicate entries from the array of CD objects.
   *
   * @return The number of unique cds in the array
   */
  public void removeDuplicatesFromArray() {
    for (int i = 0; i < numcds; i++) {
      for (int j = i + 1; j < numcds; j++) {
        if (cds[i].equals(cds[j])) {
          remove(j);
        }
      }
    }
  }

  /**
   * The removeDuplicatesFromFile function reads in the cds.txt file and removes any duplicate
   * entries from the file.
   */
  public void removeDuplicatesFromFile() throws IOException {
    BufferedReader br = new BufferedReader(new java.io.FileReader("cds.txt"));
    String line = br.readLine();
    cds = new cd[100];
    numcds = 0;
    HashMap<String, String> map = new HashMap<String, String>();

    while (line != null) {
      String[] parts = line.split(",");
      String key = parts[0] + parts[1] + parts[2] + parts[3] + parts[4];
      if (map.containsKey(key)) {
        line = br.readLine();
        continue;
      } else {
        map.put(key, line);
      }
      line = br.readLine();
    }
    br.close();
    map.clear();
  }

  public void removeDuplicates() throws IOException {
    removeDuplicatesFromFile();
    removeDuplicatesFromArray();
  }

  /**
   * The quickSortTitle function sorts the list of CDs by title.
   *
   * @param low Store the index of the first element in a list
   * @param int Define the low index of the list
   * @return Nothing
   */
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

  /**
   * The quickSortArtist function sorts the cds array using quick sort.
   *
   * @param low Store the index of the first element in a list
   * @param int Store the index of the last element in the left list
   * @return Nothing
   */
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

  /**
   * The quickSortTracks function sorts the cds array using quick sort.
   *
   * @param low Store the index of the first element in a range, and high is used to store the index
   *     of the last element
   * @param int Store the index of the current pivot element
   * @return Nothing
   */
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

  /**
   * The quickSortPrice function sorts the cds array using quick sort.
   *
   * @param low Store the index of the first element in a list
   * @param int Keep track of the number of comparisons made
   * @return Nothing
   */
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

  /**
   * The quickSortYear function sorts the list of CDs by year.
   *
   * @param low Store the index of the first element in a list
   * @param int Store the index of the pivot element
   * @return Nothing
   */
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

  /**
   * The login function checks if the username and password are correct.
   *
   * @param username Store the username entered by the user
   * @param String Store the username and password
   * @return A boolean value
   */
  public boolean login(String username, String password) {
    // use buffered reader to read the file
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("users.txt"));
    } catch (FileNotFoundException ex) {
      System.err.println(ex);
    }
    try {
      // read the file line by line
      String line = br.readLine();
      while (line != null) {
        // split the line to get the username and password
        String[] lineSplit = line.split(",");
        // check if the username and password are correct
        if (lineSplit[0].equals(username) && lineSplit[1].equals(password)) {
          return true;
        }
        line = br.readLine();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
    return false;
  }
  // for each line in users.txt
  // if username + " " + password is found return true
  // skip next line
  // if at end of file return false

  /**
   * The register function takes in a username and password, checks if the username is already
   * taken, and writes the new user to users.txt. If successful, it returns true; otherwise it
   * returns false.
   *
   * @param username Store the username entered by the user
   * @param String Pass the username to the register function
   * @return True if the username is not found in the file
   */
  public boolean register(String username, String password) {

    // write username,password to users.txt
    // return true if successful
    // return false if username already exists
    // use buffered reader to read the file
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("users.txt"));
    } catch (FileNotFoundException ex) {
      System.err.println(ex);
    }
    try {
      // read the file line by line
      String line = br.readLine();
      while (line != null) {
        // split the line to get the username and password
        String[] lineSplit = line.split(",");
        // check if the username and password are correct
        if (lineSplit[0].equals(username)) {
          return false;
        }
        line = br.readLine();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
    // if the username is not found in the file
    // write the username and password to the file
    try {
      FileWriter fw = new FileWriter("users.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter out = new PrintWriter(bw);
      out.println(username + "," + password);
      out.close();
    } catch (IOException ex) {
      System.err.println(ex);
    }
    return true;
  }
}
