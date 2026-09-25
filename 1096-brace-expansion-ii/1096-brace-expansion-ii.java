import java.util.*;

class Solution {
    private String expr;
    private int i = 0;

    public List<String> braceExpansionII(String expression) {
        expr = expression;
        Set<String> result = parseExpression();
        return new ArrayList<>(result);
    }

    // Handles union: a,b
    private Set<String> parseExpression() {
        Set<String> res = parseTerm();

        while (i < expr.length() && expr.charAt(i) == ',') {
            i++; // skip ','
            res.addAll(parseTerm());
        }

        return res;
    }

    // Handles concatenation: ab or a{b,c}
    private Set<String> parseTerm() {
        Set<String> res = new TreeSet<>();
        res.add("");

        while (i < expr.length()
                && expr.charAt(i) != '}'
                && expr.charAt(i) != ',') {

            Set<String> next = new TreeSet<>();

            if (expr.charAt(i) == '{') {
                i++; // skip '{'
                next = parseExpression();
                i++; // skip '}'
            } else {
                next.add(String.valueOf(expr.charAt(i)));
                i++;
            }

            Set<String> temp = new TreeSet<>();
            for (String a : res) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }
            res = temp;
        }

        return res;
    }
}