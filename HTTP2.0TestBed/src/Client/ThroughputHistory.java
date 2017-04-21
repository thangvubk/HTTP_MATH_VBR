package Client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by phanhaiphong on 6/18/15.
 */
public class ThroughputHistory {
    private ArrayList<ArrayList<Double>> history; // TODO: consider using a treemap instead

    public ThroughputHistory(int maxNumberOfSegments) {
        history = new ArrayList<>(maxNumberOfSegments);
        //for example: we only support push 1,2,3,4 segments --> add 4 list
        for (int i = 0; i < maxNumberOfSegments; i++) {
            history.add(new ArrayList<>());
        }
    }

    public void add(int numberOfSegments, double ratio) {
        getHistoryOf(numberOfSegments).add(ratio);
    }

    public double getCumulativeProbability(int numberOfSegments, double ratio) {
        ArrayList<Double> mylist = getHistoryOf(numberOfSegments);
        Collections.sort(mylist);
        //only sort when needed, and we must sort before binarySearch
        int index = Collections.binarySearch(mylist, ratio);
        //CY: if this list contains the required ratio multiple times, then binarySearch will return one of their indeces, not sure which one. But anyway this case is very rare.
        if (index < 0) {
            index = (index + 1) * -1;
            //if the ratio we want to find is not found (which is mostly the case, because usually there is a very little chance that a double is duplicated), then we should use the index of the first element that is greater than our ratio (the last element that is smaller than our ratio should not be used, because we want to ensure that our cumulative probability is LESS than something, so we would better use the greater element)
        }
        //index now can be from 0 to myList.size()
        return index / (double) mylist.size(); //the result cumulative probability is from 0 to 1
    }

    private ArrayList<Double> getHistoryOf(int numberOfSegments) {
        //push 1 segment: index 0, push 2 segment, index 1
        return history.get(numberOfSegments - 1);
    }
}
