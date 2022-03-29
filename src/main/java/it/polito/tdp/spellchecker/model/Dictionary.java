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
			
			FileReader fri = new FileReader("src/main/resources/Italian.txt");
			BufferedReader bri = new BufferedReader(fri);

			FileReader fre = new FileReader("src/main/resources/English.txt");
		    BufferedReader bre = new BufferedReader(fre);
		    
		    
			
			String word;
			if(language.compareTo("Italian")==0) {
			while ((word = bri.readLine()) != null) {
			listaDizionario.add(word);
			mappaDizionario.put(word, word);
			}
			
			bri.close();
			}
			else {
				while ((word = bre.readLine()) != null) {
					listaDizionario.add(word);
					mappaDizionario.put(word, word);
					}
					
					bre.close();
					}
			}
			  catch (IOException e){
			System.out.println("Errore nella lettura del file");
			e.printStackTrace();
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
			if(listaDizionario.contains(s))
				word.setGiusta(true);
			ritorno.add(word);
		}
		
		
		return ritorno;
		
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		LinkedList<RichWord> ritorno=new LinkedList<RichWord>();
		for(String s:inputTextList) {
			RichWord word=new RichWord(s,false);
			for(int i =0;i<listaDizionario.size();i++){
				if(listaDizionario.get(i).compareTo(s)==0)
					word.setGiusta(true);
			}

		}
		
		
		return ritorno;
		
	}
	
	
	public List<RichWord> spellCheckTextDicotomic(List<String> inputTextList){
		LinkedList<RichWord> ritorno=new LinkedList<RichWord>();
		for(String s:inputTextList) {
			RichWord word=new RichWord(s,false);
			for(int i =0;i<listaDizionario.size();i++){
				if(listaDizionario.get(i).compareTo(s)==0)
					word.setGiusta(true);
			}

		}
		
		
		return ritorno;
		
	}
	
	public String stampa(LinkedList<RichWord> lista) {
		String s=new String();
		for(RichWord r: lista) {
			if(r.isGiusta()==false)
				s=s+r.getParola()+"\n";
		}
		return s;
		
	}
	

}
