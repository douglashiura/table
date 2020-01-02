package table;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.junit.Test;

import jankovicsandras.imagetracer.ImageTracer;

public class TableTest {


	@Test
	public void empty() throws Exception {
		Table table = new Table();
		assertEquals("<table></table>", table.toHtml());
	}

	@Test
	public void border() throws Exception {
		Table table = new Table();
		table.setBorder();
		assertEquals("<table border=\"1\"></table>", table.toHtml());
	}

	@Test
	public void borderStyle() throws Exception {
		Table table = new Table();
		Style style = new Style();
		style.setBorder("1px solid");
		table.setStyle(style);
		assertEquals("<table style=\"border:1px solid;\"></table>", table.toHtml());
	}

	@Test
	public void headerOneCell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		row.addCell(new HeaderCell("Cell"));
		table.addHeader(row);
		assertEquals("<table><thead><tr><th>Cell</th></tr></thead></table>", table.toHtml());
	}

	@Test
	public void header2x2Cell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		HeaderCell cell = new HeaderCell("Cell");
		row.addCell(cell);
		row.addCell(cell);
		table.addHeader(row);
		table.addHeader(row);
		assertEquals(
				"<table><thead><tr><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></thead></table>",
				table.toHtml());
	}

	@Test
	public void bodyOneCellNoStyle() throws Exception {
		Table table = new Table();
		Row row = new Row();
		row.addCell(new BodyCell("Cell"));
		table.addBody(row);
		assertEquals("<table><tbody><tr><td>Cell</td></tr></tbody></table>", table.toHtml());
	}

	@Test
	public void bodyOneCell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		Style style = new Style();
		style.setBorder("1px solid");
		row.setStyle(style);
		row.addCell(new BodyCell("Cell"));
		table.addBody(row);
		assertEquals("<table><tbody><tr style=\"border:1px solid;\"><td>Cell</td></tr></tbody></table>",
				table.toHtml());
	}
	@Test
	public void bodyOneCellStyle() throws Exception {
		Table table = new Table();
		Row row = new Row();
		Style style = new Style();
		style.setBorder("1px solid");
		Cell cell=new BodyCell("Cell");
		cell.setStyle(style);
		row.addCell(cell);
		table.addBody(row);
		assertEquals("<table><tbody><tr><td style=\"border:1px solid;\">Cell</td></tr></tbody></table>",
				table.toHtml());
	}

	@Test
	public void body2x2Cell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		HeaderCell cell = new HeaderCell("Cell");
		row.addCell(cell);
		row.addCell(cell);
		table.addBody(row);
		table.addBody(row);
		assertEquals(
				"<table><tbody><tr><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></tbody></table>",
				table.toHtml());

	}

	@Test
	public void footerOneCell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		row.addCell(new BodyCell("Cell"));
		table.addFooter(row);
		assertEquals("<table><tfoot><tr><td>Cell</td></tr></tfoot></table>", table.toHtml());
	}

	@Test
	public void footer2x2Cell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		HeaderCell cell = new HeaderCell("Cell");
		row.addCell(cell);
		row.addCell(cell);
		table.addFooter(row);
		table.addFooter(row);
		assertEquals(
				"<table><tfoot><tr><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></tfoot></table>",
				table.toHtml());
	}

	@Test
	public void jsoup() throws Exception {
		Document document = Jsoup.parse(
				"<table><tfoot><tr><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></tfoot></table>");
		W3CDom transform = new W3CDom();
		transform.fromJsoup(document);
		document.getAllElements().forEach(element -> {
			System.out.println(element.nodeName());
		});

	}

	public static void main(String[] args) throws Exception {
		JEditorPane frame = new JEditorPane("text/html",
				"<table><thead><tr style=\"border: 1px solid;\"><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></thead></table>");
		frame.setSize(595, 842);
		BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g = image.createGraphics();
		frame.printAll(g);
		image.flush();
		ImageIO.write(image, "png", new File("image.png"));
		ImageTracer.saveString("output.svg", ImageTracer.imageToSVG("image.png", null, null));

	}

}
