package test.net.douglashiura.geometry;

import net.douglashiura.html.HtmlConvert;

public class Example {

	public static void main(String[] args) throws Exception {
		HtmlConvert html = new HtmlConvert("<hr color=\"black\"/>");
		System.out.println(html.getStringSVG());
//		new FileOutputStream("svg.svg").write(html.getStringSVG().getBytes(StandardCharsets.UTF_8));
	}

}
