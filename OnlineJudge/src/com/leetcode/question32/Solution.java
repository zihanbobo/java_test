package com.leetcode.question32;

import java.util.Stack;

//http://www.bubuko.com/infodetail-276831.html
public class Solution {
	public static int longestValidParentheses(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int start = 0;
		int maxLen = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					// record the position before first left parenthesis
					start = i + 1;
				} else {
					stack.pop();
					// if stack is empty mean the positon before the valid left
					// parenthesis is "last"
					if (stack.isEmpty()) {
						maxLen = Math.max(i - start + 1, maxLen);
					} else {
						// if stack is not empty, then for current i the longest
						// valid parenthesis length is
						// i-stack.peek()
						maxLen = Math.max(i - stack.peek(), maxLen);
					}
				}
			}
		}
		return maxLen;
	}
	
	public static void main(String[] args) {
		String s = "(()((())";
		int len = new Solution().longestValidParentheses(s);
		System.out.println(len);
		
		
	}
}
