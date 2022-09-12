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

  /**
   * The getYear function returns the year of the date.
   *
   * @return The year of the date
   */
  public int getYear() {
    return pYear;
  }

  public void setYear(int e) {
    pYear = e;
  }

  /**
   * The getTitle function returns the title of a book.
   *
   * @return The title of the book
   */
  // the method to get the title
  public String getTitle() {
    return title;
  }

  /**
   * The getArtist function returns the artist of a song.
   *
   * @return The artist
   */
  // the method to get the artist
  public String getArtist() {
    return artist;
  }

  /**
   * The getTracks function returns the number of tracks in the album.
   *
   * @return The number of tracks in the cd
   *     <p>/** The getTracks function returns the number of tracks in the album.
   * @return The tracks variable
   */
  /// the method to get the tracks
  public int getTracks() {
    return tracks;
  }

  /**
   * The getPrice function returns the price of the product.
   *
   * @return The price of the item
   */
  // the method to get the price
  public double getPrice() {
    return price;
  }

  /**
   * The setTitle function sets the title of the book.
   *
   * @param t Set the title of the book
   * @return The string t
   */
  // the method to set the title
  public void setTitle(String t) {
    title = t;
  }

  /**
   * The setArtist function sets the artist of a song.
   *
   * @param a Set the artist name
   * @return The artist variable
   */
  // the method to set the artist
  public void setArtist(String a) {
    artist = a;
  }

  /**
   * The setTracks function sets the number of tracks in a CD.
   *
   * @param tr Set the number of tracks in the album
   * @return The value of tracks
   */
  // the method to set the tracks
  public void setTracks(int tr) {
    tracks = tr;
  }

  /**
   * The setPrice function sets the price of the product.
   *
   * @param p Set the price of the product
   * @return Nothing
   */
  // the method to set the price
  public void setPrice(double p) {
    price = p;
  }

  /**
   * The print function prints out the title, artist, year of publication, number of tracks and
   * price.
   *
   * @return A string representation of the object
   */
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
