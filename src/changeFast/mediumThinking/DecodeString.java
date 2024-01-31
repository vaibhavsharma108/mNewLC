package changeFast.mediumThinking;

public class DecodeString {
	public static void main(String[] args) {
		String st = "3[a2[c]]";
		System.out.println(st.substring(2));
		System.out.println(decodeString( "3[a2[c]]"));
	}
	static int index = 0;
	
	// String s = "3[a]2[bc]"
	public static String decodeString(String s) {
		StringBuilder resultBuilder = new StringBuilder();
		while(index < s.length() && s.charAt(index) != ']') {
			if(Character.isDigit(s.charAt(index))) {
				int frequency = 0;
				while(Character.isDigit(s.charAt(index))) {
					frequency = frequency*10 + s.charAt(index) - '0';
					index++;
				}
				index++;
				String decodedString = decodeString(s);
				index++;
				for(int i = 0 ; i < frequency ; i++) {
					resultBuilder.append(decodedString);
				}
				
			}else {
				resultBuilder.append(s.charAt(index));
				index++;
			}
		}
		
		return resultBuilder.toString();
		
	}
	
	
	

}
