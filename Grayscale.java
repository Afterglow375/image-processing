
public class Grayscale {
	
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
	
}
