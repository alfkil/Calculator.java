import java.util.Scanner;  // Import the Scanner class

class Calculator {
    enum Operator {
        Plus,
        Minus,
        Division,
        Multiply,
        Initial,
        Expect
    };
    private String string;
    private int index = 0;
    public Calculator(String string) {
        this.string = string;
    }
    private double safeDouble() {
        int i = index;
        while(index < string.length() && (string.charAt(index) >= '0' && string.charAt(index) <= '9' || string.charAt(index) == '.'))
            index++;
        return Double.valueOf(string.substring(i, index--));
    }
    public double getResult() {
        double result = 0.0;
        Operator operator = Operator.Initial;
        while(index < string.length()) {
            if(string.charAt(index) == '(') {
                index++;
                double current = getResult();
                switch(operator) {
                    case Expect:
                        System.out.println("Expected operator");
                        break;
                    case Plus:
                        result += current;
                        break;
                    case Minus:
                        result -= current;
                        break;
                    case Division:
                        result /= current;
                        break;
                    case Multiply:
                        result *= current;
                        break;
                    case Initial:
                        result = current;
                        break;
                }
                operator = Operator.Expect;
            }
            if(string.charAt(index) == '+')
                operator = Operator.Plus;
            else if(string.charAt(index) == '-')
                operator = Operator.Minus;
            else if(string.charAt(index) == '/')
                operator = Operator.Division;
            else if(string.charAt(index) == '*')
                operator = Operator.Multiply;
            else if (string.charAt(index) >= '0' && string.charAt(index) <= '9') {
                double current = safeDouble();
                switch(operator) {
                    case Expect:
                        System.out.println("Expected operator");
                        break;
                    case Plus:
                        result += current;
                        break;
                    case Minus:
                        result -= current;
                        break;
                    case Division:
                        result /= current;
                        break;
                    case Multiply:
                        result *= current;
                        break;
                    case Initial:
                        result = current;
                        break;
                }
                operator = Operator.Expect;
            } else if (string.charAt(index) == ')') {
                index++;
                return result;
            } else if(string.charAt(index) == ' ') {
                //skip
            } else System.out.println("Unknown character");
            index++;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter string to process:");
        Calculator calculator = new Calculator(keyboard.nextLine());

        System.out.println("Result : " + calculator.getResult());
   }
}