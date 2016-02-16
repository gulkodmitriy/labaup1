package Chat;

import java.util.Comparator;


public class ComparerAmount implements Comparator<Message> {
    @Override
    public int compare(Message a, Message b) {
        if (a.getTimestamp() > b.getTimestamp())
            return 1;
        if (a.getTimestamp() < b.getTimestamp())
            return -1;
        return 0;
    }
}
