// the class of a cd object
public class cd {
  // the title of the cd
  private String title;
  // the artist of the cd
  private String artist;
  // the number of tracks on the cd
  private int tracks;
  // the price of the cd
  private double price;
  // the year the cd was released
  private int pYear;
  // the constructor of the cd class
  public cd(String t, String a, int tr, double p, int e) {
    title = t;
    artist = a;
    tracks = tr;
    price = p;
    pYear = e;
  }

  public int getYear() {
    return pYear;
  }

  public void setYear(int e) {
    pYear = e;
  }
  // the method to get the title
  public String getTitle() {
    return title;
  }
  // the method to get the artist
  public String getArtist() {
    return artist;
  }
  // the method to get the tracks
  public int getTracks() {
    return tracks;
  }
  // the method to get the price
  public double getPrice() {
    return price;
  }
  // the method to set the title
  public void setTitle(String t) {
    title = t;
  }
  // the method to set the artist
  public void setArtist(String a) {
    artist = a;
  }
  // the method to set the tracks
  public void setTracks(int tr) {
    tracks = tr;
  }
  // the method to set the price
  public void setPrice(double p) {
    price = p;
  }
  // the method to print the cd info
  public void print() {
    System.out.println("The title is " + title);
    System.out.println("The artist is " + artist);
    System.out.println("The year of publication is " + pYear);
    System.out.println("The number of tracks is " + tracks);
    System.out.println("The price is " + price);
    System.out.println("________________________________________");
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public boolean equals(Object obj) {

    return super.equals(obj);
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
