package table;

import java.util.HashMap;
import java.util.Map;

public class Style {

	private Map<String, String> proprieties;

	public Style() {
		proprieties = new HashMap<String, String>();
	}

	public void setBorder(String border) {
		proprieties.put("border", border);
	}

	public String toHtml() {
		StringBuffer stringBuffer = new StringBuffer();
		proprieties.entrySet().forEach(entry -> {
			stringBuffer.append(entry.getKey()+":"+entry.getValue()+";");
		});
		return stringBuffer.toString();
	}

}
