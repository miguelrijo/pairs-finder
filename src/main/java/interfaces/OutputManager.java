package interfaces;

import dtos.PairDTO;

import java.util.ArrayList;

public interface OutputManager {

   public void processResult(ArrayList<PairDTO> pairs);
   public void processErrorMessage(String msg);

}
