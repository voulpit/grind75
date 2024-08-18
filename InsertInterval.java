package grind75;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0], stop = newInterval[1];
        // case: newInterval contains all intervals
        if (intervals.length == 0 || start <= intervals[0][0] && stop >= intervals[intervals.length-1][1]) {
            int[][] result = new int[1][2];
            result[0] = newInterval;
            return result;
        }

        int[] insertion = new int[2];

        // What should insertion[0] be?
        insertion[0] = start;
        if (start > intervals[0][0]) {
            for (int i=0; i<intervals.length; i++) {
                if (start >= intervals[i][0] && start <= intervals[i][1]) {
                    insertion[0] = intervals[i][0];
                    break;
                }
            }
        }

        // What should insertion[1] be?
        insertion[1] = stop;
        if (stop < intervals[intervals.length-1][1]) {
            for (int i=0; i<intervals.length; i++) {
                if (stop >= intervals[i][0] && stop <= intervals[i][1]) {
                    insertion[1] = intervals[i][1];
                    break;
                }
            }
        }

        // Place insertion
        boolean added = false;
        List<Integer[]> result = new ArrayList<>();
        for (int i=0; i<intervals.length; i++) {
            if (insertion[0] <= intervals[i][0]) {
                addToList(insertion, result);
                added = true;
                while (i<intervals.length && intervals[i][1] <= insertion[1]) {
                    i++;
                }
                copyRest(i, intervals, result);
                break;
            } else {
                addToList(intervals[i], result);
            }
        }
        if (!added) {
            // add at the end
            addToList(insertion, result);
        }

        // List to arrays
        int[][] array = new int[result.size()][2];
        for (int i=0; i<result.size(); i++) {
            array[i][0] = result.get(i)[0];
            array[i][1] = result.get(i)[1];
        }
        return array;
    }

    private void copyRest(int from, int[][] intervals, List<Integer[]> list) {
        for (int i=from; i<intervals.length; i++) {
            addToList(intervals[i], list);
        }
    }

    private void addToList(int[] interval, List<Integer[]> list) {
        Integer[] pair = new Integer[2];
        pair[0] = interval[0];
        pair[1] = interval[1];
        list.add(pair);
    }
}
