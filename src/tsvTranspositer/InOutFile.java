package tsvTranspositer;

public class InOutFile {

private String name;
	
	private int currentLine; 
	
	private int width;
	private int length;
	
	private int serieRowsNb;
	
	public int getSeriesRowsNb() {
		return serieRowsNb;
	}
	public void setSeriesRowsNb(int serieRowsNb) {
		this.serieRowsNb = serieRowsNb;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getCurrentLine() {
		return currentLine;
	}
	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	} 
	
	public InOutFile(String name) {
		this.name = name;
		this.currentLine = 1;
		this.serieRowsNb = 2; // TODO : Find default value
	}
	
	public InOutFile(String name, int serieRowsNb) {
		this(name);
		this.serieRowsNb = serieRowsNb;
	}
	
	
	public void incrLine() {
		setCurrentLine(getCurrentLine() + 1);
	}
}
