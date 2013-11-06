/**
 * This image represents each color channel as an array of values separately,
 * concatenated together and stored (since this is Java and bytes are signed)
 * as integers. If there were unsigned chars or bytes in Java, this would be highly
 * efficient for several reasons - but this wastes even more space.
 *
 * Do note that this class <i>does not</i> check to see if the row and column are in range.
 * 
 * @author Brian Jackson
 */

public class PlanedImage {
    protected int pixels[], nr, nc, nchannels;

    /**
     * Basic constructor for an image of known size and channels
     *
     * @param rows the height of the image in pixels
     * @param cols the width of the image in pixels
     * @param channels the number of color channels in the image (1: grayscale, 3: rgb)
     */
    public PlanedImage(int rows, int cols, int channels) {
	nr = rows; 
	nc = cols;
	nchannels = channels;
	pixels = new int [nr * nc * nchannels];
    }
    
    /**
     * Gets one channel's intensity at a row and column.
     *
     * @param r the row coordinate (y value) of the pixel
     * @param c the column coordinate (x value) of the pixel
     * @param ch the channel of color desired (0: red, 1: green, 2: blue)
     * @return An intensity from 0-255
     */
    public int at(int r, int c, int ch) {
	return pixels[ch * nr * nc + r * nc + c];
    }
    
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
	pixels[ch * nr * nc + r * nc + c] = v;
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

    /**
     * Returns number of color channels stored in the image
     * 
     * @return Number of channels
     */
    public int channels() {return nchannels;}
};