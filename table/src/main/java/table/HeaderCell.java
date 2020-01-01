package table;

public class HeaderCell implements Cell{

	private String content;

	public HeaderCell(String content) {
		this.content = content;
	}

	public String toHtml() {
		return String.format("<th>%s</th>", content);
	}

}
