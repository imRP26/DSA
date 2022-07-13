/*
 * We've got a set of intervals and we need the following ops to be implemented 
 * efficiently :- 
 	(1) Add an interval.
 	(2) Remove an interval.
 	(3) Given an interval x, find if x overlaps with any of the existing intervals.
 * The solution is to use an Interval Tree, i.e., augment a self-balancing BST 
 * like RB Tree, AVL Tree, etc. to maintain set of intervals so that all operations 
 * can be done in O(log N) time.
 * Each node of an interval tree contains the following info. :- 
 	(1) i :- An interval represented as a pair [low, high].
 	(2) max :- maximum high value in subtree rooted with this node.
 * The low value of an interval is used as key to maintain order in the BST.
*/
#include <bits/stdc++.h>
using namespace std;

struct interval {
	int low, high;
};

struct intervalTreeNode {
	interval *i;
	int max;
	intervalTreeNode *left, *right;
};

intervalTreeNode* newNode(interval i) {
	intervalTreeNode* temp = new intervalTreeNode;
	temp->i = new interval(i);
	temp->max = i.high;
	temp->left = temp->right = NULL;
	return temp;
}

intervalTreeNode *insertion(intervalTreeNode* root, interval i1) {
	if (root == NULL)
		return newNode(i);
	int l = root->i->low;
	if (i1.low < l)
		root->left = insertion(root->left, i1);
	else
		root->right = insertion(root->right, i1);
	if (root->max < i1.high)
		root->max = i1.high;
	return root;
}

bool checkForOverlap(interval i1, interval i2) {
	if (i1.low <= i2.high && i2.low <= i1.high)
		return true;
	return false;
}

interval* overlapSearch(intervalTreeNode* root, interval i1) {
	if (root == NULL)
		return NULL;
	if (checkForOverlap(*(root->i), i1))
		return root->i;
	if (root->left != NULL && root->left->max >= i.low)
		return overlapSearch(root->left, i);
	return overlapSearch(root->right, i);
}

void inorder(intervalTreeNode* root) {
	if (root == NULL)
		return;
	inorder(root->left);
	cout << '[' << root->i->low << ", " << root->i->high << ']' << " max = " << root->max << '\n';
	inorder(root->right);
}

int main() {
	interval intervals = {{15, 20}, {10, 30}, {17, 19}, {5, 20}, {12, 15}, {30, 40}};
	int numIntervals = sizeof(intervals) / sizeof(intervals[0]);
	intervalTreeNode* root = NULL;
	for (int i = 0; i < n; i++)
		root = insertion(root, intervals[i]);
	cout << "Inorder Traversal of the constructed Interval Tree :- ";
	inorder(root);
	interval x = {6, 7};
	cout << "\nSearching for interval [" << x.low << ", " << x.high << ']';
	interval* answer = overlapSearch(root, x);
	if (answer == NULL)
		cout << "\nNo Overlapping interval!\n";
	else
		cout << "\nOverlaps with [" << answer->low << ", " << answer->high << ']';
}