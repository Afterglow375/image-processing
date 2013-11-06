/**
 * This class packs image intensities tightly in each int used to store the values, RGB8 style.
 * Note that this storage method obviates the need for three different array offsets.
 * Also note that this is inefficient for grayscale images. Nevertheless,
 * this method is very popular.
 *
 * Do note that this class <i>does not</i> check to see if the row and column are in range.
 * 
 * @author Brian Jackson
 */

public class PackedImage {
    protected int pixels[], nr, nc;

    /**
     * Basic constructor for an image of known size
     *
     * @param rows the height of the image in pixels
     * @param cols the width of the image in pixels
     */
    public PackedImage(int rows, int cols) {
		nr = rows; 
		nc = cols;
		pixels = new int [nr * nc];
    }
    
    /**
     * Gets the packed intensity values for a specific row and column
     *
     * @param r the row coordinate (y value) of the pixel
     * @param c the column coordinate (x value) of the pixel
     * @return A pixel in packed 24-bit format
     */
    public int at(int r, int c) {return pixels[r * nc + c];}

    /**
     * Gets one channel's intensity at a row and column.
     *
     * @param r the row coordinate (y value) of the pixel
     * @param c the column coordinate (x value) of the pixel
     * @param ch the channel of color desired (0: red, 1: green, 2: blue)
     * @return An intensity from 0-255
     */
    public int at(int r, int c, int ch) {
    	return (pixels[r * nc + c] >> (16 - ch * 8)) & 0xFF;
    }
    
    /**
     * Sets the packed intensity values for a specific row and column
     *
     * @param r the row coordinate (y value) of the pixel
     * @param c the column coordinate (x value) of the pixel
     * @param v the packed intensity value to store at the location
     */
    public void set(int r, int c, int v) {pixels[r * nc + c] = v;}

    /**
     * Gets one channel's intensity at a row and column.
     * Note that out-of-range intensity values produce really weird results.
     *
     * @param r the row coordinate (y value) of the pixel
     * @param c the column coordinate (x value) of the pixel
     * @param ch the channel of color desired (0: red, 1: green, 2: blue)
     * @param v the 0-255 intensity to store at the location
     */
    public void set(int r, int c, int ch, int v) {
    	pixels[r * nc + c] = (pixels[r * nc + c] & ~(0xFF << (16 - 8 * ch))) | (v << (16 - ch * 8));
    }

    /**
     * Returns height of the image
     * 
     * @return Number of rows in pixels
     */
    public int rows() {return nr;}
    
    /**
     * Returns width of the image
     * 
     * @return Number of columns in pixels
     */
    public int cols() {return nc;}
};