package org.neetcode.linkedlist;

class P7_AddTwoNums {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode resHead = res;
        int carry = 0;

        while (true) {
            int val1 = 0;
            if(l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }
            int val2 = 0;
            if(l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }

            int sum = val1 + val2 + carry;
            if(sum > 9) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }

            res.val = sum;

            if(l1 != null || l2 != null) {
                res.next = new ListNode();
                res = res.next;
            } else {
                if(carry > 0) {
                    res.next = new ListNode(carry);
                }
                break;
            }
        }

        return resHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

//        ListNode l1 = new ListNode(9);
//        ListNode l2 = new ListNode(8);

        System.out.println(ListNode.print(
                new P7_AddTwoNums().addTwoNumbers(l1, l2)));
    }
}
