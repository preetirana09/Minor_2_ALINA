/**
 * 
 */
package userbot;
import java.util.*;
import java.awt.Desktop;
import java.io.*;
import java.text.*;
import java.net.*;
/**
 * @author Ankush
 *
 */

import java.awt.*;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

/** The Language class defines all the available responses of the bot
 * a)It can pick up a response based on this API definiton-done;
 * b)It can browse over Internet for the responses-done;
 * c)It responds via a text file included which is the response file-done;
 * d) a negative acknowledgement if all fails-done
  * */

/** 
 * The first prototype shall include the following five responses;
 * a)When asked about its name
 * b)when asked who made you
 * c)when asked what it does
 * d)when asked what is its purpose
 * e)when asked to tell a joke, randomly tell a joke from 3 available choices 
 *
 * February 16 2018, first prototype is done
 * */
public class LanguageClass {
	String question;
	String response;
	Random rand;
	/** The method gets the Question asked to Alina 
	 * the question is converted to LowerCase
	 * */
	void getQuestion(JTextField t1){
		
		String question=t1.getText();
		this.question=question;
		this.question=question.toLowerCase();		
	
	
	}
	
	/** This Method is used to Send the answered response to the UI */
	JTextArea sendResponse(JTextArea l1,JTextField t1){
		getQuestion(t1);
		search();
		l1.setText(response);
		
		return l1;
	}
	
	
	/**This method theoretically should learn from the user.*/
	JTextArea learnResponse(JTextArea j1,JTextField t1){
		String Learnable=t1.getText();
		Learnable=Learnable.toLowerCase();
		Learnable=Learnable.trim();
		boolean z=true;
		//We added a boolean to check if the textfield is empty. Its boilerplate code but for now I understand only this.
		if(z=t1.getText().isEmpty()){
			j1.setText("Type what you want me to learn and I shall remember it!");
		}
		
		else{
			//This ensures that only genuine responses are learnt.
			if(Learnable.length()<12){
				j1.setText("Can you please elaborate on that?");
			}
			else{
			j1.setText("Okay I will remember it!");}
		}
		return j1;
	}
	
	
	
	/** The method searches for keywords and then gives appropriate answer accordingly */
	void search(){
		String greeting="";
		String joke="";
		String Invalid="";
		String commandsAvailable="You can directly call any command to invoke my functions in a flash.\nThe commands are:\n1)Time 2)Day 3)Search 4)Notepad 5)Video(for VLC)";
		
		//common questions in a form of a single keyword here!
		String s[];
		s=question.split(" ");
			for(int i=0;i<s.length;i++){
				if(s[i].equals("hi")||s[i].equals("hey")||s[i].equals("hey,")||s[i].equals("hey!")||s[i].equals("hi,")||s[i].equals("hi!")||s[i].equals("yo")||s[i].equals("Yo")||s[i].equals("Wassup")||s[i].equals("wassup")||s[i].equals("Waddup")||s[i].equals("waddup")){
					response=randomiserGreeting(greeting);
				}
				else if(s[i].equals("name")||s[i].equals("name?")){
					response="I am Alina, your very own personal friend . Go on ask me a question, or make me say a joke! I can open your favourite Web Browser or bring up Notepad, help you know today's day or time or simply chat with you!";
				}
				
				else if(s[i].equals("joke")||s[i].equals("joke!")||s[i].equals("joke?")||s[i].equals("Joke?")){
					response=randomiserJoke(joke);
					
				}
				
				else if(s[i].equals("made")||s[i].equals("creator")||s[i].equals("created")||s[i].equals("author")||s[i].equals("made?")||s[i].equals("creator?")||s[i].equals("created?")||s[i].equals("author?")){
					response="This one great guy named as Ankush! Trust me I know a genius when I see one kid.";
				}
				
				else if(s[i].equals("Time")||s[i].equals("time")||s[i].equals("time?")||s[i].equals("Time?")){
					response=timeResponse();
				}
				
				else if(s[i].equals("Day")||s[i].equals("day")||s[i].equals("day?")||s[i].equals("Day?")){
					response=dayResponse();
					break;
				}
				
				else if(s[i].equals("Search")||s[i].equals("search")||s[i].equals("browser")||s[i].equals("Browser")||s[i].equals("Search online")||s[i].equals("search online")||s[i].equals("search online?")||s[i].equals("Search online?")||s[i].equals("browser?")||s[i].equals("Browser?")){
					searchOnline();
					break;
				}
				
				else if(s[i].equals("command")||s[i].equals("commands")||s[i].equals("Commands")||s[i].equals("Command")||s[i].equals("command?")||s[i].equals("commands?")||s[i].equals("Commands?")||s[i].equals("commands?")){
					response=commandsAvailable;
				}
				
				else if((s[i].equals("notepad")||s[i].equals("Notepad")||s[i].equals("Notepad?")||s[i].equals("notepad?"))){
					notepadCall();
					break;
				}
				
				else if((s[i].equals("Video")||s[i].equals("Video?")||s[i].equals("video")||s[i].equals("video?"))){
					vlcCall();
				}
				
				else if((s[i].equals("Explorer")||s[i].equals("explorer")||s[i].equals("explorer?")||s[i].equals("Explorer?"))){
					explorerCall();
				}
				
				else if((s[i].equals("file")||s[i].equals("File")||s[i].equals("files")||s[i].equals("Files")||s[i].equals("file?")||s[i].equals("File?"))){
					fileOpenCall();
					break;
				}
				else if(s[i].equals("")){
					response="Umm sorry I didn't get that!";
				}

				else{
					response=randomiserInvalidInput(Invalid);
				}
				
			}
			
			//Common questions in a form of sentence from here
			if(question.equals("how are you")){
				response="just fine";
				
			}
		
	}
	

	/**
	 * Opens notepad to write a file
	 * */
	private void notepadCall() {
		Runtime rs = Runtime.getRuntime();
		 
	    try {
	      rs.exec("notepad");
	      response="Notepad is active";
	    }
	    catch (IOException e) {
	      System.out.println(e);
	    }   
		
	}

	/**
	 * The Search function runs well on Ubuntu and Windows, I dont know about the Mac as testing capabilities are not available yet. 
	 * It opens up a default browser invoking the native system commannds of windows.
	 * The default imports in java are supposed to be java.net.URL and java.util
	 * Also it throws an IO exception, so it is necessary to surround it with a catch and throw.
	 * We need to specify a URL usually it is a search engine we can modify it
	 * Future plans involve buuilding an  inbuilt browser in the Alina Index library itself.
	 * getDesktop() function- The Desktop class allows a Java application to launch associated applications registered on the native desktop to handle a URI or a file.
	 * For Ubuntu the exec command from runtime class directly stores the path to the default Browser address.
	 * */
	private void searchOnline() {
		// TODO Auto-generated method stub
		//removing white space and search keyword while actually searching in a google search url
		String qa=question.toString().replaceAll("search", "").replaceAll("\\s","+").trim();
		String url = "https://www.google.co.in/search?num=50&safe=off&dcr=0&source=hp&ei=zwKGWvnDFMvSvASv1p_ABw&q="+qa;

        if (Desktop.isDesktopSupported()) {
            // Windows
        
            try {
				Desktop.getDesktop().browse(new URI(url));
				response = "search activated";
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            // Ubuntu
            Runtime runtime = Runtime.getRuntime();
            try {
				runtime.exec("/usr/bin/firefox -new-window " + url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

	/**Print current day
	 */
	private String dayResponse(){
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		String day="Today is "+new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		return day;
	}
	
	/**This method essentially calls out time */
	private String timeResponse() {
		//Date format here doesnot shows a date but rathe shows a time in the following format
		 DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		//The date object intialise the system machine's date to display it.
         Date date = new Date();
         															//The  date format helps parsing the date to a string
         String time="The current time according to my watch is: "+dateFormat.format(date);

		return time;
	}

	
	//We need to clear the textField after every answer so this works
	public JTextField ClearField(JTextField textField) {
		String no="";
		textField.setText(no);
		return textField;
		
	}
	
	
	/**Creation of Randomiser greeting function*/
	private String randomiserGreeting(String greeting){
		
		String greeting1="Hi! how's it going! ";
		String greeting2="Hi there! What shall we talk about now?";
		String greeting3="Hey! Wassup!";
		String greeting4="Hi! I bet you have tons of stuff to talk about! ";
		String greeting5="Bonjour! Wassup!";
		
		Random rand =new Random();
		int n=rand.nextInt(5)+1;
		
		if(n==1){
			greeting=greeting1;
		}
		
		else if(n==2){
			greeting=greeting2;
		}
		
		else if(n==3){
			greeting=greeting3;
		}
		
		else if(n==4){
			greeting=greeting4;
		}
		
		else if(n==5){
			greeting=greeting5;
		}
		return greeting;
	}

	
	/**Creation of Randomiser joke function*/
	private String randomiserJoke(String joke){
		
		String joke1="So his girlfriend called him and said,\"No one is at home\". He immediately Rushed to her place and found.....really no one was at home  XD ";
		String joke2="You are not fat! You are just.....easier to see!";
		String joke3="So Dhinchak Pooja anounces her concerts will be on the footpath, Salman bhai pleased with that responds he is out for a drive these days! XD";
		String joke4="It is a scientific fact that not liking a picture of God on Facebook doesn't do anything to you!";
		String joke5="Ethics direct to give a 500 rupee note you found lying on the road to police. Facts state that a free pizza is always good! XD";
		
		Random rand =new Random();
		int n=rand.nextInt(5)+1;
		
		if(n==1){
			joke=joke1;
		}
		
		else if(n==2){
			joke=joke2;
		}
		
		else if(n==3){
			joke=joke3;
		}
		
		else if(n==4){
			joke=joke4;
		}
		
		else if(n==5){
			joke=joke5;
		}
		return joke;
	}
	
	/**Creation of Randomiser negative response or no invalid input function*/
	private String randomiserInvalidInput(String Invalid){
		
		String Invalid1="That's something that even me, an AI powered bot can't comprehend! ";
		String Invalid2="Tch Tch I don't know how to answer that! ";
		String Invalid3="Are you sure you typed correctly? See I can't understand that! ";
		String Invalid4="-_- What did you write? ";
		String Invalid5="Alina doesn't know! ";
		
		Random rand =new Random();
		int n=rand.nextInt(5)+1;
		
		if(n==1){
			Invalid=Invalid1;
		}
		
		else if(n==2){
			Invalid=Invalid2;
		}
		
		else if(n==3){
			Invalid=Invalid3;
		}
		
		else if(n==4){
			Invalid=Invalid4;
		}
		
		else if(n==5){
			Invalid=Invalid5;
		}
		return Invalid;
	}

	
	/**
	 * VLC Calls
	 * **/
	private void vlcCall() {
		Runtime rs = Runtime.getRuntime();
	    try {
	      rs.exec("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe");
	      response="VLC is active!";
	    }
	    catch (IOException e) {
	      System.out.println(e);
	    }   	    
	}

	/**Calls out explorer of windows*/
	private void explorerCall() {
		Runtime rs = Runtime.getRuntime();
		
		 
	    try {
	      rs.exec("explorer.exe");
	      response="explorer is active!";
	    }
	    catch (IOException e) {
	      System.out.println(e);
	    }
	}
	
	/**
	 * Opens any file present on desktop*/
	void fileOpenCall() {
		Runtime rs = Runtime.getRuntime();
		try
	    {
			//bug to fix, cant open file with space in between
	    	String file=question.trim().replaceAll("file", "").replaceAll("\\s","");
	        Desktop d=Desktop.getDesktop();
	        File f=new File("C:\\Users\\Ankush Sharma\\Desktop\\"+file);
	        d.open(f);
	        response= file+" successfully opened";
	    }
	    catch(Exception e){
	    	String file=question.trim().replaceAll("file", "").replaceAll("\\s","");
	    	response="Could not open the file "+file;
	    	}
	    }
	
}
