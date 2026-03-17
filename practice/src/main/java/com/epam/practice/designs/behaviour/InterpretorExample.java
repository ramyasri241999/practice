package com.epam.practice.designs.behaviour;
/*
 * Interpreter Pattern defines a representation for a grammar and provides an interpreter to evaluate sentences in that grammar.
 * 
 */
public class InterpretorExample {
	public static void main(String[] args) {
	Expression a = new NumberExpression(10);
	Expression b = new NumberExpression(20);
	Expression c = new AddExpression(a,b);
	System.out.println(c.interpret());
	
	
	Expression expr =
		    new AddExpression(
		        new AddExpression(
		            new NumberExpression(10),
		            new NumberExpression(5)
		        ),
		        new NumberExpression(3)
		    );

		System.out.println(expr.interpret());
	}
}

interface Expression {
    int interpret();
}

class NumberExpression implements Expression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

class AddExpression implements Expression {

    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

/*
 * Pitfalls / Attacks
Class Explosion

Complex grammar leads to many classes.

Example:

AddExpression
SubtractExpression
MultiplyExpression
DivideExpression

Large interpreters become huge.

Performance Issues

Recursive evaluation may be slow.

Large interpreters usually use parsers instead.
*/

/*
 * Best Practices

✔ Use interpreter only for simple grammars
✔ Avoid for complex languages
✔ Combine with Parser pattern if grammar grows
*/
