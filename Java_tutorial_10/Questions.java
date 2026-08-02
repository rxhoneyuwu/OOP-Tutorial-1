import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questionList;
    private int currentQuestionIndex;

    public Questions() {
        questionList = new ArrayList<>();
        currentQuestionIndex = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Java Fundamentals
        questionList.add(new Question(
            "What is the size of int in Java?",
            new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on platform"},
            1 // index 1 = "4 bytes"
        ));

        questionList.add(new Question(
            "Which keyword is used to define a constant in Java?",
            new String[]{"const", "final", "static", "constant"},
            1 // index 1 = "final"
        ));

        questionList.add(new Question(
            "What is the default value of a boolean variable in Java?",
            new String[]{"true", "false", "null", "0"},
            1 // index 1 = "false"
        ));

        questionList.add(new Question(
            "Which of these is NOT a primitive data type in Java?",
            new String[]{"int", "float", "String", "boolean"},
            2 // index 2 = "String"
        ));

        questionList.add(new Question(
            "What does JVM stand for?",
            new String[]{
                "Java Virtual Machine",
                "Java Variable Manager",
                "Java Visual Memory",
                "Java Version Module"
            },
            0 // index 0 = "Java Virtual Machine"
        ));

        questionList.add(new Question(
            "Which method is the entry point of a Java program?",
            new String[]{
                "main()",
                "start()",
                "run()",
                "init()"
            },
            0 // index 0 = "main()"
        ));

        questionList.add(new Question(
            "What is the result of 10 % 3 in Java?",
            new String[]{"3", "3.33", "1", "0"},
            2 // index 2 = "1"
        ));

        questionList.add(new Question(
            "Which access modifier makes a member accessible only within the same class?",
            new String[]{"public", "protected", "private", "default"},
            2 // index 2 = "private"
        ));

        questionList.add(new Question(
            "What is the parent class of all classes in Java?",
            new String[]{"Object", "Class", "Main", "Super"},
            0 // index 0 = "Object"
        ));

        questionList.add(new Question(
            "Which loop in Java guarantees at least one execution?",
            new String[]{"for", "while", "do-while", "foreach"},
            2 // index 2 = "do-while"
        ));

        questionList.add(new Question(
            "What is the correct way to create an array in Java?",
            new String[]{
                "int[] arr = new int[5];",
                "int arr[] = new int[5];",
                "Both A and B are correct",
                "Neither is correct"
            },
            2 // index 2 = "Both A and B are correct"
        ));

        questionList.add(new Question(
            "What is method overloading in Java?",
            new String[]{
                "Same method name, different parameters",
                "Same method name, same parameters, different class",
                "Different method name, same parameters",
                "Same method name, different return type only"
            },
            0 // index 0 = "Same method name, different parameters"
        ));

        questionList.add(new Question(
            "Which package is automatically imported in Java?",
            new String[]{"java.lang", "java.util", "java.io", "java.net"},
            0 // index 0 = "java.lang"
        ));

        questionList.add(new Question(
            "What is the output of: System.out.println(10 + 20 + \"30\");",
            new String[]{"3030", "102030", "30", "3030 as string"},
            0 // index 0 = "3030"
        ));

        questionList.add(new Question(
            "Which statement is used to exit a switch case in Java?",
            new String[]{"break", "continue", "exit", "return"},
            0 // index 0 = "break"
        ));
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            return questionList.get(currentQuestionIndex);
        }
        return null;
    }

    public Question getNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questionList.size()) {
            return questionList.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questionList.size();
    }

    public void reset() {
        currentQuestionIndex = 0;
    }

    public int getTotalQuestions() {
        return questionList.size();
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    // Inner class to represent a question
    public static class Question {
        private String question;
        private String[] options;
        private int correctAnswerIndex;

        public Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }

        public boolean isCorrect(int answerIndex) {
            return answerIndex == correctAnswerIndex;
        }
    }
}