package ase;

public interface IWeightSocket {
    int getWeight();
    int tare();
    void showError();
    void showText(String msg);
    void clearText();
    int getInput(String msg);
    boolean getConfirmation(String msg);
    void haltProgress(String msg);

    //Exclusively for testing with the emulator
    void overrideWeight(int grams);
    void exit();

    void sleep(int s);
}
