package table;

import static org.junit.Assert.*;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class TableTest {

	@Test
	public void empty() throws Exception {
		Table table = new Table();
		assertEquals("<table></table>", table.toHtml());
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
	public void bodyOneCell() throws Exception {
		Table table = new Table();
		Row row = new Row();
		row.addCell(new BodyCell("Cell"));
		table.addBody(row);
		assertEquals("<table><tbody><tr><td>Cell</td></tr></tbody></table>", table.toHtml());
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
		Document document = Jsoup.parse("<table><tfoot><tr><th>Cell</th><th>Cell</th></tr><tr><th>Cell</th><th>Cell</th></tr></tfoot></table>");
		W3CDom transform= new W3CDom();
		transform.fromJsoup(document);
		document.getAllElements().forEach(element->{
			System.out.println(element.nodeName());
		});  
			
	}
	
	
	

}
