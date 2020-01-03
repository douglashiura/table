package test.net.douglashiura.html.table;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.douglashiura.html.table.BodyCell;
import net.douglashiura.html.table.Cell;
import net.douglashiura.html.table.HeaderCell;
import net.douglashiura.html.table.Row;
import net.douglashiura.html.table.Style;
import net.douglashiura.html.table.Table;

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
		Cell cell = new BodyCell("Cell");
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

}
