package dependencias_hh.operator;

import java.util.BitSet;

/**
 * Class representing a bit set including a method to get the total number of bits
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
@SuppressWarnings("serial")
public class BinarySet extends BitSet {
  private int numberOfBits ;

  /**
   * Constructor
   *
   * @param numberOfBits
   */
  public BinarySet(int numberOfBits) {
    super(numberOfBits) ;
    this.numberOfBits = numberOfBits ;
  }

  /**
   * Returns the total number of bits
   * @return the number of bits of the binary set
   */
  public int getBinarySetLength() {
    return numberOfBits;
  }

  @Override
  public String toString() {
    String result = "" ;
    for (int i = 0; i < numberOfBits ; i++) {
      if (get(i)) {
        result += "1" ;
      }
      else {
        result+= "0" ;
      }
    }
    return result ;
  }
}
