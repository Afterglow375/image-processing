import java.io.IOException;

public class ImageProcessing {
	
	 /** 
     * The main method that is used to apply various image effects to a .ppm file
     *
     * @param args the command line arguments. There needs to be 3 or 4 command line arguments
     *        corresponding to the input and output files, image process, and an optional number to control a particular process
     * @throws IOException When the command line arguments are invalid
     */
    public static void main(String[] args) {
		if (args.length != 3 && args.length != 4) {
		    System.out.println("Usage: java PPM (infile).ppm (outfile).ppm imageProcess numberArgument");
		    return;
		}
	
		try {
		    System.out.println("Attempting to read " + args[0]);
		    PackedImage img = PPM.read(args[0]);
		    String imgProc = args[2].toLowerCase();
		    if (imgProc.equals("grayscale"))
		    	img = grayscale(img);
		    else if (imgProc.equals("threshold")) {
		    	int level = Integer.parseInt(args[3]);
		    	if (level < 0 || 255 < level)
			    	throw new IOException("Level must be between 0 and 255 inclusive");
		    	img = threshold(img, level);
		    }
//		    else if (imgProc.equals("segmentation")) 
//	    	img = segment(img);
		    else if (imgProc.equals("boxblur"))
		    	img = Convolution.boxBlur(img);
		    else if (imgProc.equals("sobelgradient")) 
		    	img = Convolution.sobelGradient(img);
		    else if (imgProc.equals("gaussianblur")) {
			    img = Convolution.gaussianBlur(img, Float.parseFloat(args[3]));
		    }
		    else
		    	throw new IOException("Invalid image process.");
		    PPM.write(args[1], img);
		}
		catch (Exception E) {
		    System.out.println("Exception: " + E.getMessage());
		}
    }
	
    /**
     * Returns a grayscaled image.
     *
     * @param img a PackedImage file to grayscale.
     * @return The grayscaled PackedImage file.
     */
	public static PackedImage grayscale(PackedImage img) {
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		int sum;
		
		for (int r = 0; r < img.rows(); r++) {
		    for (int c = 0; c < img.cols(); c++) {
		    	sum = img.at(r, c, 0) + img.at(r, c, 1) + img.at(r, c, 2);
		    	outImg.set(r, c, 0, sum/3);
		    	outImg.set(r, c, 1, sum/3);
		    	outImg.set(r, c, 2, sum/3);
		    }
		}
		return outImg;
	}
	
    /**
     * Returns a thresholded image based on an user given level.
     *
     * @param img a PackedImage file to threshold.
     * @param level an integer which controls which pixels to blacken and which to turn white.
     * @return The thresholded PackedImage file.
     */
	public static PackedImage threshold(PackedImage img, int level) {
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		int sum;
		
		for (int r = 0; r < img.rows(); r++) {
		    for (int c = 0; c < img.cols(); c++) {
		    	sum = img.at(r, c, 0) + img.at(r, c, 1) + img.at(r, c, 2);
		    	if (sum/3 < level) { // if average of RGB values is below the level, change corresponding output image pixel to black
		    		outImg.set(r, c, 0);
		    	}
		    	else { // else change to white
		    		outImg.set(r, c, 0, 255);
			    	outImg.set(r, c, 1, 255);
			    	outImg.set(r, c, 2, 255);
		    	}
		    }
		}
		return outImg;
	}
	
//	public static PackedImage segment(PackedImage img) {
//		
//	}
	
}
