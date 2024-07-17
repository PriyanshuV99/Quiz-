import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;

    public QuizQuestion(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int currentQuestionIndex;
    private Timer timer;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            displayQuestion();
            int userAnswer = getUserAnswer(scanner);
            if (userAnswer == questions.get(currentQuestionIndex).getCorrectAnswer()) {
                score++;
            }
            System.out.println("Correct answer: " + (questions.get(currentQuestionIndex).getCorrectAnswer() + 1));
            System.out.println("Your score: " + score);
            System.out.println();
        }
        displayResult();
    }

    private void displayQuestion() {
        QuizQuestion question = questions.get(currentQuestionIndex);
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }
        startTimer();
    }

    private int getUserAnswer(Scanner scanner) {
        System.out.print("Enter your answer (1-" + questions.get(currentQuestionIndex).getOptions().length + "): ");
        int userAnswer = scanner.nextInt();
        return userAnswer - 1; // adjust for 0-based indexing
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                timer.cancel();
            }
        }, 30000); // 30 seconds
    }

    private void displayResult() {
        System.out.println("Quiz complete!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("Correct answers:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + (questions.get(i).getCorrectAnswer() + 1));
        }
    }

    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Rome"}, 0));
        questions.add(new QuizQuestion("What is the largest planet in our solar system?", new String[]{"Earth", "Saturn", "Jupiter", "Uranus"}, 2));
        questions.add(new QuizQuestion("What is the smallest country in the world?", new String[]{"Vatican City", "Monaco", "Nauru", "Tuvalu"}, 0));
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}