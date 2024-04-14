import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TeachArithmeticFrame extends JFrame implements MouseListener {
   private static final long serialVersionUID = 8460787337067804933L;
   private static boolean isWaitingForAnswer;
   private static boolean currentProblemAnswered;
   private boolean hintShown;
   private static String comment;
   private static String command;
   private static int inputAnswer;
   private static double encounteredProblems = 0.0D;
   private static double solvedProblems;
   private double score;
   private static Container container;
   private static Label problemLabel;
   private static JLabel responseLabel;
   private static JLabel statsScoreLabel;
   private static JLabel numberOfEncouteredProblemsLabel;
   private static JLabel numberOfSolvedProblemsLabel;
   private static JLabel commentLabel;
   private static JLabel scoreLabel;
   private static JTextField answerTextField;
   private static JLabel start_okLabel;
   private static JLabel nextLabel;
   private static JLabel hintLabel;
   private static JLabel quitLabel;
   private static String fontName = "Roboto Thin"; //Enter font here
   private static ImageIcon correctSign;
   private static ImageIcon wrongSign;
   private static DecimalFormat df;
   private static int buttonLength = 100;
   private static int buttonWidth = 40;
   private static int start_okX = 200;
   private static int start_okY = 600;
   private static int nextX = 1000;
   private static int nextY = 600;
   private static int hintX = 683 - buttonLength / 2;
   private static int hintY = 600;
   private static int quitX = 683 - buttonLength / 2;
   private static int quitY = 600 + buttonWidth + 20;
   private static TeachArithmetic teachArithmetic;

   public TeachArithmeticFrame() {
      currentProblemAnswered = false;
      command = "start";
      df = new DecimalFormat("0");
      container = this.getContentPane();
      problemLabel = new Label("");
      responseLabel = new JLabel();
      statsScoreLabel = new JLabel();
      numberOfEncouteredProblemsLabel = new JLabel();
      numberOfSolvedProblemsLabel = new JLabel();
      commentLabel = new JLabel();
      scoreLabel = new JLabel();
      answerTextField = new JTextField();
      start_okLabel = new JLabel(command);
      nextLabel = new JLabel("Next");
      hintLabel = new JLabel("Hint");
      quitLabel = new JLabel("Quit");
      correctSign = new ImageIcon("Correct.png");
      wrongSign = new ImageIcon("Wrong.png");
      teachArithmetic = new TeachArithmetic();
      answerTextField.setBounds(1000, 20, 300, 80);
      problemLabel.setBounds(10, 10, 500, 100);
      responseLabel.setBounds(413, 350, 500, 170);
      statsScoreLabel.setBounds(1000, 180, 300, 40);
      numberOfEncouteredProblemsLabel.setBounds(1000, 220, 300, 40);
      numberOfSolvedProblemsLabel.setBounds(1000, 260, 300, 40);
      scoreLabel.setBounds(1000, 300, 300, 40);
      commentLabel.setBounds(1000, 340, 300, 40);
      start_okLabel.setBounds(start_okX, start_okY, buttonLength, buttonWidth);
      nextLabel.setBounds(nextX, nextY, buttonLength, buttonWidth);
      hintLabel.setBounds(hintX, hintY, buttonLength, buttonWidth);
      quitLabel.setBounds(quitX, quitY, buttonLength, buttonWidth);
      problemLabel.setForeground(Color.WHITE);
      problemLabel.setFont(new Font(fontName, 0, 70));
      start_okLabel.setOpaque(true);
      start_okLabel.setBackground(new Color(50, 50, 50));
      start_okLabel.setHorizontalAlignment(0);
      start_okLabel.setVerticalAlignment(0);
      start_okLabel.setFont(new Font(fontName, 0, 15));
      start_okLabel.setForeground(Color.BLACK);
      hintLabel.setHorizontalAlignment(0);
      hintLabel.setVerticalAlignment(0);
      hintLabel.setFont(new Font(fontName, 0, 15));
      hintLabel.setForeground(Color.BLACK);
      nextLabel.setHorizontalAlignment(0);
      nextLabel.setVerticalAlignment(0);
      nextLabel.setFont(new Font(fontName, 0, 15));
      nextLabel.setForeground(Color.BLACK);
      quitLabel.setHorizontalAlignment(0);
      quitLabel.setVerticalAlignment(0);
      quitLabel.setFont(new Font(fontName, 0, 15));
      quitLabel.setForeground(Color.BLACK);
      responseLabel.setFont(new Font(fontName, 0, 40));
      responseLabel.setVerticalTextPosition(1);
      responseLabel.setHorizontalTextPosition(0);
      responseLabel.setHorizontalAlignment(0);
      responseLabel.setVerticalAlignment(3);
      statsScoreLabel.setFont(new Font(fontName, 0, 30));
      statsScoreLabel.setVerticalAlignment(1);
      statsScoreLabel.setHorizontalAlignment(2);
      numberOfEncouteredProblemsLabel.setFont(new Font(fontName, 0, 20));
      numberOfSolvedProblemsLabel.setFont(new Font(fontName, 0, 20));
      commentLabel.setFont(new Font(fontName, 0, 20));
      scoreLabel.setFont(new Font(fontName, 0, 20));
      answerTextField.setFont(new Font(fontName, 0, 40));
      container.setLayout((LayoutManager)null);
      container.setBackground(Color.BLACK);
      container.add(responseLabel);
      container.add(statsScoreLabel);
      container.add(numberOfEncouteredProblemsLabel);
      container.add(numberOfSolvedProblemsLabel);
      container.add(commentLabel);
      container.add(scoreLabel);
      container.add(start_okLabel);
      container.add(quitLabel);
      this.addMouseListener(this);
      this.setSize(1366, 768);
      this.setTitle("Teach Arithmetic");
      this.setResizable(false);
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.setUndecorated(true);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setVisible(true);
   }

   public static void main(String[] args) {
      new TeachArithmeticFrame();
   }

   public void processAnswer(String answer) {
      if (!currentProblemAnswered) {
         try {
            inputAnswer = Integer.parseInt(answer);
         } catch (NumberFormatException var3) {
            responseLabel.setText("Enter valid numbers only");
            return;
         }

         if (answer == "") {
            responseLabel.setText("No number, try again");
         } else if (inputAnswer == teachArithmetic.getResult()) {
            responseLabel.setText("");
            responseLabel.setIcon(correctSign);
            isWaitingForAnswer = false;
            if (!this.hintShown && !currentProblemAnswered) {
               ++solvedProblems;
            }

            currentProblemAnswered = true;
            this.showStats();
         } else {
            responseLabel.setText("");
            responseLabel.setIcon(wrongSign);
            this.showStats();
         }
      }

   }

   public void start() {
      responseLabel.setText("");
      command = "OK";
      start_okLabel.setText(command);
      container.add(problemLabel);
      container.add(answerTextField);
      container.add(nextLabel);
      container.add(hintLabel);
      nextLabel.setOpaque(true);
      nextLabel.setBackground(new Color(50, 50, 50));
      hintLabel.setOpaque(true);
      hintLabel.setBackground(new Color(50, 50, 50));
      quitLabel.setOpaque(true);
      quitLabel.setBackground(new Color(50, 50, 50));
      teachArithmetic.generateProblem();
      responseLabel.setText("");
      answerTextField.setText("");
      switch(teachArithmetic.getOperand()) {
      case 1:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " + " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 2:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " * " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 3:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " - " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 4:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " / " + teachArithmetic.getSecondNumber() + " =");
      }

      isWaitingForAnswer = true;
      this.hintShown = false;
      ++encounteredProblems;
      currentProblemAnswered = false;
      answerTextField.setBackground(new Color(250, 250, 250));
      this.add(problemLabel);
      isWaitingForAnswer = true;
   }

   public void updateFrame() {
      responseLabel.setText("");
      answerTextField.setText("");
      switch(teachArithmetic.getOperand()) {
      case 1:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " + " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 2:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " * " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 3:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " - " + teachArithmetic.getSecondNumber() + " =");
         break;
      case 4:
         problemLabel.setText(teachArithmetic.getFirstNumber() + " / " + teachArithmetic.getSecondNumber() + " =");
      }

      isWaitingForAnswer = true;
      this.hintShown = false;
      encounteredProblems++;
      currentProblemAnswered = false;
   }

   public void showStats() {
      this.score = solvedProblems / encounteredProblems * 100.0D;
      if (this.score <= 33.0D) {
         scoreLabel.setForeground(Color.RED);
         commentLabel.setForeground(Color.RED);
         comment = "You're doing bad";
      } else if (this.score >= 66.0D) {
         scoreLabel.setForeground(Color.GREEN);
         commentLabel.setForeground(Color.GREEN);
         comment = "You're doing great";
      } else {
         scoreLabel.setForeground(Color.YELLOW);
         commentLabel.setForeground(Color.YELLOW);
         comment = "You're doing normal";
      }

      statsScoreLabel.setText("STATS:");
      numberOfEncouteredProblemsLabel.setText("Encountered problems: " + df.format(encounteredProblems));
      numberOfSolvedProblemsLabel.setText("Solved problems: " + df.format(solvedProblems));
      scoreLabel.setText("Score: " + df.format(this.score));
      commentLabel.setText("Comment: " + comment);
   }

   public void mouseClicked(MouseEvent event) {
      int x = event.getX();
      int y = event.getY();
      if (x >= start_okX && x <= start_okX + buttonLength && y >= start_okY && y <= start_okY + buttonWidth) {
         if (command.equals("start")) {
            this.start();
         } else { // equals("ok")
            this.processAnswer(answerTextField.getText());
         }
      } else {
         if (x >= nextX && x <= nextX + buttonLength && y >= nextY && y <= nextY + buttonWidth) {
            responseLabel.setIcon(null);
            teachArithmetic.generateProblem();
            this.updateFrame();
         }

         if (x >= hintX && x <= hintX + buttonLength && y >= hintY && y <= hintY + buttonWidth) {
            if (isWaitingForAnswer) {
               responseLabel.setText("The answer is >> " + teachArithmetic.getResult());
            }

            this.hintShown = true;
         }

         if(x >= quitX && x <= quitX + buttonLength && y >= quitY && y <= quitY + buttonWidth) {
            System.exit(0);
         }
      }

   }

   public void mouseEntered(MouseEvent arg0) {
   }

   public void mouseExited(MouseEvent arg0) {
   }

   public void mousePressed(MouseEvent arg0) {
   }

   public void mouseReleased(MouseEvent arg0) {
   }
}
