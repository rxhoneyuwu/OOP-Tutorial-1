import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuizBattleGUI extends JFrame implements ActionListener {
    // Game state
    private Questions questions;
    private int playerHP;
    private int bossHP;
    private int score;
    private boolean gameOver;

    // UI Components
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton;
    private JButton nextButton;
    private JButton resetButton;
    private JLabel playerHPLabel;
    private JLabel bossHPLabel;
    private JLabel scoreLabel;
    private JLabel questionCounterLabel;
    private JTextArea statusArea;
    private JPanel questionPanel;
    private JPanel statusPanel;
    private JPanel controlPanel;

    // Timer for boss HP animation
    private Timer bossHPTimer;
    private int animationDelay = 50;
    private int animationSteps = 10;
    private int currentAnimationStep = 0;
    private int targetBossHP;

    public QuizBattleGUI() {
        // Initialize game state
        questions = new Questions();
        playerHP = 100;
        bossHP = 100;
        score = 0;
        gameOver = false;

        // Set up the frame
        setTitle("⚔️ Code Boss Battle - Java Quiz ⚔️");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize UI components
        initializeComponents();

        // Load first question
        loadQuestion();

        // Show the frame
        setVisible(true);
    }

    private void initializeComponents() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 245));

        // Title
        titleLabel = new JLabel("⚔️ CODE BOSS BATTLE ⚔️", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 150));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Status Panel (HP and Score)
        statusPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statusPanel.setBackground(new Color(240, 240, 245));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        playerHPLabel = new JLabel("❤️ Player HP: " + playerHP);
        playerHPLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerHPLabel.setForeground(new Color(200, 0, 0));

        bossHPLabel = new JLabel("👾 Boss HP: " + bossHP);
        bossHPLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bossHPLabel.setForeground(new Color(150, 0, 150));

        scoreLabel = new JLabel("⭐ Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(0, 100, 0));

        questionCounterLabel = new JLabel("📝 Question 1/" + questions.getTotalQuestions());
        questionCounterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionCounterLabel.setForeground(new Color(0, 0, 150));

        statusPanel.add(playerHPLabel);
        statusPanel.add(bossHPLabel);
        statusPanel.add(scoreLabel);
        statusPanel.add(questionCounterLabel);

        // Question Panel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 200), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        questionPanel.setMaximumSize(new Dimension(750, 300));

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Option buttons
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton("Option " + (i + 1));
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
            optionButtons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            optionButtons[i].setBackground(Color.WHITE);
            optionGroup.add(optionButtons[i]);
        }

        questionPanel.add(questionLabel);
        for (JRadioButton button : optionButtons) {
            questionPanel.add(button);
            questionPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        // Control Panel
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        controlPanel.setBackground(new Color(240, 240, 245));

        submitButton = new JButton("✅ Submit Answer");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(0, 150, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        nextButton = new JButton("➡️ Next Question");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(0, 100, 200));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setEnabled(false);
        nextButton.addActionListener(this);

        resetButton = new JButton("🔄 Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(200, 100, 0));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);

        controlPanel.add(submitButton);
        controlPanel.add(nextButton);
        controlPanel.add(resetButton);

        // Status Area
        statusArea = new JTextArea(4, 50);
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        statusArea.setBackground(new Color(255, 255, 250));
        statusArea.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        statusArea.setText("📖 Welcome to Code Boss Battle!\nAnswer correctly to defeat the boss!");
        JScrollPane scrollPane = new JScrollPane(statusArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Add all panels to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        // Window listener for cleanup
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (bossHPTimer != null && bossHPTimer.isRunning()) {
                    bossHPTimer.stop();
                }
            }
        });
    }

    private void loadQuestion() {
        if (gameOver) {
            return;
        }

        Questions.Question currentQuestion = questions.getCurrentQuestion();

        if (currentQuestion == null) {
            // No more questions - check if boss is defeated
            if (bossHP <= 0) {
                showGameResult(true);
            } else {
                statusArea.append("\n❌ No more questions! Game Over!\n");
                gameOver = true;
                disableAllButtons();
                showGameResult(false);
            }
            return;
        }

        questionLabel.setText("📌 " + currentQuestion.getQuestion());
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < options.length; i++) {
            optionButtons[i].setText((char)('A' + i) + ". " + options[i]);
            optionButtons[i].setSelected(false);
        }

        // Clear selection
        optionGroup.clearSelection();

        // Update question counter
        questionCounterLabel.setText("📝 Question " + (questions.getCurrentQuestionIndex() + 1) + "/" + questions.getTotalQuestions());

        // Enable submit, disable next
        submitButton.setEnabled(true);
        nextButton.setEnabled(false);

        // Enable option buttons if game not over
        if (!gameOver) {
            for (JRadioButton button : optionButtons) {
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        if (e.getSource() == submitButton) {
            handleSubmit();
        } else if (e.getSource() == nextButton) {
            handleNext();
        } else if (e.getSource() == resetButton) {
            resetGame();
        }
    }

    private void handleSubmit() {
        // Check if an option is selected
        int selectedIndex = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select an answer!",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Questions.Question currentQuestion = questions.getCurrentQuestion();

        if (currentQuestion == null) {
            return;
        }

        boolean correct = currentQuestion.isCorrect(selectedIndex);

        if (correct) {
            // Correct answer
            bossHP -= 20;
            score += 10;
            statusArea.append("✅ CORRECT! Boss HP -20, Score +10\n");
            bossHPLabel.setText("👾 Boss HP: " + bossHP);
            scoreLabel.setText("⭐ Score: " + score);

            // Boss HP animation
            animateBossHP();

            // Check if boss is defeated
            if (bossHP <= 0) {
                bossHP = 0;
                bossHPLabel.setText("👾 Boss HP: " + bossHP);
                showGameResult(true);
                return;
            }
        } else {
            // Wrong answer
            playerHP -= 10;
            statusArea.append("❌ WRONG! Player HP -10\n");
            playerHPLabel.setText("❤️ Player HP: " + playerHP);

            // Show correct answer
            String correctOption = (char)('A' + currentQuestion.getCorrectAnswerIndex()) + ". " +
                currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()];
            statusArea.append("   Correct answer: " + correctOption + "\n");

            // Check if player is defeated
            if (playerHP <= 0) {
                playerHP = 0;
                playerHPLabel.setText("❤️ Player HP: " + playerHP);
                showGameResult(false);
                return;
            }
        }

        // Disable submit and options, enable next
        submitButton.setEnabled(false);
        nextButton.setEnabled(true);
        for (JRadioButton button : optionButtons) {
            button.setEnabled(false);
        }
    }

    private void handleNext() {
        questions.getNextQuestion();
        loadQuestion();
    }

    private void animateBossHP() {
        if (bossHPTimer != null && bossHPTimer.isRunning()) {
            bossHPTimer.stop();
        }

        targetBossHP = bossHP;
        currentAnimationStep = 0;

        // Start from current displayed value
        String currentText = bossHPLabel.getText();
        int currentDisplayHP = bossHP + 20; // The HP before the decrease

        bossHPTimer = new Timer(animationDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAnimationStep++;
                if (currentAnimationStep <= animationSteps) {
                    float progress = (float) currentAnimationStep / animationSteps;
                    int animatedHP = Math.round(currentDisplayHP - (20 * progress));
                    bossHPLabel.setText("👾 Boss HP: " + animatedHP);
                } else {
                    bossHPLabel.setText("👾 Boss HP: " + targetBossHP);
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        bossHPTimer.start();
    }

    private void showGameResult(boolean victory) {
        gameOver = true;
        disableAllButtons();

        String title, message;
        Color color;

        if (victory) {
            title = "🏆 VICTORY! 🏆";
            message = "Congratulations! You defeated the Code Boss!\n" +
                      "Final Score: " + score + "\n" +
                      "Questions Answered: " + (questions.getCurrentQuestionIndex() + 1) + "\n\n" +
                      "You are now a true Java Programmer!";
            color = new Color(0, 150, 0);
            statusArea.append("\n🎉🎉🎉 YOU WIN! BOSS DEFEATED! 🎉🎉🎉\n");
        } else {
            title = "💀 GAME OVER 💀";
            message = "The Code Boss has defeated you!\n" +
                      "Final Score: " + score + "\n" +
                      "Questions Answered: " + (questions.getCurrentQuestionIndex() + 1) + "\n\n" +
                      "Don't give up! Practice more and try again!";
            color = new Color(200, 0, 0);
            statusArea.append("\n💀 GAME OVER! BOSS WINS! 💀\n");
        }

        statusArea.append("📊 Final Score: " + score + "\n");

        JOptionPane.showMessageDialog(this,
            message,
            title,
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void disableAllButtons() {
        submitButton.setEnabled(false);
        nextButton.setEnabled(false);
        for (JRadioButton button : optionButtons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        // Stop any running timer
        if (bossHPTimer != null && bossHPTimer.isRunning()) {
            bossHPTimer.stop();
        }

        // Reset game state
        questions.reset();
        playerHP = 100;
        bossHP = 100;
        score = 0;
        gameOver = false;

        // Update UI
        playerHPLabel.setText("❤️ Player HP: " + playerHP);
        bossHPLabel.setText("👾 Boss HP: " + bossHP);
        scoreLabel.setText("⭐ Score: " + score);

        statusArea.setText("🔄 Game Reset!\n📖 Welcome to Code Boss Battle!\nAnswer correctly to defeat the boss!");
        statusArea.setForeground(Color.BLACK);

        // Load first question
        loadQuestion();

        // Enable submit button and options
        submitButton.setEnabled(true);
        for (JRadioButton button : optionButtons) {
            button.setEnabled(true);
        }

        JOptionPane.showMessageDialog(this,
            "Game has been reset! Good luck!",
            "Game Reset",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizBattleGUI();
            }
        });
    }
}