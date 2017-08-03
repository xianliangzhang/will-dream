package top.kou.dream.pattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZXL on 2017/8/2.
 */
public class InterpreterPattern {
    String constant = "3+(5-3)*2/(4-2)";

    static class Context {
        String expression;
        int cursor = 0;
        List<CalculatorExpression> expressions = new LinkedList<>();

        Context(String expression) {
            this.expression = expression;
            this.interpret(expression);
        }

        void interpret(String expression) {
            if (expression != null && expression.isEmpty()) {
                int cursor = 0;
                while (cursor < expression.length()) {
                    int leftBracketCount = 0;
                    int rightBracketCount = 0;

                    switch (expression.charAt(cursor)) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            break;
                        case '(':
                            leftBracketCount++;
                        case ')':
                            rightBracketCount++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    enum Operator {ADD, SUB, MUL, DIV}
    enum OperatorPriority {NONE, SINGLE_OPERAND, BRACKET, MUL_DIV, ADD_SUB}
    interface CalculatorExpression {
        OperatorPriority getOperatorPriority();
        Operator getOperator();
        CalculatorExpression getLeftOperand();
        CalculatorExpression getRightOperand();
        int interpret();
    }

    static abstract class AbstractCalculatorExpression implements CalculatorExpression {
        private Operator operator;
        private OperatorPriority operatorPriority;
        private CalculatorExpression leftOperand;
        private CalculatorExpression rightOperand;

        public OperatorPriority getOperatorPriority() {
            return OperatorPriority.NONE;
        }

        public Operator getOperator() {
            return this.operator;
        }

        public CalculatorExpression getLeftOperand() {
            return this.leftOperand;
        }

        public CalculatorExpression getRightOperand() {
            return this.rightOperand;
        }
    }

    static class AddCalculatorExpression extends AbstractCalculatorExpression {

        @Override
        public int interpret() {
            return getLeftOperand().interpret() + getRightOperand().interpret();
        }
    }

    static class SubCalculatorExpression extends AbstractCalculatorExpression {

        @Override
        public int interpret() {
            return getLeftOperand().interpret() - getRightOperand().interpret();
        }
    }

    public static void main(String[] args) {
        String expression = "3+3-2";
        Context context = new Context(expression);

    }
}
