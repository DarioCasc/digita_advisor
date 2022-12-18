package entities;

public class Feedback {
    private int progressive;
    private boolean isPositive;
    private int score;

    public int getProgressive() {
        return progressive;
    }

    public void setProgressive(int progressive) {
        this.progressive = progressive;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
