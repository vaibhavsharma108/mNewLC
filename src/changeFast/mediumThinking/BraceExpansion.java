package changeFast.mediumThinking;

import java.util.ArrayList;
import java.util.Arrays;

public class BraceExpansion {

	public static void main(String[] args) {
		String[] result = expand("{a,b}c{d,e}f");
		Arrays.stream(result).forEach(s -> {System.out.print(s+" ");} );
	}

	static int index = 0;

	public static String[] expand(String s) {
		
		ArrayList<String> resultList = findExpandSolution(s);
		
		String result[] = new String[resultList.size()];
		for(int i = 0 ; i < resultList.size() ; i++) {
			result[i] = resultList.get(i);
		}
		Arrays.sort(result);
		return result;
	}
	
	
	private static ArrayList<String> findExpandSolution(String s){
		ArrayList<String> currentList = new ArrayList<>();
		while(index < s.length() && s.charAt(index) != '}') {
			ArrayList<String> newCurrentList = new ArrayList<>();
			if(s.charAt(index) == '{') {
				index++;
				String result[] = expand(s);
				index++;
				
				for(String st : result) {
					if(currentList.isEmpty()) {
						newCurrentList.add(st);
					}else {
						for(String currentString : currentList) {
							newCurrentList.add(currentString + st);
						}
					}
				}
			}else {
				
				StringBuilder strBuild = new StringBuilder();
				while(index < s.length() && (Character.isLowerCase(s.charAt(index)) || s.charAt(index) == ',' )){
					if(s.charAt(index) == ',') {
						if(currentList.isEmpty()) {
							newCurrentList.add(strBuild.toString());
						}else {
							for(String listString : currentList) {
								newCurrentList.add(listString+strBuild.toString());
							}
						}
						strBuild.setLength(0);
						index++;
						continue;
					}
					
					strBuild.append(s.charAt(index));
					index++;
				}
				
				if(strBuild.length() > 0) {
					if(currentList.isEmpty()) {
						newCurrentList.add(strBuild.toString());
					}else {
						for(String listString : currentList) {
							newCurrentList.add(listString+strBuild.toString());
						}
					}
				}
				
			}
			
			currentList = newCurrentList;
		}
		
		return currentList;
		
	}

}
