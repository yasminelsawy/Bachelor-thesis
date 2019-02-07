package displayImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.*;
//This module of the OpenCV help you read and write images to/from disk or memory.
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.calib3d.*;

public class fixFeature {
	public static void fixAnag(String Picname , int colCrop , int rowCrop){
		Mat originalMat = Imgcodecs.imread(Picname);
		Mat anagl      = new Mat(originalMat.rows(),(originalMat.cols()/2), originalMat.type());
		System.out.println("Original pic width x height"+originalMat.cols()+" x "+originalMat.rows());
		for(int row = 0;row < originalMat.rows()- rowCrop ;row++){
			for(int col = colCrop;col < (originalMat.cols()/2);col++){
				double [] leftColor  = originalMat.get(row + rowCrop , col);
				double [] rightColor = originalMat.get(row, (col + (originalMat.cols()/2) ) - colCrop);
				double [] anaglColor = {rightColor[0],rightColor[1],leftColor[2]};
				anagl.put(row, col, anaglColor);
			}
		}
		DisplayImage.Display(anagl, "anag");
		Imgcodecs.imwrite("PicCanagyTABELFRONTCORNER.png", anagl);
	}
	
	public static void fixAnag2(String Picname , int colCrop , int rowCrop){
		Mat originalMat = Imgcodecs.imread(Picname);
		Mat anagl      = new Mat(originalMat.rows(),(originalMat.cols()/2), originalMat.type());
		System.out.println("Original pic width x height"+originalMat.cols()+" x "+originalMat.rows());
		for(int row = 0;row < originalMat.rows();row++){
			for(int col = 0;col < (originalMat.cols()/2);col++){
				double [] leftColor  = originalMat.get(row, col);
				double [] rightColor = originalMat.get(row, (col + (originalMat.cols()/2)));
				double [] anaglColor = {rightColor[0],rightColor[1],leftColor[2]};
				anagl.put(row, col, anaglColor);
			}
		}
		DisplayImage.Display(anagl, "anag");
		Imgcodecs.imwrite("PicCanagyTABELFRONTCORNER.png", anagl);
	}
	
	public static void main(String[] args) {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
	    try {
	    System.load("D:\\programs\\opencv\\build\\bin\\opencv_ffmpeg340.dll");
	    } catch (UnsatisfiedLinkError e) {
	        System.err.println("Native code library failed to load.\n" + e);
	        System.exit(1);
	    }
	    //fixAnag("picA.png", 113 , 53);
	    //fixAnag("picB.png", 59 , 32);
	    //fixAnag("picC.png", 44 , 30);
	    //fixAnag("picC.png", 43 , 26);
	   //fixAnag("picC.png", 36 , 27);
	    //fixAnag("picC.png", 56 , 33);
	    
	}
}
