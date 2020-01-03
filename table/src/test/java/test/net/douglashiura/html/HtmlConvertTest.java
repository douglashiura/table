package test.net.douglashiura.html;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import org.junit.Before;
import org.junit.Test;

import net.douglashiura.html.HtmlConvert;

public class HtmlConvertTest {
	private HtmlConvert html;
	private ByteArrayOutputStream png;

	@Before
	public void setUp() throws Exception {
		String htmlString = "<table><thead><tr style=\"border: 1px solid;\"><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></thead></table>";
		JEditorPane frame = new JEditorPane("text/html", htmlString);
		frame.setSize(595, 842);
		BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = image.createGraphics();
		frame.printAll(g);
		image.flush();
		png = new ByteArrayOutputStream();
		ImageIO.write(image, "png", png);
		html = new HtmlConvert(htmlString);

	}

	@Test
	public void toPng() throws Exception {
		assertArrayEquals(png.toByteArray(), html.getBytesPNG());
	}

	@Test
	public void toSvg() throws Exception {
		assertTrue(html.getStringSVG().startsWith("<svg width=\"595\" height=\"842\""));
	}

}
