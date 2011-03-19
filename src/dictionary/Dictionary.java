package dictionary;

import static entry.EntryClass.print;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import dictionary.Noun.Gender;
import dictionary.Noun.Quantity;
import dictionary.Verb.Semantics;
import dictionary.Word.Type;

public class Dictionary {
	
	private String filename;
	private HashMap<String, Word> wordMap = new HashMap<String, Word>();
	
	public Dictionary(String filename) {
		this.filename = filename;
		load();
	}
	
	public void load() {
		print("======Carregando dicionário======");
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			while((line = br.readLine()) != null) {
				String[] args = line.split(";");
				Type type = Type.valueOf(args[0]);
				String word = args[1];
				
				switch (type) {
				case S:
					Noun noun = new Noun(word, Gender.valueOf(args[2]), Quantity.valueOf(args[3]));
					addWord(noun);
					break;
				case V:
					Verb verb = new Verb(word, Semantics.valueOf(args[2]));
					verb.addConjugation(Quantity.S, args[3]);
					verb.addConjugation(Quantity.P, args[4]);
					addWord(verb);
					break;
				default:
					addWord(new Word(word, null));
					break;
				}
			}
			
			in.close();
		} catch (Exception e) {
			print("Não foi possível carregar o dicionário");
			System.exit(0);
		}
	}
	
	private void addWord(Word obj) {
		wordMap.put(obj.getWord(), obj);
	}
	
	public Word getWord(String word) {
		return wordMap.get(word);
	}
}
