/**
 * This convolution class contains the image processes that use a kernel:
 * box blur, gaussian blur, and sobel gradient.
 * 
 * @author Alex Tatusko
 */

public class Convolution {
	
	/**
     * Takes an image and applies a sobel gradient to it.
     *
     * @param img the input image to apply a sobel gradient
     * @return An image with a sobel gradient applied
     */
	public static PackedImage sobelGradient(PackedImage img) {
		PackedImage gxImg = new PackedImage(img.rows(), img.cols());
		PackedImage gyImg = new PackedImage(img.rows(), img.cols());
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		Kernel gx = new Kernel("sobelGradientGx");
		Kernel gy = new Kernel("sobelGradientGy");
		gxImg = convolve(img, gx);
		gyImg = convolve(img, gy);
		
		for (int r = 0; r < img.rows(); r++) {
		    for (int c = 0; c < img.cols(); c++) {
		    	outImg.set(r, c, 0, (int) Math.round(Math.sqrt(gxImg.at(r, c, 0)*gxImg.at(r, c, 0) + gyImg.at(r, c, 0)*gyImg.at(r, c, 0)) / (Math.sqrt(2))));
		    	outImg.set(r, c, 1, (int) Math.round(Math.sqrt(gxImg.at(r, c, 1)*gxImg.at(r, c, 1) + gyImg.at(r, c, 1)*gyImg.at(r, c, 1)) / (Math.sqrt(2))));
		    	outImg.set(r, c, 2, (int) Math.round(Math.sqrt(gxImg.at(r, c, 2)*gxImg.at(r, c, 2) + gyImg.at(r, c, 1)*gyImg.at(r, c, 2)) / (Math.sqrt(2))));
		    }
		}
		
		return outImg;
	}
	
	/**
     * Takes an image and applies a box blur it.
     *
     * @param img the input image to apply a box blur
     * @return A box blurred image
     */
	public static PackedImage boxBlur(PackedImage img) {
		Kernel kernel = new Kernel("boxBlur");
		return convolve(img, kernel);
	}
	
	/**
     * Takes an image and a float sigma and applies a gaussian blur to the image.
     *
     * @param img the input image to apply a gaussian blur
     * @param sigma a float which controls how blurry to make the image
     * @return A gaussian blurred image
     */
	public static PackedImage gaussianBlur(PackedImage img, float sigma) {
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		Kernel horizontalGaussian = new Kernel("gaussianBlurHorizontal", sigma);
		Kernel verticalGaussian = new Kernel("gaussianBlurVertical", sigma);
		outImg = convolve(img, horizontalGaussian);
		return convolve(outImg, verticalGaussian);
	}
	
	/**
     * Takes an image and a kernel and convolves it by computing the sum of products of each pixel
     * in the image using the kernel. It then normalizes the sum if necessary before storing it in the appropriate
     * pixel in the image to return.
     *
     * @param img the input image to convolve
     * @param kernel the convolution kernel to multiply pixels with 
     * @return A convolved image
     */
	public static PackedImage convolve(PackedImage img, Kernel kernel) {
		PackedImage outImg = new PackedImage(img.rows(), img.cols());
		int imgRowVal, imgColVal;
		double sum;
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
		    				else // add in input image's r, g, or b value otherwise
		    					sum += kernel.at(kRow, kCol) * img.at(imgRowVal, imgColVal, ch);
		    			}
			    	}
		    		outImg.set(iRow, iCol, ch, (int) Math.abs(sum)/kernel.normalizingInt());
		    	}
		    }
		}
		return outImg;
	}
	
}
