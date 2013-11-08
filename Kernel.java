import java.util.Arrays;


public class Kernel {
	protected int r;
	protected int c;
	protected int pix[];
	
	/**
     * Constructor for a box blur or sobel gradient convolution kernel
     *
     * @param A string that indicates what type of kernel to create (box blur or sobel gradient)
     */
	public Kernel(String kernelType) {
		this.r = 3;
		this.c = 3;
		this.pix = new int[9];
		if (kernelType.equals("boxBlur")) {
			Arrays.fill(pix, 1); // Set the 3x3 kernel to all 1's if box blur
		}
		else if (kernelType.equals("sobelGradientGx")) { // Sobel gradient Gx
			
		}
		else { // Sobel gradient Gy
			
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
     * Returns the total size of the kernel array
     * 
     * @return Number of rows in the kernel
     */
    public int size() {return this.r * this.c;}
    
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
