package changeFast.mediumThinking;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.*;

public class NumberOfAtoms {

	public static void main(String[] args) {
		System.out.println(countOfAtoms("Mg(OH)2"));
	}

	static int index = 0;

	public static String countOfAtoms(String formula) {

		// runnning the loop for i = 0 i < formula.length
		// cf. add(charFrequency)
		// if(c == '(') -> cf.add(countOfAtoms(remainingString))

		// TreeMap -> aggregate all the frequency at that perticular key
		// convert all the keys with frequency in string return

		List<CFrequency> result = generateCompoundFrequencyResult(formula);
		TreeMap<String, Integer> tmap = new TreeMap<>((a , b) -> {return a.compareTo(b);});
		StringBuilder cResult = new StringBuilder();
		
		for(CFrequency cf : result) {
		if(	tmap.containsKey(cf.compund)){
				tmap.put(cf.compund, tmap.get(cf.compund)+cf.frequency);
			}else {
				tmap.put(cf.compund, cf.frequency);
			}
		}
		
		StringBuilder finalResult = new StringBuilder();
		
		for(Entry<String, Integer> entry : tmap.entrySet()) {
			if(entry.getValue() != 1)
			finalResult.append(entry.getKey()+entry.getValue());else {
				finalResult.append(entry.getKey());
			}
		}
		
		return finalResult.toString();

	}

	private static List<CFrequency> generateCompoundFrequencyResult(String formula) {
		ArrayList<CFrequency> result = new ArrayList<>();
		while (index < formula.length() && formula.charAt(index) != ')') {

			if (formula.charAt(index) != '(') {
				String compund = getCompund(formula);
				int frequency = getFrequency(formula);
				result.add(new CFrequency(compund, frequency));
			} else {
				index++;
				List<CFrequency> lastList = generateCompoundFrequencyResult(formula);
				index++;
				int frequency = getFrequency(formula);
				for (CFrequency cf : lastList) {
					cf.frequency = cf.frequency * frequency;
				}

				result.addAll(lastList);
			}
		}

		return result;
	}

	private static String getCompund(String formula) {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(formula.charAt(index));
		index++;
		while(index < formula.length()&& (formula.charAt(index) - 'a' >= 0 && formula.charAt(index) - 'z' <=26)) {
			strBuild.append(formula.charAt(index));
			index++;
		}
		
		return strBuild.toString();
		
	}

	private static int getFrequency(String formula) {
		int defaultFrequency = 1;
		int currentFrequency = 0;
		
		while(index < formula.length() && Character.isDigit(formula.charAt(index))) {
			currentFrequency = currentFrequency*10 + formula.charAt(index) - '0';
			index++;
		}
		
		return currentFrequency == 0 ? defaultFrequency : currentFrequency;
	}

	static class CFrequency {
		public CFrequency(String c, int f) {
			compund = c;
			frequency = f;
		}

		String compund;
		int frequency;
	}

}
