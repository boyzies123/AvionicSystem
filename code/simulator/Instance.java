/*
 * Code made by: Harry Booth-Beach
 * Date created: 20/05/2024
 * Date modified: 24/05/2024
 */
package code.simulator;

import java.util.ArrayList;
import java.util.HashMap;

public record Instance(int id, ArrayList<String> fieldNames, HashMap<String, Double> values) {

    /**
     * Returns instance id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Returns arraylist containing field names
     * @return
     */
    public ArrayList<String> getFieldNames() {
        return fieldNames;
    }

    /**
     * Returns hashmap containing Key: field name, Value: field value
     * @return
     */
    public HashMap<String, Double> getValues() {
        return values;
    }
}
