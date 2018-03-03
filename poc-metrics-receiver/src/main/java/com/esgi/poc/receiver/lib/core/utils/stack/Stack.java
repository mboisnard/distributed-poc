package com.esgi.poc.receiver.lib.core.utils.stack;

import java.util.Map;

abstract class Stack<A, B> {

    Map<A, B> stack;

    void push(A a, B b) {
        stack.put(a, b);
    }
}
