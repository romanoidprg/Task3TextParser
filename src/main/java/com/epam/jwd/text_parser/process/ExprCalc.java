package com.epam.jwd.text_parser.process;

import com.epam.jwd.text_parser.text_elements.TextRegexes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExprCalc {

    private static final Map<String, Integer> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put("~", 4);
        OPERATORS.put("<<", 3);
        OPERATORS.put(">>", 3);
        OPERATORS.put(">>>", 3);
        OPERATORS.put("&", 2);
        OPERATORS.put("^", 1);
        OPERATORS.put("|", 0);
    }

    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private static int cmpPrecedence(String token1, String token2) {
        return OPERATORS.get(token1) - OPERATORS.get(token2);
    }

    public static String[] infixToRPN(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        for (String token : inputTokens) {
            if (isOperator(token)) {
                while (!stack.empty() && isOperator(stack.peek())) {
                    if (cmpPrecedence(token, stack.peek()) <= 0) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            }
            else if (token.equals("(")) {
                stack.push(token);  //
            }
            else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            else {
                out.add(token);
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    public static int RPNtoInt(String[] tokens) {
        Stack<String> stack = new Stack<String>();

        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                int result = 0, operand1, operand2;

                if (token.equals("~")) {
                    operand1 = Integer.parseInt(stack.pop());
                    result = ~operand1;
                } else {
                    operand2 = Integer.parseInt(stack.pop());
                    operand1 = Integer.parseInt(stack.pop());
                    switch (token) {
                        case "<<":
                            result = operand1 << operand2;
                            break;
                        case ">>":
                            result = operand1 >> operand2;
                            break;
                        case ">>>":
                            result = operand1 >>> operand2;
                            break;
                        case "|":
                            result = operand1 | operand2;
                            break;
                        case "&":
                            result = operand1 & operand2;
                            break;
                        case "^":
                            result = operand1 ^ operand2;
                            break;
                    }
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static String bitCalc(String expr) {
        if (!expr.matches(TextRegexes.EXPRESSION_REGEX)) {
            return expr;
        } else {
            return String.valueOf(RPNtoInt(infixToRPN(exprToTokens(expr))));
        }
    }

    private static String[] exprToTokens(String expr) {
        List<String> tokens = new ArrayList<>();
        boolean isNum = false;
        boolean isOffs = false;
        StringBuilder num = new StringBuilder();
        StringBuilder offs = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            String s = String.valueOf(expr.charAt(i));
            if (s.matches("[0-9]")) {
                if (isOffs) {
                    tokens.add(offs.toString());
                    offs = new StringBuilder();
                }
                isOffs = false;
                num.append(s);
                isNum = true;
            } else if (s.matches("[><]")) {
                if (isNum) {
                    tokens.add(num.toString());
                    num = new StringBuilder();
                }
                isNum = false;
                offs.append(s);
                isOffs = true;
            } else {
                if (isNum) {
                    tokens.add(num.toString());
                    num = new StringBuilder();
                }
                if (isOffs) {
                    tokens.add(offs.toString());
                    offs = new StringBuilder();
                }
                isNum = false;
                isOffs = false;
                tokens.add(s);
            }
            if (i == expr.length()-1){
                if (isNum) {
                    tokens.add(num.toString());
                }
                if (isOffs) {
                    tokens.add(offs.toString());
                }
            }
        }
        return tokens.toArray(new String[0]);
    }
}
