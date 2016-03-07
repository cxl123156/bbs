package edu.zsc.cxl.bbs.validate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


public class validatePic {

	private char codeSequence[] = {
			'A','B','C','D','E','F','G','H','I',
			'J','K','L','M','N','O','P','Q','R',
			'S','T','U','V','W','X','Y','Z','1',
			'2','3','4','5','6','7','8','9','0'
	};
	
	public String getValiPic(int width , int height , OutputStream os){
		if(width<=0) width=60;
		if(height<=0) height=20;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphic = bi.getGraphics();
		graphic.setColor(new Color(0xDCDCDC));
		graphic.fillRect(0, 0, width, height);
		graphic.setColor(Color.black);
		graphic.drawRect(0, 0, width-1, height-1);
		String strEnsure = "";
		for(int i=0;i<4;i++){
			strEnsure+=codeSequence[(int) (codeSequence.length*Math.random())];
		}
		graphic.setColor(Color.black);
		graphic.setFont(new Font("Atlantic Inline",Font.PLAIN,18));
		String str = strEnsure.substring(0,1);
		graphic.drawString(str, 8, 17);
		str = strEnsure.substring(1,2);
		graphic.drawString(str, 20, 15);
		str = strEnsure.substring(2,3);
		graphic.drawString(str, 35, 18);
		str = strEnsure.substring(3,4);
		graphic.drawString(str, 45, 15);
		
		Random rand = new Random();
		for(int i = 10;i<10;i++){
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			graphic.drawOval(x, y, 1, 1);
		}	
		graphic.dispose();
		try {
			ImageIO.write(bi, "JPEG", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return strEnsure;
	}
	
	
	
}
