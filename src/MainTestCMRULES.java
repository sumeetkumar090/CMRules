
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;

import ca.pfv.spmf.algorithms.sequential_rules.cmrules.AlgoCMRules;

public class MainTestCMRULES {

	public static void main(String[] arg) throws IOException, ParseException {
		// Load database

		//ReadLogsXML.readXMLData();
		System.out.println();
		String input = fileToPath("contextPrefixSpan1.txt"); // the database
		String output = "output.txt"; // the path for saving the frequent
											// itemsets found
		double minSup = 0.75;
		double minConf = 0.50;

		AlgoCMRules algo = new AlgoCMRules();

		// TO SET MINIMUM / MAXIMUM SIZE CONSTRAINTS you can use the following
		// lines:
		// algo.setMinLeftSize(1);
		// algo.setMaxLeftSize(0);
		// algo.setMinRightSize(1);
		// algo.setMaxRightSize(1);

		algo.runAlgorithm(input, output, minSup, minConf);

		algo.printStats();
	}

	public static String fileToPath(String filename) throws UnsupportedEncodingException {
		URL url = MainTestCMRULES.class.getResource(filename);
		return java.net.URLDecoder.decode(url.getPath(), "UTF-8");
	}
}
