//
// Created by ezlinho on 6/3/2019.
//

#ifndef C___MYLINKEDLIST_H
#define C___MYLINKEDLIST_H


class MyLinkedList {
public:

    MyLinkedList() {

    }

    int get(int index) {
        if (index < 0 || index >= len) return -1;
        auto tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp->next;
        }
        return tmp->val;

    }

    void addAtHead(int val) {
        if (head == nullptr) {
            head = tail = new Node(val);
        } else {
            auto t = new Node(val);
            t->next = head;
            head = t;
        }
        len++;

    }

    void addAtTail(int val) {
        if (tail == nullptr) {
            head = tail = new Node(val);
        } else {
            auto t = new Node(val);
            tail->next = t;
            tail = tail->next;
        }
        len++;
    }

    void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
        } else if (len == index) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else if (index > len) {
            return;
        } else {
            auto tmp = head;
            for (int i = 1; i < index; i++) {
                tmp = tmp->next;
            }
            auto t = new Node(val);
            t->next = tmp->next;
            tmp->next = t;
            len++;
        }
    }

    void deleteAtIndex(int index) {
        if (index >= len) {
            return;
        } else if (index < 0) {
            return;
        }
        if (index == 0) {
            auto tmp = head->next;
            delete head;
            head = tmp;
        } else {
            auto tmp = head;
            for (int i = 1; i < index; i++) {
                tmp = tmp->next;
            }
            auto t = tmp->next;
            tmp->next = t->next;
            delete t;
            if (index == len - 1) {
                tail = tmp;
            }
        }
        len--;
        if (len == 0) {
            tail = nullptr;
        }
    }

private:
    class Node {
    public:
        Node *next;
        int val;

        Node(int v) : val(v) { next = nullptr; }
    };

    Node *head = nullptr;
    Node *tail = nullptr;
    int len = 0;
};


#endif //C___MYLINKEDLIST_H
