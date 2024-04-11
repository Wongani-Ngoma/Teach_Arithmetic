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
   private static final int FRAME_X = 1366;
   private static final int FRAME_Y = 768;
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
   private static JLabel responceLabel;
   private static JLabel statscoreLabelabel;
   private static JLabel encounteredProblemscoreLabelabel;
   private static JLabel solvedProblemscoreLabelabel;
   private static JLabel commentLabel;
   private static JLabel scoreLabel;
   private static JTextField answerTextField;
   private static JLabel startJLabel;
   private static JLabel nextJLabel;
   private static JLabel okJLabel;
   private static JLabel hintJLabel;
   private static ImageIcon correctSign;
   private static ImageIcon wrongSign;
   private static DecimalFormat df;
   private static int buttonLength = 100;
   private static int buttonWidth = 40;
   private static int startX = 200;
   private static int startY = 600;
   private static int nextX = 1000;
   private static int nextY = 600;
   private static int hintX;
   private static int hintY;
   private static TeachArithmetic teachArithmetic;

   static {
      hintX = 683 - buttonLength / 2;
      hintY = 600;
   }

   public TeachArithmeticFrame() {
      currentProblemAnswered = false;
      command = "Start";
      df = new DecimalFormat("0");
      container = this.getContentPane();
      problemLabel = new Label("");
      responceLabel = new JLabel();
      statscoreLabelabel = new JLabel();
      encounteredProblemscoreLabelabel = new JLabel();
      solvedProblemscoreLabelabel = new JLabel();
      commentLabel = new JLabel();
      scoreLabel = new JLabel();
      answerTextField = new JTextField();
      startJLabel = new JLabel(command);
      nextJLabel = new JLabel("Next");
      hintJLabel = new JLabel("Hint");
      correctSign = new ImageIcon("C:\\Users\\Ngoma\\Pictures\\Correct.png");
      wrongSign = new ImageIcon("C:\\Users\\Ngoma\\Pictures\\Wrong.png");
      teachArithmetic = new TeachArithmetic();
      answerTextField.setBounds(1000, 20, 300, 80);
      problemLabel.setBounds(10, 10, 500, 100);
      responceLabel.setBounds(413, 350, 500, 170);
      statscoreLabelabel.setBounds(1000, 180, 300, 40);
      encounteredProblemscoreLabelabel.setBounds(1000, 220, 300, 40);
      solvedProblemscoreLabelabel.setBounds(1000, 260, 300, 40);
      scoreLabel.setBounds(1000, 300, 300, 40);
      commentLabel.setBounds(1000, 340, 300, 40);
      startJLabel.setBounds(startX, startY, buttonLength, buttonWidth);
      nextJLabel.setBounds(nextX, nextY, buttonLength, buttonWidth);
      hintJLabel.setBounds(hintX, hintY, buttonLength, buttonWidth);
      problemLabel.setForeground(Color.WHITE);
      problemLabel.setFont(new Font("Antipasto", 0, 70));
      startJLabel.setOpaque(true);
      startJLabel.setBackground(new Color(50, 50, 50));
      startJLabel.setHorizontalAlignment(0);
      startJLabel.setVerticalAlignment(0);
      startJLabel.setFont(new Font("Antipasto", 0, 15));
      startJLabel.setForeground(Color.BLACK);
      hintJLabel.setHorizontalAlignment(0);
      hintJLabel.setVerticalAlignment(0);
      hintJLabel.setFont(new Font("Antipasto", 0, 15));
      hintJLabel.setForeground(Color.BLACK);
      nextJLabel.setHorizontalAlignment(0);
      nextJLabel.setVerticalAlignment(0);
      nextJLabel.setFont(new Font("Antipasto", 0, 15));
      nextJLabel.setForeground(Color.BLACK);
      responceLabel.setFont(new Font("Antipasto", 0, 40));
      responceLabel.setVerticalTextPosition(1);
      responceLabel.setHorizontalTextPosition(0);
      responceLabel.setHorizontalAlignment(0);
      responceLabel.setVerticalAlignment(3);
      statscoreLabelabel.setFont(new Font("Antipasto", 0, 30));
      statscoreLabelabel.setVerticalAlignment(1);
      statscoreLabelabel.setHorizontalAlignment(2);
      encounteredProblemscoreLabelabel.setFont(new Font("Antipasto", 0, 20));
      solvedProblemscoreLabelabel.setFont(new Font("Antipasto", 0, 20));
      commentLabel.setFont(new Font("Antipasto", 0, 20));
      scoreLabel.setFont(new Font("Antipasto", 0, 20));
      answerTextField.setFont(new Font("Antipasto", 0, 40));
      container.setLayout((LayoutManager)null);
      container.setBackground(Color.BLACK);
      container.add(responceLabel);
      container.add(statscoreLabelabel);
      container.add(encounteredProblemscoreLabelabel);
      container.add(solvedProblemscoreLabelabel);
      container.add(commentLabel);
      container.add(scoreLabel);
      container.add(startJLabel);
      this.addMouseListener(this);
      this.setSize(1366, 768);
      this.setTitle("Teach Arithmetic v1.1");
      this.setResizable(false);
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
            responceLabel.setText("Enter valid numbers only");
            return;
         }

         if (answer == "") {
            responceLabel.setText("No number, try again");
         } else if (inputAnswer == teachArithmetic.getResult()) {
            responceLabel.setText("");
            responceLabel.setIcon(correctSign);
            isWaitingForAnswer = false;
            if (!this.hintShown && !currentProblemAnswered) {
               ++solvedProblems;
            }

            currentProblemAnswered = true;
            this.showStats();
         } else {
            responceLabel.setText("");
            responceLabel.setIcon(wrongSign);
            this.showStats();
         }
      }

   }

   public void start() {
      responceLabel.setText("");
      command = "OK";
      startJLabel.setText(command);
      container.add(problemLabel);
      container.add(answerTextField);
      container.add(nextJLabel);
      container.add(hintJLabel);
      nextJLabel.setOpaque(true);
      nextJLabel.setBackground(new Color(50, 50, 50));
      hintJLabel.setOpaque(true);
      hintJLabel.setBackground(new Color(50, 50, 50));
      teachArithmetic.generateProblem();
      responceLabel.setText("");
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
      responceLabel.setText("");
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

      statscoreLabelabel.setText("STATS:");
      encounteredProblemscoreLabelabel.setText("Encountered problems: " + df.format(encounteredProblems));
      solvedProblemscoreLabelabel.setText("Solved problems: " + df.format(solvedProblems));
      scoreLabel.setText("Score: " + df.format(this.score));
      commentLabel.setText("Comment: " + comment);
   }

   public void mouseClicked(MouseEvent arg0) {
      int x = arg0.getX();
      int y = arg0.getY();
      if (x >= 204 && x <= 204 + buttonLength && y >= 622 && y <= 622 + buttonWidth) {
         if (command.equals("Start")) {
            this.start();
         } else {
            this.processAnswer(answerTextField.getText());
         }
      } else {
         if (x >= 1003 && x <= 1003 + buttonLength && y >= 622 && y <= 622 + buttonWidth) {
            responceLabel.setIcon((Icon)null);
            teachArithmetic.generateProblem();
            this.updateFrame();
         }

         if (x >= 636 && x <= 636 + buttonLength && y >= 622 && y <= 622 + buttonWidth) {
            if (isWaitingForAnswer) {
               responceLabel.setText("The answer is >> " + teachArithmetic.getResult());
            }

            this.hintShown = true;
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