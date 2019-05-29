package helpers;

public class Helpers {
	
	public static String getExtension(String pathOrFilename) {
		String extension = "";
		int i = pathOrFilename.lastIndexOf('.');
		if (i > 0) {
		    extension = pathOrFilename.substring(i+1);
		}
		return extension.toUpperCase();
	}
}
