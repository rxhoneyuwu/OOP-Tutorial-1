import java.util.Scanner;

public class QuizBattleCLI {
    private Questions questions;
    private int playerHP;
    private int bossHP;
    private int score;
    private Scanner scanner;

    public QuizBattleCLI() {
        questions = new Questions();
        playerHP = 100;
        bossHP = 100;
        score = 0;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("⚔️ CODE BOSS BATTLE - TERMINAL EDITION ⚔️");
        System.out.println("=".repeat(50));
        System.out.println("Answer Java questions correctly to defeat the boss!\n");

        while (questions.hasMoreQuestions() && bossHP > 0 && playerHP > 0) {
            displayStatus();
            Questions.Question currentQuestion = questions.getCurrentQuestion();
            
            if (currentQuestion == null) {
                break;
            }

            displayQuestion(currentQuestion);
            int answer = getUserAnswer();
            
            if (currentQuestion.isCorrect(answer)) {
                bossHP -= 20;
                score += 10;
                System.out.println("\n✅ CORRECT! Boss HP -20, Score +10");
            } else {
                playerHP -= 10;
                System.out.println("\n❌ WRONG! Player HP -10");
                System.out.println("Correct answer: " + (char)('A' + currentQuestion.getCorrectAnswerIndex()) + 
                    ". " + currentQuestion.getOptions()[currentQuestion.getCorrectAnswerIndex()]);
            }

            if (bossHP <= 0) {
                System.out.println("\n🎉🎉🎉 VICTORY! You defeated the Code Boss! 🎉🎉🎉");
                System.out.println("Final Score: " + score);
                break;
            }

            if (playerHP <= 0) {
                System.out.println("\n💀💀💀 GAME OVER! The Code Boss defeated you! 💀💀💀");
                System.out.println("Final Score: " + score);
                break;
            }

            questions.getNextQuestion();
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

    private void displayStatus() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("❤️ Player HP: " + playerHP + "  |  👾 Boss HP: " + bossHP + "  |  ⭐ Score: " + score);
        System.out.println("📝 Question " + (questions.getCurrentQuestionIndex() + 1) + "/" + questions.getTotalQuestions());
        System.out.println("=".repeat(50));
    }

    private void displayQuestion(Questions.Question question) {
        System.out.println("\n📌 " + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (char)('A' + i) + ". " + options[i]);
        }
    }

    private int getUserAnswer() {
        while (true) {
            System.out.print("\nEnter your answer (A, B, C, or D): ");
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
                return input.charAt(0) - 'A';
            }
            System.out.println("Invalid input! Please enter A, B, C, or D.");
        }
    }

    public static void main(String[] args) {
        QuizBattleCLI game = new QuizBattleCLI();
        game.start();
    }
}
