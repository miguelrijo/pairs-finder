package dtos;

public class PairDTO {

    private String firstWorkerName;
    private String secondWorkerName;
    private int numberOfShiftTogether = 0;

   public PairDTO(String firstWorkerName, String secondWorkerName) {
            this.firstWorkerName = firstWorkerName;
            this.secondWorkerName = secondWorkerName;
            this.numberOfShiftTogether = numberOfShiftTogether;
    }

   public void incrementShiftTogether(){
        this.numberOfShiftTogether++;
    }

    public String getFirstWorkerName() {
        return firstWorkerName;
    }

    public String getSecondWorkerName() {
        return secondWorkerName;
    }

    public int getNumberOfShiftTogether() {
        return numberOfShiftTogether;
    }
}
