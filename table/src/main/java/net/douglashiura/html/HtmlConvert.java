package net.douglashiura.html;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import jankovicsandras.imagetracer.ImageTracer;

public class HtmlConvert {

	private ByteArrayOutputStream bo;
	private String svg;

	public HtmlConvert(String html) throws Exception {
		JEditorPane frame = new JEditorPane("text/html", html);
		frame.setSize(595, 842);
		BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = image.createGraphics();
		frame.printAll(g);
		image.flush();
		bo = new ByteArrayOutputStream();
		ImageIO.write(image, "png", bo);
		InputStream in = new ByteArrayInputStream(bo.toByteArray());
		BufferedImage bImageFromConvert = ImageIO.read(in);
		svg = ImageTracer.imageToSVG(bImageFromConvert, null, null);
	}

	public byte[] getBytesPNG() {
		return bo.toByteArray();
	}

	public String getStringSVG() {
		return svg;
	}

}
