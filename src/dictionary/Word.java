package dictionary;

public class Word {
	public static enum Type {
		S, V;
	}
	
	protected Type type;
	protected String word;
	
	public Word(String word, Type type) {
		this.type = type;
		this.word = word;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getWord() {
		return word;
	}
}
