package table;

import java.util.LinkedList;
import java.util.List;

public class Table {

	private List<Row> headers;
	private List<Row> bodies;
	private List<Row> footers;
	private Boolean hasBorder;
	private Style style;

	public Table() {
		hasBorder = Boolean.FALSE;
		headers = new LinkedList<Row>();
		bodies = new LinkedList<Row>();
		footers = new LinkedList<Row>();
	}

	public Object toHtml() {
		String border = hasBorder ? " border=\"1\"" : "";
		String style = "";
		if (this.style != null) {
			style = " style=\"" + this.style.toHtml() + "\"";
		}
		return String.format("<table%s%s>%s</table>", border, style, headers());
	}

	private String headers() {
		StringBuffer html = new StringBuffer();
		html.append(rowsOf("<%sthead>", headers));
		html.append(rowsOf("<%stbody>", bodies));
		html.append(rowsOf("<%stfoot>", footers));
		return html.toString();
	}

	private String rowsOf(String tag, List<Row> rows) {
		StringBuffer html = new StringBuffer();
		if (!rows.isEmpty()) {
			html.append(String.format(tag, ""));
			rows.forEach(row -> {
				html.append(row.toHtml());
			});
			html.append(String.format(tag, "/"));
		}
		return html.toString();
	}

	public void addHeader(Row row) {
		headers.add(row);
	}

	public void addBody(Row row) {
		bodies.add(row);
	}

	public void addFooter(Row row) {
		footers.add(row);
	}

	public void setBorder() {
		hasBorder = Boolean.TRUE;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

}
