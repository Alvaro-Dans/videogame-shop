package utils;

public class Utils {
	
	public static String convertCharArrayToString(char[] input) {
		String password = "";
		char[] passwordChar = input;
		for (char c : passwordChar) {
			password += c;
		}
		return password;
	}

}
