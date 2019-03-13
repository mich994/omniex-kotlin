package omniex.nl.omniex.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String fixUrl(String url) {
        String cleanedUrl = url.replaceAll("\\\\", "");
        return cleanedUrl;
    }

    public static String createErrorMessageString(List<String> errors) {
        if (errors.size() == 1)
            return errors.get(0);
        else {
            List<String> cleanedErrors = new ArrayList<>();
            for(String e : errors){
                cleanedErrors.add(e.replace("!", "."));
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i <cleanedErrors.size() ; i++) {
                if(i== cleanedErrors.size()-1)
                    builder.append(cleanedErrors.get(i));
                else
                    builder.append(cleanedErrors.get(i) + "\n \n");
            }
            return builder.toString();
        }
    }
}
