package test.net.douglashiura.html;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.douglashiura.html.HtmlConvert;
import net.douglashiura.html.table.BodyCell;
import net.douglashiura.html.table.Cell;
import net.douglashiura.html.table.Row;
import net.douglashiura.html.table.Style;
import net.douglashiura.html.table.Table;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class GenerateTable {

	private Attribute svg;
	private Attribute html;
	private Instances dataset;

	public GenerateTable() throws Exception {
		ArrayList<Attribute> attInfo = new ArrayList<Attribute>();
		svg = new Attribute("svg", Boolean.TRUE);
		html = new Attribute("html", Boolean.TRUE);
		attInfo.add(html);
		attInfo.add(svg);
		dataset = new Instances("svgToHtml", attInfo, 100);
		dataset.setClass(html);
		generateDataSet();

		NGramTokenizer tokenizer = new NGramTokenizer();
		tokenizer.setNGramMinSize(1);
		tokenizer.setNGramMaxSize(1);
		tokenizer.setDelimiters(" \r\n\t\\s.,;:'\"()?!'");

		// Set the filter
		StringToWordVector filter = new StringToWordVector();
		filter.setAttributeIndicesArray(new int[] { 1 });
		filter.setOutputWordCounts(true);
		filter.setTokenizer(tokenizer);
		filter.setInputFormat(dataset);
		filter.setWordsToKeep(1000000);
		filter.setDoNotOperateOnPerClassBasis(true);
		filter.setLowerCaseTokens(true);
		filter.setTFTransform(true);
		filter.setIDFTransform(true);
		Instances outputInstances = weka.filters.Filter.useFilter(dataset, filter);
		Classifier classifier = new weka.classifiers.bayes.NaiveBayesMultinomial();
		classifier.buildClassifier(outputInstances);
	}

	private void generateDataSet() throws Exception {
		Style style = new Style();
		style.setBorderCollapse("collapse");
		style.setBorderBottom("1px solid");
		Table table = new Table();
		for (int cells = 1; cells < 15; cells++) {
			for (int numberRow = 0; numberRow < 40; numberRow++) {
				Row row = new Row();
				generateCells(style, row, cells);
				table.addBody(row);
				addInstance(table);
			}
		}
	}

	private void addInstance(Table table) throws Exception, FileNotFoundException, IOException {
		String html2 = table.toHtml();
		HtmlConvert html = new HtmlConvert(html2);
		DenseInstance newInstance = new DenseInstance(2);
		newInstance.setValue(this.html, html2);
		newInstance.setValue(svg, html.getStringSVG());
		dataset.add(newInstance);
	}

	public static void main(String[] args) throws Exception {
		new GenerateTable();
	}

	private static void generateCells(Style style, Row row, Integer numberCells) {
		for (int i = 0; i < numberCells; i++) {
			Cell cell = new BodyCell("Cell");
			cell.setStyle(style);
			row.addCell(cell);
		}
	}

}
