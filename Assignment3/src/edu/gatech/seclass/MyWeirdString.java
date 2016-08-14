package edu.gatech.seclass;

public class MyWeirdString implements MyWeirdStringInterface {
	private String currStr;
	private static String[] romanNumeralArray = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

	@Override
	public void setWeirdString(String string) {
		currStr = string;
	}

	@Override
	public String getWeirdString() {
		return currStr;
	}

	@Override
	public String getEvenCharacters() {
		StringBuilder strWithEvenChar = new StringBuilder();
		for (int i = 1; i < currStr.length(); i += 2) {
			strWithEvenChar.append(currStr.charAt(i));
		}
		return strWithEvenChar.toString();
	}

	@Override
	public String getOddCharacters() {
		StringBuilder strWithOddChar = new StringBuilder();
		for (int i = 0; i < currStr.length(); i += 2) {
			strWithOddChar.append(currStr.charAt(i));
		}
		return strWithOddChar.toString();
	}

	@Override
	public int countDigits() {
		int count = 0;
		for (int i = 0; i < currStr.length(); i++) {
			if (Character.isDigit(currStr.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition)
			throws MyIndexOutOfBoundsException, IllegalArgumentException {
		if (startPosition < 1 || startPosition > currStr.length() || endPosition < 1 || endPosition > currStr.length()) {
			throw new MyIndexOutOfBoundsException("startPosition or endPosition is out of bound");
		} else {
			if (startPosition > endPosition) {
				throw new IllegalArgumentException("startPosition > endPosition");
			}
		}
		
		StringBuilder convertedStr = new StringBuilder();
		for (int i = 0; i < currStr.length(); i++) {
			if (i >= startPosition - 1 && i <= endPosition - 1 && Character.isDigit(currStr.charAt(i))) {
				convertedStr.append(romanNumeralArray[currStr.charAt(i) - '0']);
			} else {
				convertedStr.append(currStr.charAt(i));
			}
		}
		currStr = convertedStr.toString();
	}

}
