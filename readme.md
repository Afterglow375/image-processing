A program that applies various image effects to an input image. Meant to be run through the command line. Uses an outdated PPM format for images, to simply things.

Usage: java ImageProcessing (infile).ppm (outfile).ppm imageProcess numberArgument

Examples inputs:
java ImageProcessing test.ppm out.ppm grayscale
java ImageProcessing test.ppm out.ppm threshold 128
java ImageProcessing test.ppm out.ppm boxBlur 128
java ImageProcessing test.ppm out.ppm gaussianBlur 3
java ImageProcessing test.ppm out.ppm sobelGradient
java ImageProcessing test.ppm out.ppm segmentation 128
