package org.mrbriefcase;

/*
* Reverse Polish Notation calculator
*
* Implmentation of Reverse Polish Notation calculator that can handle addition, subtraction,
* multiplication, division and cosine (input radians). The operands can be integers or real
* numbers and the operators can be symbols or text.
*
* Implementation details:
*   - OS: Ubuntu 18
*   - Java: 11
*   - compile: javac rpn.java
*   - run: java rpn
*     - examples: java rpn 3 3 2 '*' +
*                 java rpn 3 3 2 m +
*                 java rpn 3 cos 
*                 java rpn 1 3 cos +
* 
* @param  operands  integers or real numbers
* @param  operators +, -, *, /, cos (also respitively add, subtract, multiply, divide, cosine)
* @return the value of the operation
*/

import java.util.regex.Pattern;
import java.lang.Math;


class rpn {
  public static void main(String args[]) {
    rpn rpn = new rpn();
    rpn.calculate(args);
  }
      
  public String calculate(String args[]) {

    // valid operands and operations
    Pattern operandPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    Pattern operationPattern = Pattern.compile("(\\+|-|\\*|\\/|a|s|m|d|add|sub|mul|div|cos)", Pattern.CASE_INSENSITIVE);
    Pattern twoArgumentOperationPattern = Pattern.compile("(\\+|-|\\*|\\/|a|s|m|d|add|sub|mul|div)", Pattern.CASE_INSENSITIVE);

    double output = 0.0;
    String formatttedOuput = "";
    String errorMessage = "";
    String errorCode = "";
    
    // variables used in the processing
    boolean processingOperators = false;
    int numberOfArguments = args.length;
    int nextArgumentPosition = 0;

    // if no arguments, then print help
    if (args.length == 0) {
      errorCode = "ERR01";
      errorMessage = "(" + errorCode + ") ERROR: No arguments provided";
      printHelp(errorMessage);
    } else if (args.length == 1) {
      errorCode = "ERR06";
      errorMessage = "(" + errorCode + ") ERROR: Not enough operands and/or operators";
      printHelp(errorMessage);
    } else {
      // processing loop, processing operands and operations from the command line
      for (int argumentPosition = 0; argumentPosition < numberOfArguments; argumentPosition++) {
        if (operandPattern.matcher(args[argumentPosition]).matches()) {
          // this captures when a user gives numbers after operators, 3 3 + 3 +
          if (processingOperators) {
            int position = argumentPosition + 1;
            errorCode = "ERR05";
            errorMessage = "(" + errorCode + ") ERROR: Invalid argument order at position " + position + " with value " + args[argumentPosition];
            printHelp(errorMessage);
            break;
          }
        } else if (operationPattern.matcher(args[argumentPosition]).matches()) {
          // start processing/operating

          // this captures the first operation
          if (!processingOperators) {
            if (args.length > 1) {
              // this will grab the last operand to start the operating
              output = Double.parseDouble(args[argumentPosition - 1]);
              nextArgumentPosition = argumentPosition - 1;
            }
            processingOperators = true;
          }

          // performing the operations
          String operator = args[argumentPosition].toLowerCase();

          // check to make sure we still have operands for 2 argument operations
          if (twoArgumentOperationPattern.matcher(operator).matches()) {
            nextArgumentPosition -= 1;
            if (nextArgumentPosition < 0) {
              errorCode = "ERR04";
              errorMessage = "(" + errorCode + ") ERROR: Not enough operands to operator on";
              printHelp(errorMessage);
              break;
            }
          }

          // perform the operation
          switch(operator) {
            case "+":
            case "a":
            case "add":
              output = Double.parseDouble(args[nextArgumentPosition]) + output;
              break;
            case "-":
            case "s":
            case "sub":
              output = Double.parseDouble(args[nextArgumentPosition]) - output;
              break;
            case "*":
            case "m":
            case "mul":
              output = Double.parseDouble(args[nextArgumentPosition]) * output;
              break;
            case "/":
            case "d":
            case "div":
              // check for division by zero
              if (output == 0) {
                errorCode = "ERR08";
                errorMessage = "(" + errorCode + ") ERROR: Cannot divide by zero";
                printHelp(errorMessage);
              } else {
                output = Double.parseDouble(args[nextArgumentPosition]) / output;
              }
              break;
            case "cos":
              output = Math.cos(output);
              break;
            default:
              break;
          }
        } else {
          // this captures and exits when invalid arguments or operators are given by the user
          int position = argumentPosition + 1;
          errorCode = "ERR03";
          errorMessage = "(" + errorCode + ") ERROR: Invalid argument or operator in position " + position + " with value " + args[argumentPosition];
          printHelp(errorMessage);
        }
        
        // break out of loop if an error has occurred 
        if (errorCode.length() > 0) {
          break;
        }
      } // for loop, argument processing

      // this checks to see if we received an operator, if so, show the output, else show an error
      if (processingOperators) {
        // this prints the output, cleaning up the value to look more "integer-ish", ie, 7.0 -> 7
        formatttedOuput = Double.toString(output).replaceAll("\\.0*$", "");
      } else {
        // check for error occurred in processing loop, if not, then present this error
        if (errorCode.length() == 0) {
          // this captures and exits when invalid arguments or operators are given by the user
          errorCode = "ERR02";
          errorMessage = "(" + errorCode + ") ERROR: No operators were present";
          printHelp(errorMessage);
        }
      }      
    } // argument processing
    
    // this captures when a user provides too many operands, 3 3 3 +
    if ((errorCode.length() == 0) && (nextArgumentPosition > 0)) {
      errorCode = "ERR07";
      errorMessage = "(" + errorCode + ") ERROR: Too many operands were present";
      printHelp(errorMessage);
    }

    // if all has gone well, display result
    if (errorCode.length() == 0) {
      System.out.println(formatttedOuput);
    }

    // return the error code or result
    return((errorCode.length() > 0) ? errorCode : formatttedOuput);
  } // end main

  private String printHelp(String errorMessage) {
    if (errorMessage.length() > 0) {
      System.out.println("\n" + errorMessage);
    }

    System.out.println("");
    System.out.println("usage: rpn [operand] ... [operator] ...");
    System.out.println("  operand types: integer, real");
    System.out.println("  operations: +, a, add, -, s, sub, '*', m, mul, /, d, div, cos");
    System.out.println("        note: you may need to escape the multiplication symbol with single quotes ('*'), double quotes (\"*\") or a backslash (\\*)");
    System.out.println("");
    System.out.println("  example: 3 4 +");
    System.out.println("   output: 7");
    System.out.println("");

    return("");
  }

}
