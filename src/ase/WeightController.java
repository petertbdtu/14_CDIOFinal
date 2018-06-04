package ase;

public class WeightController {

}

/*

    public static void main(String[] args) {
        IWeightHandler meme = new WeightHandler();
        go(meme, new UserDAO(), new BatchDAO());
    }

    private static void go(IWeightHandler wh, IUserDAO user, IBatchDAO batch){
        int taraWeight;
        int netWeight;
        int brutWeight;
        boolean isRunning = true;

        IConsoleUI ui = new ConsoleUI();

        while(isRunning){
            taraWeight = 0;
            netWeight = 0;
            brutWeight = 0;
            int opNo;
            boolean hasValidOp = false;
            UserDTO curOp = null;

            wh.clearText();
            wh.tare();
            wh.haltProgress("Vaegt klar");

            while(!hasValidOp){
                String tmp = wh.getInput("Indtast operatornr:");
                opNo = Integer.parseInt(tmp);
                if(opNo < 11 || opNo > 99){
                    wh.showError();
                    wh.showText("Ugyldigt operatornr.");
                    ui.showMessage("OpNo out of domain [11;99] was:"+opNo);
                    wh.sleep(3);
                    wh.clearText();
                    break;
                }

                try {
                    curOp = user.getUser(opNo);
                    hasValidOp = true;
                } catch (DALException e) {
                    wh.showError();
                    wh.showText("Ugyldigt operatornr.");
                    ui.showMessage("Operator does not exist:"+opNo);
                    wh.sleep(3);
                    wh.clearText();
                }
            }
            //Ensure correct userID was entereed
            wh.showText("Velkommen "+ curOp.getUserName());
            wh.sleep(3);
            wh.clearText();
            if(!wh.getConfirmation("Er brugeren korrekt?")){
                break;
            }

            //Batchentry
            int batchNo = 0;
            boolean hasValidBatch = false;
            BatchDTO curBatch = null;

            while(!hasValidBatch){
                String tmp = wh.getInput("Indtast batchnr:");
                batchNo = Integer.parseInt(tmp);
                if(batchNo < 1000 || batchNo > 9999){
                    wh.showError();
                    wh.showText("Ugyldigt batchnr.");
                    ui.showMessage("OpNo out of domain [1000;9999] was:"+batchNo);
                    wh.sleep(3);
                    wh.clearText();
                    break;
                }

                try {
                    curBatch = batch.getBatch(batchNo);
                    hasValidBatch = true;
                } catch (DALException e) {
                    wh.showError();
                    wh.showText("Ugyldigt batchnr.");
                    ui.showMessage("Batch does not exist:"+batchNo);
                    wh.sleep(3);
                    wh.clearText();
                }
            }

            //Ensure correct batchID was entereed
            if(!wh.getConfirmation("Du vil veje "+curBatch.getBatchName()+"?")){
                break;
            }

            //tara registration
            wh.haltProgress("Fjern alt fra vaegt");
            wh.tare();
            wh.haltProgress("Placer tara");
            taraWeight = wh.tare();
            ui.showMessage("Taraweight:"+taraWeight);

            //weight registration
            wh.haltProgress("Placer "+ curBatch.getWeight()+"g "+curBatch.getBatchName());
            netWeight = wh.getWeight();
            ui.showMessage("Netweight:"+netWeight);
            wh.tare();

            wh.haltProgress("Fjern alt");
            brutWeight = wh.getWeight();
            ui.showMessage("Brutweight:"+brutWeight);

            ui.showMessage("Weight difference from expected: " + Math.abs(Math.abs(brutWeight)-(netWeight+taraWeight)));
            ui.showMessage("Errortolerance: " + curBatch.getTolerance());
            if(Math.abs(Math.abs(brutWeight)-(netWeight+taraWeight)) > curBatch.getTolerance()){
                wh.haltProgress("Ikke godkendt");
            } else {
                wh.haltProgress("Godkendt");
            }
            //isRunning = wh.getConfirmation("Vej mere?");
        }
    }
 */