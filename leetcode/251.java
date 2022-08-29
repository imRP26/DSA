import java.util.*;

/*
 * https://leetcode.com/problems/flatten-2d-vector/
 */



/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// My Naive Approach
class Vector2D2 {

	List<Integer> array;
	int index;

	Vector2D2(int[][] vec) {
		array = new ArrayList<>();
		index = 0;
		for (int i = 0; i < vec.length; i++) {
			for (int j = 0; j < vec[i].length; j++)
				array.add(vec[i][j]);
		}
	}
	
	public int next() {
		index++;
		return array.get((index - 1));
	}
	
	public boolean hasNext() {
		return index < array.size();
	}
}



// Another (Better?) Approach
class Vector2D1 {

	int[][] v;
	int i, j;
	
	public Vector2D1(int[][] v) {
		this.v = v;
		i = 0;
		j = 0;
	}
	
	public int next() {
		if (hasNext())
			return this.v[i][j++];
		return -1;
	}
	
	public boolean hasNext() {
		while (i < this.v.length && j == this.v[i].length) {
			i++;
			j = 0;
		}
		return i < this.v.length;
	}
}



// 
