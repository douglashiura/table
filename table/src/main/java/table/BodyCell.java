package table;

public class BodyCell implements Cell {

	private String content;

	public BodyCell(String content) {
		this.content = content;
	}

	public String toHtml() {
		return String.format("<td>%s</td>", content);
	}

}
