package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Dictionary {
	
	private LinkedList<String> listaDizionario;
	private TreeMap<String,String> mappaDizionario;
	
	
	
	
	
	
	public Dictionary() {
		super();
		this.listaDizionario=new LinkedList();
		this.mappaDizionario=new TreeMap();
	}






	public void loadDictionary(String language) {
		try {
			FileReader fr = new FileReader(language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			listaDizionario.add(word);
			mappaDizionario.put(word, word);
			}
			br.close();
			 } catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
		
		
		
	}
	
	public void liberaDizionario(){
		listaDizionario.clear();
		mappaDizionario.clear();
	}
	
	
	public List<String> testoInList(String s) {
		s=s.toLowerCase();
		s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		List<String> inputTextList=new LinkedList<String>();
		String [] inserire=s.split(" ");
		for(int k=0;k<inserire.length;k++) {
			inputTextList.add(inserire[k]);
		}
		return inputTextList;
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		LinkedList<RichWord> ritorno=new LinkedList<RichWord>();
		for(String s:inputTextList) {
			RichWord word=new RichWord(s,false);
			if(mappaDizionario.containsKey(s))
				word.setGiusta(true);
			ritorno.add(word);
		}
		
		
		return ritorno;
		
	}
	

}
