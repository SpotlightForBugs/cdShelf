import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

  // the method to sort the cds by price
  public void sort() {
    for (int i = 0; i < numcds - 1; i++) {
      int min = i;
      for (int j = i + 1; j < numcds; j++) {
        if (cds[j].getPrice() < cds[min].getPrice()) {
          min = j;
        }
      }
      cd temp = cds[i];
      cds[i] = cds[min];
      cds[min] = temp;
    }
  }

  public void addcd(String t, String a, int tr, double p) {

    try {
      cd c = new cd(t, a, tr, p);
      add(c);
    } catch (Exception e) {

    }
  }

  // the method to sort the cds by artist lexicographically
  public void sortArtist() {
    for (int i = 0; i < numcds - 1; i++) {
      int min = i;
      for (int j = i + 1; j < numcds; j++) {
        if (cds[j].getArtist().compareTo(cds[min].getArtist()) < 0) {
          min = j;
        }
      }
      cd temp = cds[i];
      cds[i] = cds[min];
      cds[min] = temp;
    }
  }
  // the method to sort the cds by name lexicographically
  public void sortTitle() {
    for (int i = 0; i < numcds - 1; i++) {
      int min = i;
      for (int j = i + 1; j < numcds; j++) {
        if (cds[j].getTitle().compareTo(cds[min].getTitle()) < 0) {
          min = j;
        }
      }
      cd temp = cds[i];
      cds[i] = cds[min];
      cds[min] = temp;
    }
  }

  public void bubbleSortTitle() {

    int len = cds.length;

    while (len > 1) {
      for (int i = 0; i <= len - 2; len--) {
        if (cds[len].getTitle().compareTo(cds[len + 1].getTitle()) < 0) {
          swap(len, len + 1);
        }
      }
    }
  }

  public void bubbleSortArtist() {

    int len = cds.length;

    while (len > 1) {
      for (int i = 0; i <= len - 2; len--) {
        if (cds[len].getArtist().compareTo(cds[len + 1].getArtist()) < 0) {
          swap(len, len + 1);
        }
      }
    }
  }

  public void bubbleSortTracks() {

    int len = cds.length;

    while (len > 1) {
      for (int i = 0; i <= len - 2; len--) {
        if (cds[len].getTracks() > cds[len + 1].getTracks()) {
          swap(len, len + 1);
        }
      }
    }
  }

  public void bubbleSortPrice() {

    int len = cds.length;

    while (len > 1) {
      for (int i = 0; i <= len - 2; len--) {
        if (cds[len].getPrice() > cds[len + 1].getPrice()) {
          swap(len, len + 1);
        }
      }
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
              + cds[i].getPrice());
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
      add(new cd(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3])));
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
}
