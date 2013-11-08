
public class Convolution {
	
	
	public static PackedImage sobelGradient(PackedImage img) {
		PackedImage gxImg = new PackedImage(img.rows(), img.cols());
		PackedImage gyImg = new PackedImage(img.rows(), img.cols());
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		Kernel gx = new Kernel("sobelGradientGx");
		Kernel gy = new Kernel("sobelGradientGy");
		gxImg = convolve(img, gx);
		gyImg = convolve(img, gy);
		double sum;
		
		for (int r = 0; r < img.rows(); r++) {
		    for (int c = 0; c < img.cols(); c++) {
//		    	System.out.println(Math.sqrt(gxImg.at(r, c, 0)*gxImg.at(r, c, 0) + gyImg.at(r, c, 0)*gyImg.at(r, c, 0)) / (4*Math.sqrt(2)));
//		    	System.out.println(Math.sqrt(gxImg.at(r, c, 1)*gxImg.at(r, c, 1) + gyImg.at(r, c, 1)*gyImg.at(r, c, 1)) / (4*Math.sqrt(2)));
//		    	System.out.println(Math.sqrt(gxImg.at(r, c, 2)*gxImg.at(r, c, 2) + gyImg.at(r, c, 2)*gyImg.at(r, c, 2)) / (4*Math.sqrt(2)));
		    	
//		    	sum = (Math.sqrt(gxImg.at(r, c, 0)*gxImg.at(r, c, 0) + gyImg.at(r, c, 0)*gyImg.at(r, c, 0)) / (Math.sqrt(2))) +
//		    	(Math.sqrt(gxImg.at(r, c, 1)*gxImg.at(r, c, 1) + gyImg.at(r, c, 1)*gyImg.at(r, c, 1)) / (Math.sqrt(2))) +
//		    	(Math.sqrt(gxImg.at(r, c, 2)*gxImg.at(r, c, 2) + gyImg.at(r, c, 2)*gyImg.at(r, c, 2)) / (Math.sqrt(2)));
//		    	outImg.set(r, c, 0, (int) Math.round(sum/3));
//		    	outImg.set(r, c, 1, (int) Math.round(sum/3));
//		    	outImg.set(r, c, 2, (int) Math.round(sum/3));
		    	
		    	outImg.set(r, c, 0, (int) Math.round(Math.sqrt(gxImg.at(r, c, 0)*gxImg.at(r, c, 0) + gyImg.at(r, c, 0)*gyImg.at(r, c, 0)) / (Math.sqrt(2))));
		    	outImg.set(r, c, 1, (int) Math.round(Math.sqrt(gxImg.at(r, c, 1)*gxImg.at(r, c, 1) + gyImg.at(r, c, 1)*gyImg.at(r, c, 1)) / (Math.sqrt(2))));
		    	outImg.set(r, c, 2, (int) Math.round(Math.sqrt(gxImg.at(r, c, 2)*gxImg.at(r, c, 2) + gyImg.at(r, c, 1)*gyImg.at(r, c, 2)) / (Math.sqrt(2))));
		    }
		}
		
		return outImg;
	}
	
	public static PackedImage boxBlur(PackedImage img) {
		Kernel kernel = new Kernel("boxBlur");
		//return img;
		return convolve(img, kernel);
	}
	
//	public static PackedImage gaussianBlur(PackedImage img, int sigma) {
//		
//	}
//	
	public static PackedImage convolve(PackedImage img, Kernel kernel) {
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		int value, imgRowVal, imgColVal, sum;
		int kernelRowConstant = (kernel.rows()-1)/2;
		int kernelColConstant = (kernel.cols()-1)/2;

		for (int iRow = 0; iRow < img.rows(); iRow++) { // for each input image row
		    for (int iCol = 0; iCol < img.cols(); iCol++) { // for each input image col
		    	for (int ch = 0; ch < 3; ch++) { // for each channel
		    		sum = 0;
		    		for (int kRow = 0; kRow < kernel.rows(); kRow++) { // for each kernel row
		    			for (int kCol = 0; kCol < kernel.cols(); kCol++) { // for each kernel col
		    				imgRowVal = kRow+iRow-kernelRowConstant;
		    				imgColVal = kCol+iCol-kernelColConstant;
		    				if (imgRowVal < 0 || imgRowVal > img.rows()-1 || imgColVal < 0 || imgColVal > img.rows()-1) // consider out of range pixels black
		    					sum += 0; 
		    				else { // add in input image's r, g, or b value otherwise
		    					sum += kernel.at(kRow, kCol) * img.at(imgRowVal, imgColVal, ch);
		    				}
		    			}
			    	}
		    		value = sum/(kernel.rows()*kernel.cols());
		    		//System.out.println(Math.abs(sum)/4);
		    		outImg.set(iRow, iCol, ch, Math.abs(sum)/4);
		    	}
		    }
		}
		return outImg;
	}
	
}
