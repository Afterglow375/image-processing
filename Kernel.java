
public class Kernel {
	protected int r;
	protected int c;
	protected int pix[];
	private final static int BOX_BLUR[] = {1,1,1,1,1,1,1,1,1};
	private final static int SOBEL_GRADIENT_GX[] = {1,0,-1,2,0,-2,1,0,-1};
	private final static int SOBEL_GRADIENT_GY[] = {1,2,1,0,0,0,-1,-2,-1};
	
	/**
     * Constructor for a box blur or sobel gradient convolution kernel
     *
     * @param A string that indicates what type of kernel to create (box blur or sobel gradient)
     */
	public Kernel(String kernelType) {
		this.r = 3;
		this.c = 3;
		if (kernelType.equals("boxBlur")) {
			this.pix = BOX_BLUR.clone();
		}
		else if (kernelType.equals("sobelGradientGx")) {
			this.pix = SOBEL_GRADIENT_GX;
		}
		else if (kernelType.equals("sobelGradientGy")) {
			this.pix = SOBEL_GRADIENT_GY;
		}
	}
	
	/**
     * Constructor for a gaussian blur convolution kernel
     *
     * @param A string that indicates what type of kernel to create
     * @param An integer sigma necessary to construct the gaussian blur kernel
     */
	public Kernel(String kernelType, int sigma) {
		
	}
	
	/**
     * Gets the integer value at a specified row and column in a kernel
     *
     * @param r the row coordinate (y value) of the kernel
     * @param c the column coordinate (x value) of the kernel
     * @return An integer value of the kernel
     */
    public int at(int r, int c) {
    	return pix[r * this.c + c];
    }
    
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
