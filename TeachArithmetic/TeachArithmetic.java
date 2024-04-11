public class TeachArithmetic {
    private static int firstNumber;
    private static int secondNumber;
    private static int operation;
    private static int result;
    private static int maxX;
    private static int x;
 
    public void generateOperation() {
       operation = (int)Math.floor(Math.random() * 4.0D + 1.0D);
    }
 
    public void generateNumbers() {
       switch(operation) {
       case 1:
          firstNumber = (int)Math.floor(Math.random() * 100.0D + 0.0D);
          maxX = 100 - firstNumber - 1;
          secondNumber = (int)Math.floor(Math.random() * (double)(maxX - 0 + 1) + 0.0D);
          result = firstNumber + secondNumber;
          break;
       case 2:
          firstNumber = (int)Math.floor(Math.random() * 100.0D + 0.0D);
          maxX = 99 / firstNumber;
          secondNumber = (int)Math.floor(Math.random() * (double)(maxX - 0 + 1) + 0.0D);
          result = firstNumber * secondNumber;
          break;
       case 3:
          firstNumber = (int)Math.floor(Math.random() * 100.0D + 0.0D);
          secondNumber = (int)Math.floor(Math.random() * (double)(firstNumber - 0 + 1) + 0.0D);
          result = firstNumber - secondNumber;
          break;
       case 4:
          secondNumber = (int)Math.floor(Math.random() * 100.0D + 1.0D);
          if (secondNumber == 1) {
             firstNumber = (int)Math.floor(Math.random() * 100.0D + 1.0D);
          } else if (secondNumber > 49) {
             firstNumber = secondNumber;
          } else {
             maxX = 99 / secondNumber;
             x = (int)Math.floor(Math.random() * (double)(maxX + 1) + 1.0D);
             firstNumber = secondNumber * x;
          }
 
          result = firstNumber / secondNumber;
       }
 
    }
 
    public int getOperand() {
       return operation;
    }
 
    public int getFirstNumber() {
       return firstNumber;
    }
 
    public int getSecondNumber() {
       return secondNumber;
    }
 
    public int getResult() {
       return result;
    }
 
    public void generateProblem() {
       this.generateOperation();
       this.generateNumbers();
    }
 }