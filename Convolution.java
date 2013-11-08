
public class Convolution {
	
	
//	public static PackedImage sobelGradient(PackedImage img) {
//		Math.sqrt(5);
//	}
	
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
		    				else { // consider out of range pixels black
		    					sum += kernel.at(kRow, kCol) * img.at(imgRowVal, imgColVal, ch);
		    				}
		    			}
			    	}
		    		value = sum/kernel.size();
		    		outImg.set(iRow, iCol, ch, value);
		    	}
		    }
		}
		
		return outImg;
	}
	
}
