
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadLogsXML {

	static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	static Map<Date, String> activityMap = new TreeMap();

	static Map<Date, List<String>> dateHourMap = new TreeMap();

	public static void readXMLData() throws ParseException {
		String filePath = "C://Users//sumeet//Desktop//ProcessLog1.xml";
		File processFile = new File(filePath);
		processLog(processFile);

//		System.exit(0);

	}
	
	public static void main(String[] args) throws ParseException {
		readXMLData();
	}

	/**
	 * 
	 * @param xmlFile
	 * @throws ParseException
	 */
	public static void processLog(File processFile) throws ParseException {
		DocumentBuilder dBuilder;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {

			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(processFile);

			doc.getDocumentElement().normalize();

			NodeList processNodeList = doc.getElementsByTagName("ProcessLog");

			setActivityMap(processNodeList);

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void setActivityMap(NodeList processNodeList) throws FileNotFoundException, ParseException {
//		processMap = new HashMap<>();

		for (int i = 0; i < processNodeList.getLength(); i++) {
			ProcessLogDto processLogDto = getProcessLog(processNodeList.item(i));
			try {
				activityMap.put(fmt.parse(processLogDto.getTimeStamp()), processLogDto.getTaskID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setDateHourMap(activityMap);
	}

	private static void setDateHourMap(Map<Date, String> activityMap2) {
		Calendar cal = Calendar.getInstance();

		for (Map.Entry<Date, String> activity : activityMap.entrySet()) {
			cal.setTime(activity.getKey());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			cal.clear();
			cal.set(year, month, day, hour, 0);
			Date dateHour = cal.getTime();
			List<String> list = dateHourMap.get(dateHour);
			if (list == null)
				dateHourMap.put(dateHour, list = new ArrayList<String>());
			list.add(activity.getValue());
		}

		createInputText(dateHourMap);

	}

	private static void createInputText(Map<Date, List<String>> dateHourMap2) {

		try {
			fmt = new SimpleDateFormat("dd/MM/yyyy HH");
			PrintStream out = new PrintStream(new FileOutputStream("contextPrefixSpan1.txt"));
			for (Map.Entry<Date, List<String>> entry : dateHourMap.entrySet()) {
				List<String> list = entry.getValue();
				out.print("-1");
				for (String s : list) {
					out.print(" ");
					out.print(s);
				}
				out.println(" -2");
				System.out.println(fmt.format(entry.getKey()) + ": " + entry.getValue());
			}
			System.setOut(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param node
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ProcessLogDto getProcessLog(Node node) throws FileNotFoundException {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		ProcessLogDto processLog = new ProcessLogDto();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;

			// Process Log
			processLog.setTimeStamp(getTagValue("TimeStamp", element));

			processLog.setCaseID(getTagValue("CaseID", element));
			processLog.setTaskID(getTagValue("TaskID", element));
			processLog.setReason(getTagValue("Reason", element));

		}

		return processLog;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
//	public static EventLogDto getEventLog(Node node) {
//		// XMLReaderDOM domReader = new XMLReaderDOM();
//		EventLogDto eventLog = new EventLogDto();
//		if (node.getNodeType() == Node.ELEMENT_NODE) {
//			Element element = (Element) node;
//			eventLog.setTimeStamp(getTagValue("TimeStamp", element));
//			eventLog.setEventId(getTagValue("EventID", element));
//		}
//
//		return eventLog;
//	}

	/**
	 * 
	 * @param node
	 * @return
	 */
//	public static ProcessEventStateTransitionDto getProcessEventStateTransitionDto(Node node) {
//		// XMLReaderDOM domReader = new XMLReaderDOM();
//		ProcessEventStateTransitionDto processEventStateTransitionDto = new ProcessEventStateTransitionDto();
//		if (node.getNodeType() == Node.ELEMENT_NODE) {
//			Element element = (Element) node;
//			processEventStateTransitionDto.setTaskId(getTagValue("TaskID", element));
//			processEventStateTransitionDto.setPostEffect(getTagValue("PostEffect", element));
//			processEventStateTransitionDto.setPriorEffect(getTagValue("PriorEffect", element));
//		}
//
//		return processEventStateTransitionDto;
//	}

	/**
	 * 
	 * @param tag
	 * @param element
	 * @return
	 */
	public static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}