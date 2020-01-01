package table;

import java.util.LinkedList;
import java.util.List;

public class Table {

	private List<Row> headers;
	private List<Row> bodies;
	private List<Row> footers;

	public Table() {
		headers = new LinkedList<Row>();
		bodies = new LinkedList<Row>();
		footers = new LinkedList<Row>();
	}

	public Object toHtml() {
		return String.format("<table>%s</table>", headers());
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

}
