package dictionary;

import java.util.HashMap;

import dictionary.Noun.Quantity;

public class Verb extends Word {
	public static enum Semantics {
		I(false), D(true);
		
		boolean demandObject;
		
		private Semantics(boolean demandObject) {
			this.demandObject = demandObject;
		}
	}
	
	protected Semantics semantics;
	protected HashMap<Quantity, String> conjugationMap = new HashMap<Quantity, String>();
	
	public Verb(String word, Semantics semantics) {
		super(word, Type.V);
		
		this.semantics = semantics;
	}
	
	public void addConjugation(Quantity quantity, String conjugation) {
		conjugationMap.put(quantity, conjugation);
	}
	
	public String getConjugation(Quantity quantity) {
		return conjugationMap.get(quantity);
	}
	
	public Semantics getSemantics() {
		return semantics;
	}
	
	public boolean demandObject() {
		return semantics.demandObject;
	}
}
