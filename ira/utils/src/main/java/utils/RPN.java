package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: timofeevdv Date: 06.02.2017
 */
public class RPN {
	private static Map<Character, Integer> operators;

	static {
		operators = new HashMap<>();
		operators.put('(', 1);
		operators.put(')', 1);
		operators.put('+', 2);
		operators.put('-', 2);
		operators.put('*', 3);
		operators.put('/', 3);
	}

	public static String make(String expression) {
		checkExpression(expression);
		Stack<Character> stack = new Stack<>();
		StringBuilder result = new StringBuilder();
		for (Character c : expression.toCharArray()) {
			// Скобки
			if (c == '(') {
				stack.push(c);
				continue;
			}
			if (c == ')') {
				while (stack.size() > 0) {
					Character op = stack.pop();
					if (op != '(') {
						result.append(op);
					}
				}
				continue;
			}
			// Операнды
			if (operators.get(c) == null) {
				result.append(c);
				continue;
			}

			while (stack.size() > 0 && operators.get(stack.peek()) >= operators.get(c)) {
				result.append(stack.pop());
			}
			stack.push(c);
		}
		while (stack.size() > 0) {
			result.append(stack.pop());
		}

		return result.toString();
	}

	private static void checkRPNExpression(String expression) {
		Pattern pattern = Pattern.compile("[0-9\\+\\-*/]+");
		Matcher matcher = pattern.matcher(expression);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Неизвестный оператор в выражении " + expression);
		}
	}

	private static void checkExpression(String expression) {
		if (StringUtils.countMatches(expression, "(") != StringUtils.countMatches(expression, ")")) {
			throw new IllegalArgumentException("Некорректное выражение");
		}
	}

	/**
	 * Вычисляет выражение, записанное в обратной польской записи
	 *
	 * @param expression
	 *            выражение, записанное в обратной польской записи
	 * @return Результат вычислений
	 */
	public static Double calculate(String expression) {
		checkRPNExpression(expression);
		Stack<Double> stack = new Stack<>();
		for (Character c : expression.toCharArray()) {
			if (operators.get(c) == null) {
				stack.push(new Double(Character.digit(c, 10)));
			} else {
				Double var2 = stack.pop();
				Double var1 = stack.pop();
				Double result = evaluate(var1, var2, c);
				stack.push(result);
			}
		}
		return stack.pop();
	}

	/**
	 * Выполняет арифметическую операцию
	 * 
	 * @param var1
	 * @param var2
	 * @param operator
	 * @return
	 */
	private static Double evaluate(Double var1, Double var2, Character operator) {
		switch (operator) {
		case '+':
			return var1 + var2;
		case '-':
			return var1 - var2;
		case '*':
			return var1 * var2;
		case '/':
			return var1 / var2;
		default:
			throw new IllegalArgumentException("Неизвестный оператор: " + operator);
		}
	}
}
