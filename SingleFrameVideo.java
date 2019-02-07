package displayImage;


import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class SingleFrameVideo {

	// capture the first frame from the video
	public static void Saveframe(){
		VideoCapture readVideo= new VideoCapture("D:\\College\\bachelor\\Java Extras\\Videos and images\\MyMovieCAR.mp4");
		
		 JFrame jframe = new JFrame("Video Title");

		    //Inform jframe what to do in the event that you close the program
		    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    //Create a new JLabel object vidpanel
		    JLabel lbl = new JLabel();
		    lbl.setHorizontalAlignment(JLabel.CENTER);
		    lbl.setVerticalAlignment(JLabel.CENTER);
		    
		    //assign vidPanel to jframe ,Sets the contentPane property.
		    jframe.add(lbl);
		    

		Mat frameRead=new Mat();
		
		
	    jframe.setVisible(true);
	    int i=1;
	    while (i++<=400) {

	        //If next video frame is available
	        if (readVideo.read(frameRead)) {
	        	jframe.setSize(frameRead.cols()+10,frameRead.rows());	
	        	if(i==400){	
	        		i++;
	        	     Imgcodecs.imwrite("test400CAR.jpg", frameRead);
	        	}
	        	
	        	ImageIcon image = new ImageIcon(Mat2BufferedImage(frameRead));
	        	
	            //Update the image in the vidPanel
	            lbl.setIcon(image);
	            
	            //Update the vidPanel in the JFrame
	            lbl.updateUI();
	           // jframe.add(lbl);
	       
	            

	        }
	    }
	    
	}
	public static void anagl() {
		Mat frameRead=Imgcodecs.imread("test300CAR.jpg");
		Mat newimg=new Mat(frameRead.rows(), (frameRead.cols()/2), frameRead.type()); 
		for (int j = 315; j < 720-73; j++) {
			for (int i2 = 30; i2 < (frameRead.cols()/2)-30; i2++) {
			double [] leftColor= frameRead.get(j+10, i2);
			double [] rightColor= frameRead.get(j, i2+(frameRead.cols()/2)-30);
			double [] newimgColor={rightColor[0],rightColor[1],leftColor[2]};
			newimg.put(j,i2,newimgColor);
			}
			
		}
		Imgcodecs.imwrite("analyptest300CAR14col-29.jpg", newimg);
	    DisplayImage.Display(newimg, "Ana");

	}
	
	public static void anaglNOCROP() {
		Mat frameRead=Imgcodecs.imread("test300CAR.jpg");
		Mat newimg=new Mat(frameRead.rows(), (frameRead.cols()/2), frameRead.type()); 
		for (int j = 315; j < 720-17; j++) {
			for (int i2 = 30; i2 < (frameRead.cols()/2)-30; i2++) {
			double [] leftColor= frameRead.get(j+17, i2);
			double [] rightColor= frameRead.get(j, i2+(frameRead.cols()/2));
			double [] newimgColor={rightColor[0],rightColor[1],leftColor[2]};
			newimg.put(j,i2,newimgColor);
			}
			
		}
		Imgcodecs.imwrite("analyptest300CAR+17col0.jpg", newimg);
	    DisplayImage.Display(newimg, "Ana");

	}
	public static BufferedImage Mat2BufferedImage(Mat m) {
	    //Method converts a Mat to a Buffered Image
	    int type = BufferedImage.TYPE_BYTE_GRAY;
	     if ( m.channels() > 1 ) {
	         type = BufferedImage.TYPE_3BYTE_BGR;
	     }
	     int bufferSize = m.channels()*m.cols()*m.rows();
	     byte [] b = new byte[bufferSize];
	     m.get(0,0,b); // get all the pixels
	     BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	     final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	     System.arraycopy(b, 0, targetPixels, 0, b.length);  
	     return image;
	    }
	
	
	
	public static void main(String[] args) throws IOException {
		//load the OpenCV Native Library
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		    try {
		    System.load("D:\\programs\\opencv\\build\\bin\\opencv_ffmpeg340.dll");
		    
		    //anagl();
		    anaglNOCROP();
		    //Saveframe();
		    } catch (UnsatisfiedLinkError e) {
		        System.err.println("Native code library failed to load.\n" + e);
		        System.exit(1);
		    }
	}
}
