import java.util.*;

/*
 * https://leetcode.com/problems/my-calendar-i/
 */



/*
 * Naive Solution -> Check each booking 1-by-1 and see if the booking being 
 * considered currently is viable or not.
 * TC = O(N * N), SC = O(N)
 */
class MyCalendar1 {

    List<int[]> booking;
    
    public MyCalendar1() {
        this.booking = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        if (!this.booking.isEmpty()) {
            for (int i = 0; i < this.booking.size(); i++) {
                int temp1 = Math.max(start, this.booking.get(i)[0]), temp2 = Math.min(end, this.booking.get(i)[1]);
                if (temp1 < temp2)
                    return false;
            }    
        }
        this.booking.add(new int[] {start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */



/*
 * 
 */
