package praca.demo.commons;

import java.util.Optional;

public class HelperConverter {


    public static String checkIfDouble(Optional<String> paramets){
        if(paramets.isPresent()){
            try {
                Double.parseDouble(paramets.get());
            }catch (NumberFormatException e) {
                return null;
            }
            return paramets.get();
        }
        return null;
    }
    public static String checkIfInteger(Optional<String> paramets){
        if(paramets.isPresent()){
            try {
                Integer.parseInt(paramets.get());
            }catch (NumberFormatException e) {
                return null;
            }
            return paramets.get();
        }
        return null;
    }
    public static String convertToString(Optional<String> paramets) {
       if(paramets.isPresent()){
           try {
               Integer.parseInt(paramets.get());
           }catch (NumberFormatException e) {
               return paramets.get();
           }
       }
       return null;
    }
}
