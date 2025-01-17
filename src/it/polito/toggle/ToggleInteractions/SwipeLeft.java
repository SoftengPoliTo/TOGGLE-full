package it.polito.toggle.ToggleInteractions;

import java.awt.MouseInfo;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import it.polito.toggle.ToggleInteraction;

public class SwipeLeft extends ToggleInteraction {

	public SwipeLeft(String packagename, String search_type, String search_keyword, String timestamp,
			String interaction_type, String args, File screen_capture, File dump)
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		super(packagename, search_type, search_keyword, timestamp, interaction_type, args, screen_capture, dump);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> generateSikuliLines() {
		
			
		ArrayList<String> res = new ArrayList<>();

		res.add("sleep(1)");
		res.add("wait(\"" + timestamp + "_cropped.png\", 30)");
		res.add("r = find(\"" + timestamp + "_cropped.png\")");
		res.add("start = r.getCenter()");
		res.add("stepX = 250");
		res.add("run = start");
		res.add("mouseMove(start); wait(0.2)");
		res.add("mouseDown(Button.LEFT); wait(0.2)");
		res.add("run = run.left(stepX)");
		res.add("mouseMove(run)");
		res.add("mouseUp()");
		res.add("wait(0.2)");
		
		return res;

		
	}
	
	@Override
	public ArrayList<String> generateEyeStudioLines() {

		
		ArrayList<String> res = new ArrayList<>();
		res.add("Check \"{ImageFolder}\\" + timestamp + "_cropped.png\"");

		res.add("Move \"{ImageFolder}\\" + timestamp + "_cropped.png\"");
		res.add("Sleep 10");
		res.add("MouseLeftPress");
		res.add("MoveRelative \"-250\" \"0\"");
		res.add("MouseLeftRelease");
		
		return res;
		
		
	}
	
	@Override
	public ArrayList<String> generateEyeAutomateJavaLines(String starting_folder) {

		
		ArrayList<String> res = new ArrayList<>();

		res.add("image = eye.loadImage(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\");");
		res.add("if (image != null) {");
		res.add("\tmatch = eye.findImage(image);");
		res.add("\tif (match == null) {");
		res.add("\t\tSystem.out.println(\"Test failed - " + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\");");
		res.add("\t\treturn \"fail;\"+interactions;");
		res.add("\t}");

		
		res.add("\teye.move(match.getCenterLocation());");
		
		res.add("		eye.move(match.getCenterLocation());\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		int mov_x = (int) MouseInfo.getPointerInfo().getLocation().getX();" +
				"		for (int i=0; i<100; i++){  \r\n" + 
				"		    mov_x -= 2.5;\n" + 
				"		    bot.mouseMove(mov_x,(int)(MouseInfo.getPointerInfo().getLocation().getY()));\r\n" + 
				"		    bot.delay(10);\r\n" + 
				"		}\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);");
	
		

		res.add("}");
		res.add("else {");
		res.add("\tSystem.out.println(\"image not found\");");
		res.add("\treturn \"fail;\"+interactions;");
		res.add("}");
		
		return res;
		
	}
	
	@Override
	public ArrayList<String> generateSikuliJavaLines(String starting_folder) {

		ArrayList<String> res = new ArrayList<>();

		res.add("try {");

		res.add("\tsikuli_screen.wait(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\", 30);");

		res.add("\torg.sikuli.script.Match sikuli_match = sikuli_screen.find(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\",  "\\\\") + "\");");
		res.add("\torg.sikuli.script.Location l = sikuli_match.getCenter();");
		res.add("\tsikuli_screen.mouseMove(l);");
		res.add("\tsikuli_screen.mouseDown(Button.LEFT);");
		res.add("\tsikuli_screen.wait(0.2);");
		res.add("\tl = l.left(250);");
		res.add("\tsikuli_screen.mouseMove(l);");
		res.add("\tsikuli_screen.mouseUp();");
		res.add("}");
		res.add("catch (FindFailed ffe) {");
		res.add("\tffe.printStackTrace();");
		res.add("\treturn \"fail;\"+interactions;");
		res.add("}");

		
		return res;
		
	}
	
	@Override
	public ArrayList<String> generateCombinedJavaLines(String starting_folder) {

		
		ArrayList<String> res = new ArrayList<>();

		res.add("image = eye.loadImage(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\");");
		res.add("if (image != null) {");
		res.add("\tmatch = eye.findImage(image);");
		
		
		//try sikuli

		res.add("\tif (match == null) {");
		res.add("\t\teyeautomate_failures++;");
		res.add("\t\ttry {");

		res.add("\t\t\tsikuli_screen.wait(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\", 25);");
		res.add("\t\t\torg.sikuli.script.Match sikuli_match = sikuli_screen.find(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\",  "\\\\") + "\");");
		res.add("\t\t\torg.sikuli.script.Location l = sikuli_match.getCenter();");
		res.add("\t\t\tsikuli_screen.mouseMove(l);");
		res.add("\t\t\tsikuli_screen.mouseDown(Button.LEFT);");
		res.add("\t\t\tsikuli_screen.wait(0.2);");
		res.add("\t\t\tl = l.left(250);");
		res.add("\t\t\tsikuli_screen.mouseMove(l);");
		res.add("\t\t\tsikuli_screen.mouseUp();");
		res.add("\t\t}");
		res.add("\t\tcatch (FindFailed ffe) {");
		res.add("\t\t\tffe.printStackTrace();");
		res.add("\t\t\treturn \"fail;\" + eyeautomate_failures + \";\" + interactions;");
		res.add("\t\t}");

		res.add("\t}");


		//eyeautomate

		res.add("\telse {");
		res.add("		eye.move(match.getCenterLocation());\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		int mov_x = (int) MouseInfo.getPointerInfo().getLocation().getX();" +
				"		for (int i=0; i<100; i++){  \r\n" + 
				"		    mov_x -= 2.5;\n" + 
				"		    bot.mouseMove(mov_x,(int)(MouseInfo.getPointerInfo().getLocation().getY()));\r\n" + 
				"		    bot.delay(10);\r\n" + 
				"		}\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);");
		res.add("\t}");
		res.add("}");
		res.add("else {");
		res.add("\tSystem.out.println(\"image not found\");");
		res.add("\treturn \"fail;\" + eyeautomate_failures + \";\" + interactions;");
		res.add("}");


	
		return res;
	}
	
	@Override
	public ArrayList<String> generateCombinedJavaLinesSikuliFirst(String starting_folder) {
	
		ArrayList<String> res = new ArrayList<String>();
		
		
		res.add("try {");
		res.add("\tsikuli_screen.wait(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\", 5);");

		res.add("\torg.sikuli.script.Match sikuli_match = sikuli_screen.find(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\",  "\\\\") + "\");");
		res.add("\torg.sikuli.script.Location l= sikuli_match.getCenter();");
		res.add("\tsikuli_screen.mouseMove(l);");
		res.add("\tsikuli_screen.mouseDown(Button.LEFT);");
		res.add("\tsikuli_screen.wait(0.2);");
		res.add("\tl = l.left(250);");
		res.add("\tsikuli_screen.mouseMove(l);");
		res.add("\tsikuli_screen.mouseUp();");

		res.add("}");
		res.add("catch (FindFailed ffe) {");
		res.add("\tsikuli_failures++;");
		res.add("\timage = eye.loadImage(\"" + new String(starting_folder + "\\" + timestamp + "_cropped.png").replace("\\", "\\\\") + "\");");
		res.add("\tif (image != null) {");
		res.add("\t\tmatch = eye.findImage(image);"); 
		res.add("\t\tif (match == null) {");		//test failed also with eyeautomate
		res.add("\t\t\treturn \"fail;\" + sikuli_failures + \";\" + interactions;");
		res.add("\t\t}");
		res.add("\t\telse {");						//test ok with eyeautomate
		res.add("		eye.move(match.getCenterLocation());\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		for (int i=0; i<100; i++){  \r\n" + 
				"		    int mov_x = (((int)(MouseInfo.getPointerInfo().getLocation().getX() -250) * i)/100) + ((int)MouseInfo.getPointerInfo().getLocation().getX()*(100-i)/100);\r\n" + 
				"		    bot.mouseMove(mov_x,(int)(MouseInfo.getPointerInfo().getLocation().getY()));\r\n" + 
				"		    bot.delay(10);\r\n" + 
				"		}\r\n" + 
				"		Thread.sleep(20);\r\n" + 
				"		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);");

		res.add("\t\t}");
		res.add("\t}");
		res.add("\telse {");
		res.add("\t\tSystem.out.println(\"image not found\");");
		res.add("\t\treturn \"fail;\" + sikuli_failures + \";\" + interactions;");
		res.add("\t}");
		res.add("}");
				
		
		return res;
		
	}

	
}
