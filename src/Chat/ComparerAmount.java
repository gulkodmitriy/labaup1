package Chat;

import java.util.Comparator;

/**
 * Created by User on 16.02.2016.
 */
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
