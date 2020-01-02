package table;

import java.util.LinkedList;
import java.util.List;

public class Row {

	private List<Cell> cells;
	private Style style;

	public Row() {
		cells = new LinkedList<Cell>();
	}

	public void addCell(Cell cell) {
		cells.add(cell);
	}

	public String toHtml() {
		StringBuffer cellHtml = new StringBuffer();
		cells.forEach(cell -> {
			cellHtml.append(cell.toHtml());
		});

		String style = "";
		if (this.style != null) {
			style = " style=\"" + this.style.toHtml() + "\"";
		}
		return String.format("<tr%s>%s</tr>", style, cellHtml);
	}

	public void setStyle(Style style) {
		this.style = style;
	}

}
