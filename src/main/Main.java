package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import it.enhancer.enhancer.Enhancer;
import it.enhancer.enhancer.Utils;
import it.polito.toggle.ToggleClassManager;
import it.polito.toggle.ToggleInteraction;
import it.polito.toggle.ToggleTranslator;

public class Main {
	
	
	private static String adbPath = System.getenv("LOCALAPPDATA")+"\\Android\\Sdk\\platform-tools";
	
	public static void executeEnhancedEspresso(String testPackage, String testName, String instrumentation) throws IOException {
		
		ProcessBuilder builder = new ProcessBuilder(
				"cmd.exe", "/c\"", adbPath + "\\adb\" shell am instrument -w -e class "+testPackage+"."+testName+" "+instrumentation);


		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			System.out.println(line);
			if (line == null) { break; }


		}

		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String logcat_filename = "logcat.txt";
		List<ToggleInteraction> interactions = new ArrayList<>();
		String starting_folder = "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest";
		String testPackage = "it.feio.android.omninotes";
		String className = "TestInterfaceBasicTry";
		String enhancedClassName = "TestInterfaceBasicTryEnhanced";
		String test_name = "testSearchUnsearchUpdates";
		String logcat_tool_tag = "touchtest";
		//String instrumentation="ar.rulosoft.mimanganu.test/android.support.test.runner.AndroidJUnitRunner";
		
		
		
		
		Enhancer en = new Enhancer("test");
		
		//Utils.removeLogFiles();
		

		
		
		
		
		
		//enhancement of a test class
		//en.generateEnhancedClassFrom("C:\\Users\\Riccardo Coppola\\MiMangaNu-master_oldgraphics\\MiMangaNu-master\\app\\src\\androidTest\\java\\ar\\rulosoft\\mimanganu\\TestAdvancedFeatures.java");
		
		
		
		//en.generateEnhancedClassFrom("C:\\Users\\Riccardo Coppola\\StudioProjects\\Travel-Mate-master\\Android\\app\\src\\androidTest\\java\\io\\github\\project_travel_mate\\TripsTest.java");

		
		
		//en.generateEnhancedClassFrom("C:\\Users\\Riccardo Coppola\\StudioProjects\\k-9-master\\app\\k9mail\\src\\androidTest\\java\\com\\fsck\\k9\\debug\\MessageOperationsTest.java");
		//en.generateEnhancedClassFrom("C:\\Omni-Notes-develop_oldgraphics\\Omni-Notes-develop\\omniNotes\\src\\androidTest\\java\\it\\feio\\android\\omninotes\\TestSearchChecklist.java");
		//en.generateEnhancedClassFrom("C:\\Users\\Riccardo Coppola\\StudioProjects\\PassAndroid-2.5.0_oldgraphics\\PassAndroid-2.5.0\\app\\src\\androidTest\\java\\org\\ligi\\passandroid\\TestCreatePassTabs.java");

		
/*
		
		//execution of an enhanced test class
		long st = System.currentTimeMillis();
		executeEnhancedEspresso(testPackage, enhancedClassName, instrumentation);
		long et = System.currentTimeMillis();
		System.out.println("time to execute " + (et - st));
		
		*/
		
		
		
		
		
		//translation of instructions
	
		long time_for_script_creation_before = System.currentTimeMillis();

		ArrayList<String> tests = new ArrayList<String>();
		tests.add("testTripInfoFields");
		tests.add("testAddTwoTrips");
		//tests.add("testChecklist");
		//tests.add("testCompass");
		
		//tests.add("testRestaurantsSortRating");
		//tests.add("testHangoutContent");
		//tests.add("testSetUnsetStarTest");

		//ToggleClassManager tcm = new ToggleClassManager("TestAdditionalFeatures", "it.feio.android.omninotes.alpha", "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest", tests);
		//ToggleClassManager tcm = new ToggleClassManager("TestInterfaceBasicTry", "ar.rulosoft.mimanganu", "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest", tests);
		ToggleClassManager tcm = new ToggleClassManager("TripsTest", "io.github.project_travel_mate", "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest", tests);
		//ToggleClassManager tcm = new ToggleClassManager("MessageOperationsTest", "com.fsck.k9.debug", "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest", tests);
		//ToggleClassManager tcm = new ToggleClassManager("TestAdvancedFeatures", "ar.rulosoft.mimanganu", "C:\\Users\\Riccardo Coppola\\Desktop\\touchtest", tests);

		
		ArrayList<String> result_class = tcm.createClass();
		for (String s: result_class) {
			System.out.println(s);
		}
		ToggleTranslator tt = new ToggleTranslator(starting_folder, testPackage, className, test_name);
		tt.readLogcatToFile(logcat_filename);				
		List<String> filtered_logcat_interactions = tt.filterLogcat(logcat_filename, logcat_tool_tag);
		for (String s:filtered_logcat_interactions) {
			String[] separated = s.split(": ");
			String line_data = separated[1];						
			String[] separated2 = line_data.split(", ");
			ToggleInteraction interaction = tt.readInteractionsFromLogcat(s);
			interactions.add(interaction);
		}
		tt.saveCroppedScreenshots(interactions);
		tt.createEyeStudioScript(interactions);
		tt.createSikuliScript(interactions);
		tt.createEyeAutomateJavaMethod(interactions);
		tt.createSikuliJavaMethod(interactions);
		tt.createCombinedJavaMethod(interactions);
		long time_for_script_creation_after = System.currentTimeMillis();

		System.out.println("time to translate " + (time_for_script_creation_after - time_for_script_creation_before));


	
		
		
	}
	
	

}
