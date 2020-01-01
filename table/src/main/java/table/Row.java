package table;

import java.util.LinkedList;
import java.util.List;

public class Row {

	private List<Cell> cells;

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
		return String.format("<tr>%s</tr>", cellHtml);
	}

}
