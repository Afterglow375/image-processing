/**
 * This class is an implementation of a convolution kernel, used by various image processes.
 * 
 * @author Alex Tatusko
 */

public class Kernel {
	protected int r;
	protected int c;
	protected int normalizingInt;
	protected double pix[];
	private final static double BOX_BLUR[] = {1,1,1,1,1,1,1,1,1};
	private final static double SOBEL_GRADIENT_GX[] = {1,0,-1,2,0,-2,1,0,-1};
	private final static double SOBEL_GRADIENT_GY[] = {1,2,1,0,0,0,-1,-2,-1};
	
	/**
     * Constructor for a box blur or sobel gradient convolution kernel
     *
     * @param kernelType a string that indicates what type of kernel to create (box blur or sobel gradient)
     */
	public Kernel(String kernelType) {
		this.r = 3;
		this.c = 3;
		if (kernelType.equals("boxBlur")) {
			this.normalizingInt = 9;
			this.pix = BOX_BLUR;
		}
		else if (kernelType.equals("sobelGradientGx")) {
			this.normalizingInt = 4;
			this.pix = SOBEL_GRADIENT_GX;
		}
		else if (kernelType.equals("sobelGradientGy")) {
			this.normalizingInt = 4;
			this.pix = SOBEL_GRADIENT_GY;
		}
	}
	
	/**
     * Constructor for a gaussian blur convolution kernel
     *
     * @param kernelType a string that indicates what type of kernel to create
     * @param sigma an integer sigma necessary to construct the gaussian blur kernel
     */
	public Kernel(String kernelType, float sigma) {
		this.normalizingInt = 1;
		int dimension = (int) Math.ceil(6*sigma);
		if (dimension % 1 == 0)
			dimension++; // Ensuring an odd dimensioned kernel
		if (kernelType.equals("gaussianBlurHorizontal")) {
			this.r = 1;
			this.c = dimension;
		}
		else if (kernelType.equals("gaussianBlurVertical")) {
			this.r = dimension;
			this.c = 1;
		}
		
		this.pix = new double[dimension];
		int x;
		for (int i = 0; i < dimension; i++) {
			x = -(dimension-1)/2 + i;
			this.pix[i] = (1/Math.sqrt((double) (2*Math.PI*sigma*sigma)) * Math.exp((double) (-((x*x)/(2*sigma*sigma))))); // gaussian function
		}
	}
	
	/**
     * Gets the integer value at a specified row and column in a kernel
     *
     * @param r the row coordinate (y value) of the kernel
     * @param c the column coordinate (x value) of the kernel
     * @return The value of the kernel at the specified row and column
     */
    public double at(int r, int c) {
    	return pix[r * this.c + c];
    }
    
    /**
     * Returns the normalizing integer that divides into the sum when applying the kernel to ensure a [0, 255] value
     * 
     * @return Normalizing integer
     */
    public int normalizingInt() {return this.normalizingInt;}
    
    /**
     * Returns height of the kernel
     * 
     * @return Number of rows in the kernel
     */
    public int rows() {return this.r;}
    
    /**
     * Returns width of the kernel
     * 
     * @return Number of columns in the kernel
     */
    public int cols() {return this.c;}
}
