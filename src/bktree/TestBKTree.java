package bktree;

import java.io.IOException;
import java.util.HashMap;

import distance.LevenshteinDistance;


public class TestBKTree {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {


		String[] wordList = new String[] {
				"remote",
				"barn",
				"book",
				"glass",
				"chair",
				"closet",
				"skull",
				"giraffe",
				"boat",
				"soda"
		};
		
		BKTree<String> bkTree = new BKTree<String>(new LevenshteinDistance());
		
		for (String word : wordList) {
			bkTree.add(word);
		}
		
                //running a search query with a threshold value
		HashMap<String, Integer> queryMap = bkTree.query("book", 2);
		System.out.println(queryMap);
		
                //running bestmatch search
		String searchTerm = "temotw";
		System.out.println("Best match for search '"+searchTerm+"' = "+bkTree.findBestWordMatchWithDistance(searchTerm));
		
		searchTerm = "garage";
		System.out.println("Best match for search '"+searchTerm+"' = "+bkTree.findBestWordMatchWithDistance(searchTerm));
		
	}		
}