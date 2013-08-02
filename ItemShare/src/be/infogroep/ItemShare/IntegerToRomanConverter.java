package be.infogroep.ItemShare;

public class IntegerToRomanConverter {

	private int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5,
			4, 1 };
	private String[] letters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL",
			"X", "IX", "V", "IV", "I" };

	IntegerToRomanConverter() {

	}

	public String Convert(int Number) {
		String roman = "";
		for (int i = 0; i < numbers.length; i++) {
			while (Number >= numbers[i]) {
				roman += letters[i];
				Number -= numbers[i];
			}
		}
		return roman;
	}

}
