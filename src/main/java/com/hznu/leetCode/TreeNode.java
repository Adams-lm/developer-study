package com.hznu.leetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LIN
 * @date 2022/3/11 20:05
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Soluton {
    public static void main(String[] args) {
        String[] m = {"123", "456", "789"};
        String[] n = {"123", "234", "456", "567"};
        System.out.println(findSame(m, n));
    }

    private static List<String> findSame(String[] m, String[] n) {
        int minLength = Math.min(m.length, n.length);
        List<String> same = new LinkedList<String>();
        int i = 0, j = 0;
        while (j < minLength && i < minLength) {
            if (m[i].equals(n[j])) {
                same.add(m[i]);
                i++;
                j++;
            } else if (Integer.parseInt(m[i]) < Integer.parseInt(n[j])) {
                i++;
            } else {
                j++;
            }
        }
        return same;
    }


}

