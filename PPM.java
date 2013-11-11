import java.io.*;
import java.util.*;

/**
 * This class contains a very <i>very</i> specific implementation of PPM.
 * Note that this uses the "portable pixmap" binary NetPBM format.
 * Essentially, a small header with a magic word "P6" is printed,
 * followed by the width and height of the image in pixels (as integer tokens),
 * then the maximum possible value for an intensity (as an integer token).
 * Following that, the RGB components are stored in a RGB8 packed format, 24 bpp.
 * Note that the comments in here follow Javadoc format.
 * Also notice that the comments for this have two stars at the very beginning...
 *
 * @author Brian Jackson, modified by Alex Tatusko
 */

public class PPM {

    /**
     * Writes a PPM file from a PackedImage object to the present working directory.
     *
     * @param filename the desired output filename
     * @param image a (color) image to format and write
     * @throws IOException When file writing fails for one reason or another
     */
    public static void write(String filename, PackedImage image) throws IOException {
	File out = new File(filename);
	FileOutputStream fos = new FileOutputStream(out);
	
	String header = "P6\n" + image.cols() + " " + image.rows() + "\n255\n";
	fos.write(header.getBytes());

	byte[] buf = new byte [image.cols() * image.rows() * 3];
	for (int r = 0; r < image.rows(); r++)
	    for (int c = 0; c < image.cols(); c++)
		for (int ch = 0; ch < 3; ch++)
		    buf[3 * (r * image.cols() + c) + ch] = (byte)image.at(r, c, ch);
	fos.write(buf);
	fos.flush();

	fos.close();
    }

    /**
     * Returns a string token read from the header of the PPM file.
     *
     * @param in the input stream pointing to the file
     * @return A string containing the token
     */
    private static String readField(FileInputStream in) throws IOException {
	String str = "";
	for (char c = (char)in.read(); !Character.isWhitespace(c) && c != '\n'; c = (char)in.read())
	    str = str.concat(String.valueOf(c));
	return str;
    }

    /**
     * Creates a PackedImage from a valid PPM file.
     * that only the binary "portable pixmap" images will work here.
     * 
     * @param filename an image filename in the present working directory
     * @return A complete packedimage with RGB color from the file
     * @throws IOException If the file is incomplete, the wrong format, or a file error occurs.
     */
    public static PackedImage read(String filename) throws IOException {
	FileInputStream in = new FileInputStream(filename);

	if (!readField(in).equals("P6")) throw new IOException("Not a P6 PPM file");
	int nc = Integer.parseInt(readField(in));
	int nr = Integer.parseInt(readField(in));
	int maxint = Integer.parseInt(readField(in));
	
	PackedImage img = new PackedImage(nr, nc);
	for (int r = 0; r < nr; r++)
	    for (int c = 0; c < nc; c++)
		for (int ch = 0; ch < 3; ch++)
		    img.set(r, c, ch, in.read());
	
	in.close();
	
	return img;
    }
}
