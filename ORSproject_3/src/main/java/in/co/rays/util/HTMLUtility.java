package in.co.rays.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.dto.DropdownList;
import in.co.rays.exception.ApplicationException;

// TODO: Auto-generated Javadoc
/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 *
 * @author 
 *
 */
public class HTMLUtility {

    /**
     * Create HTML SELECT list from MAP paramters values.
     *
     * @param name the name
     * @param selectedVal the selected val
     * @param map the map
     * @return the list
     */
    public static String getList(String name, String selectedVal,
            HashMap<String, String> map) {

        StringBuffer sb = new StringBuffer("<select dropdown-menu class='form-control' dropdown-item name='" + name + "' >");

        Set<String> keys = map.keySet();
        String val = null;
        boolean select=true;
        if(select){
        	sb.append("<option style='background-color:white;' selected value=''>-------select--------</option>");
        }

        for (String key : keys) {
            val = map.get(key);
            if (key.trim().equals(selectedVal)) {
                sb.append("<option selected value='" + key + "'>" + val
                        + "</option>");
            } else {
                sb.append("<option value='" + key + "'>" + val + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * Create HTML SELECT List from List parameter.
     *
     * @param name the name
     * @param selectedVal the selected val
     * @param list the list
     * @return the list
     */
    public static String getList(String name, String selectedVal, List list) {

        Collections.sort(list);

        List<DropdownList> dd = (List<DropdownList>) list;
        
        StringBuffer sb = new StringBuffer("<select dropdown-menu style='background-color:white;' class='form-control' name='" + name + "'>");
		
        sb.append("<option style='background-color:white;' selected value=''>--------select--------</option>");
		
		String key = null;
        String val = null;

        for (DropdownList obj : dd) {
            key = obj.getKey();
            val = obj.getValue();

            if (key.trim().equals(selectedVal)) {
                sb.append("<option selected value='" + key + "'>" + val
                        + "</option>");
            } else {
                sb.append("<option value='" + key + "'>" + val + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }
}

