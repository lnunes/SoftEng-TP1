package entry;

import javax.swing.JOptionPane;

import dictionary.Dictionary;
import dictionary.Noun;
import dictionary.Verb;
import dictionary.Word;

public class EntryClass {
	
	private static Dictionary dictionary = new Dictionary("files/dictionary.txt");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		do {
			Noun subject = (Noun) readWord("Substantivo");
			
			Verb verb = (Verb) readWord("Verbo");
			
			Noun object = null;
			if (verb.demandObject()) {
				object = (Noun) readWord("Objeto");
			}
			
			String phrase = makePhrase(subject, verb, object);
			if (phrase != null) {
				print(phrase);
			}
			
			int r = JOptionPane.showConfirmDialog(null, "Criar nova frase?", "Nova Frase", JOptionPane.YES_NO_OPTION);
			if (r == JOptionPane.NO_OPTION) {
				break;
			}
		} while(true);
	}
	
	private static Word readWord(String label) {
		do {
			String s = JOptionPane.showInputDialog(null, "Insira o " + label, label);
			Word word = dictionary.getWord(s);
			if (word == null) {
				print(label + " " + s +" não cadastrado.");
				continue;
			}
			
			return word;
		} while(true);
	}
	
	public static String makePhrase(Noun subject, Verb verb, Noun object) {
		String ret = subject.processNoun(true);
		
		ret += " " + verb.getConjugation(subject.getQuantity());
		
		if (verb.demandObject()) {
			ret += " " + object.processNoun(false);
		}
		
		return ret + ".";
	}
	
	public static void print(String s) {
		JOptionPane.showMessageDialog(null, s, "Mensagem", JOptionPane.PLAIN_MESSAGE);
	}

}
