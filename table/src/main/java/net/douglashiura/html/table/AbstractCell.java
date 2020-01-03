package net.douglashiura.html.table;

public class AbstractCell implements Cell {

	private String content;
	private String type;
	private Style style;

	public AbstractCell(String htmlType, String content) {
		type = htmlType;
		this.content = content;
	}

	public String toHtml() {
		String style = "";
		if (this.style != null) {
			style = " style=\"" + this.style.toHtml() + "\"";
		}
		return String.format("<%s%s>%s</%s>", type, style, content, type);
	}

	@Override
	public void setStyle(Style style) {
		this.style = style;
	}
}
