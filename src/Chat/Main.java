package Chat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            PrintStream ps;
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc1 = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            Scanner read = new Scanner(fis);
            Date date;
            JSONParser parser = new JSONParser();
            JSONObject jsonObj;
            String str;
            String ID;
            String author;
            String text;
            long time;

            Chat c = new Chat();
            Message message;
            int i = 0;
            while (read.hasNextLine()) {
                str = read.nextLine();
                jsonObj = (JSONObject) parser.parse(str);
                message = new Message((String) jsonObj.get("id"), (String) jsonObj.get("message"), (String) jsonObj.get("author"), Long.parseLong(jsonObj.get("timestamp").toString()));
                c.add(message);
            }

            do {
                System.out.println("Add message: 1");
                System.out.println("Show history: 2");
                System.out.println("Delete message by ID: 3");
                System.out.println("Print messages: 4");
                System.out.println("Search by author: 5");
                System.out.println("Search by word: 6");
                System.out.println("View message history for a period: 7");
                System.out.println("Search by regular expression: 8");

                i = sc1.nextInt();
                if (i < 0) {
                    throw new IllegalArgumentException();
                }
                switch (i) {
                    case 1:
                        date = new Date();
                        System.out.println("Enter user: ");
                        author = sc2.nextLine();
                        System.out.println("Message: ");
                        text = sc2.nextLine();
                        if (text.length() > 140) {
                            System.out.println("Message text is longer than 140 characters. Try again");
                            continue;
                        }
                        message = new Message(text, author, date.getTime());
                        JSONObject jo = new JSONObject();
                        jo.put("ID", message.getID());
                        jo.put("author", message.getAutor());
                        jo.put("message", message.getText());
                        jo.put("timestamp", message.getTimestamp());
                        c.add(message);
                        break;
                    case 2:
                        c.show();
                        break;
                    case 3:
                        System.out.println("Enter ID");
                        ID = sc2.nextLine();
                        if (c.deleteID(ID)) {
                            System.out.println("ID found and message is deleted");
                        }
                        else {
                            System.out.println("ID not found");
                        }

                        break;
                    case 4:
                        ps = new PrintStream(new FileOutputStream("output.txt"));
                        for (Message s : c.getChat()) {
                            ps.println(s.getJsonString());
                        }
                        break;
                    case 5:
                        System.out.println("Enter author");
                        str = sc2.nextLine();
                        c.searchAuthor(str);
                        break;
                    case 6:
                        System.out.println("Enter word");
                        str = sc2.nextLine();
                        c.searchByWord(str);
                        break;
                    case 7:
                        System.out.println("Enter period: (example 14.04.1997-14.04.2015)");
                        str = sc2.nextLine();
                        c.Period(str);
                        break;
                    case 8:
                        System.out.println("Enter regular expression");
                        str = sc2.nextLine();
                        c.searchRegular(str);
                        break;
                    case 9:

                }
                System.out.println();
            }
            while (i >= 0);


        } catch (IOException ex) {
            System.out.println("Check your file");
        } catch (org.json.simple.parser.ParseException ex) {
            System.out.println("Error");
        } catch (IllegalArgumentException ex){
            System.out.println("I must be bigger or equal 0");
        }
    }
}
