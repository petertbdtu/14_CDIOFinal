package ase;

import java.io.IOException;

public interface IWeightSocket {
    int getWeight() throws IOException;
    int tare() throws IOException;
    void showError() throws IOException;
    void showText(String msg) throws IOException;
    void clearText() throws IOException;
    int getInput(String msg) throws IOException;
    boolean getConfirmation(String msg) throws IOException;
    void haltProgress(String msg) throws IOException;

    //Exclusively for testing with the emulator
    void overrideWeight(int grams);
    void exit();

    void sleep(int s);
}
