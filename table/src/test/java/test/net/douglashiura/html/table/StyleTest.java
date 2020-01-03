package test.net.douglashiura.html.table;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.douglashiura.html.table.Style;

public class StyleTest {

	@Test
	public void empty() throws Exception {
		Style style = new Style();
		assertEquals("", style.toHtml());
	}

	@Test
	public void border() throws Exception {
		Style style = new Style();
		style.setBorder("1px solid");
		assertEquals("border:1px solid;", style.toHtml());
	}
	
	@Test
	public void border_collapse() throws Exception {
		Style style = new Style();
		style.setBorderCollapse("collapse");
		assertEquals("border-collapse:collapse;", style.toHtml());
	}
	@Test
	public void borderAndBorderCollapse() throws Exception {
		Style style = new Style();
		style.setBorder("1px solid");
		style.setBorderCollapse("collapse");
		assertEquals("border:1px solid;border-collapse:collapse;", style.toHtml());
	}
	@Test
	public void borderBottom() throws Exception {
		Style style = new Style();
		style.setBorderBottom("1px solid");
		assertEquals("border-bottom:1px solid;", style.toHtml());
	}
	
}
