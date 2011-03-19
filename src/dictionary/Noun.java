package dictionary;

public class Noun extends Word {
	public static enum Gender {
		M("o"), F("a");
		
		String article;
		private Gender(String article) {
			this.article = article;
		}
	}
	
	public static enum Quantity {
		S, P
	}
	
	protected Gender gender;
	protected Quantity quantity;
	
	public Noun(String word, Gender gender, Quantity quantity) {
		super(word, Type.S);
		
		this.gender = gender;
		this.quantity = quantity;
	}

	public Gender getGender() {
		return gender;
	}

	public Quantity getQuantity() {
		return quantity;
	}
	
	public String processNoun(boolean cap) {
		String ret = gender.article;
		
		if (cap) {
			ret = ret.toUpperCase();
		}
		
		if (quantity == Quantity.P) {
			ret += "s";
		}
		
		return ret + " " + word;
	}
}
