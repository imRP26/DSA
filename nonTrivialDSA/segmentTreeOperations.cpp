/*
 * For any doubts, refer to the following CodeMonk tutorial :- 
 * https://www.hackerearth.com/practice/notes/segment-tree-and-lazy-propagation/
 */


int n;
int arr[n];
int tree[2 * n - 1];
int lazy[2 * n - 1];


void build(int node, int low, int high) {
	if (low == high)
		tree[node] = arr[low];
	else {
		int mid = start + (end - start) / 2;
		build(2 * node + 1, low, mid);
		build(2 * node + 2, mid + 1, high);
		tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
	}
}


void update(int node, int low, int high, int index, int val) {
	if (low == high) {
		arr[index] += val;
		tree[node] += val;
	}
	else {
		int mid = low + (high - low) / 2;
		if (low <= index && index <= mid)
			update(2 * node + 1, low, mid, index, val);
		else
			update(2 * node + 2, mid + 1, high, index, val);
		tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
	}
}


void updateRange(int node, int low, int high, int left, int right, int val) {
	if (low > high || low > right || high < left)
		return;
	if (low == high) {
		tree[node] += val;
		return;
	}
	int mid = low + (high - low) / 2;
	updateRange(2 * node + 1, low, mid, left, right, val);
	updateRange(2 * node + 2, mid + 1, high, left, right, val);
	tree[node] += tree[2 * node + 1] + tree[2 * node + 2];
}


void lazyRangeUpdation(int node, int low, int high, int left, int right, int val) {
	if (laxy[node] != 0) {
		tree[node] += (high - low + 1) * lazy[node];
		if (low != high) {
			lazy[2 * node + 1] += lazy[node];
			lazy[2 * node + 2] += lazy[node];
		}
		lazy[node] = 0;
	}
	if (low > right || high < left)
		return;
	if (low >= left && high <= right) {
		tree[node] += (high - low + 1) * val;
		if (low != high) {
			lazy[2 * node + 1] += val;
			lazy[2 * node + 2] += val;
		}
		return;
	}
	int mid = low + (high - low) / 2;
	lazyRangeUpdation(2 * node + 1, low, mid, left, right, val);
	lazyRangeUpdation(2 * node + 2, mid + 1, high, left, right, val);
	tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
}


int query(int node, int low, int high, int left, int right) {
	if (right < low || high < left)
		return 0;
	if (left <= low && high <= right)
		return tree[node];
	int mid = low + (high - low) / 2;
	int q1 = query(2 * node + 1, low, mid, left, right);
	int q2 = query(2 * node + 2, mid + 1, high, left, right);
	return (q1 + q2);
}


int lazyQueryRange(int node, int low, int high, int left, int right) {
	if (low > high || low > right || high < left)
		return 0;
	if (lazy[node] != 0) {
		tree[node] += (high - low + 1) * lazy[node];
		if (low != high) {
			lazy[2 * node + 1] += lazy[node];
			lazy[2 * node + 2] += lazy[node];
		}
		lazy[node] = 0;
	}
	if (low >= left && high <= right)
		return tree[node];
	int mid = low + (high - low) / 2;
	int q1 = lazyQueryRange(2 * node + 1, low, mid, left, right);
	int q2 = lazyQueryRange(2 * node + 2, mid + 1, high, left, right);
	return (q1 + q2);
}
