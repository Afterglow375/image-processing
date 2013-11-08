
public class ImageProcessing {
	
    /**
     * Returns a grayscaled image.
     *
     * @param A PackedImage file to grayscale.
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
     * @param A PackedImage file to threshold.
     * @param An integer which controls which pixels to blacken and which to turn white.
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
	
}
