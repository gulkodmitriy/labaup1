package Chat;

import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.*;


public class Chat {
    private ArrayList<Message> chat;

    Chat() {
        chat = new ArrayList<>();
    }

    public ArrayList<Message> getChat() {
        return this.chat;
    }

    public void add(Message message) {
        chat.add(message);
    }

    public void show() throws ParseException {

        Collections.sort(chat, new ComparerAmount());
        for (Message s : chat) {
            System.out.println(s);
        }
    }

    public boolean deleteID(String ID) {
        for (Message s : chat) {
            if (s.toString().contains(ID)) {
                chat.remove(s);
                return true;
            }
        }
        return false;
    }

    public void searchAuthor(String author) {
        int i = 0;
        for (Message s : chat) {
            if (s.toString().contains(author)) {
                System.out.println(s);

                i--;
            }
            i++;
        }
        if (i == chat.size())
            System.out.println(author + " not found");
    }

    public void searchByWord(String word) {
        int i = 0;
        for (Message s : chat) {
            if (s.toString().toLowerCase().contains(word.toLowerCase())) {
                System.out.println(s);
                i--;
            }
            i++;
        }
        if (i == chat.size())
            System.out.println(word + " not found");
    }

    public void searchRegular(String regular){
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher;
        int step = 0;
        for(Message m: chat){
            matcher = pattern.matcher(m.getText());
            if(matcher.matches()){
                System.out.println(m);
                step--;
            }
            step++;
        }
        if(step==chat.size()){
            System.out.println("Regular expression not found");
        }
    }

    public void Period(String period) {
        int day1;
        int month1;
        int year1;
        int day2;
        int month2;
        int year2;
        StringTokenizer st = new StringTokenizer(period, "-.,-");
        int i = 0;

        day1 = Integer.parseInt(st.nextToken());
        month1 = Integer.parseInt(st.nextToken());
        year1 = Integer.parseInt(st.nextToken());
        day2 = Integer.parseInt(st.nextToken());
        month2 = Integer.parseInt(st.nextToken());
        year2 = Integer.parseInt(st.nextToken());

        Calendar c0 = new GregorianCalendar(year1, month1 - 1, day1);
        Calendar c1 = new GregorianCalendar(year2, month2 - 1, day2);
        Calendar c2 = new GregorianCalendar();


        for (Message s : chat) {
            c2.setTimeInMillis(s.getTimestamp());
            if (c2.before(c1))
                if (c2.after(c0)) {
                    System.out.println(s);
                    i--;
                }
            i++;
        }
        if (i == chat.size())
            System.out.println("Date not fount");
    }
}



