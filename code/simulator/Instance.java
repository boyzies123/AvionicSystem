/*
 * Code made by: Harry Booth-Beach
 * Date created: 20/05/2024
 * Date modified: 20/05/2024
 */
package code.simulator;

import java.util.HashMap;

public record Instance(int id, HashMap<String, Double> values) {

    /**
     * Returns instance id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Returns hashmap containing Key: field name, Value: field value
     * @return
     */
    public HashMap<String, Double> getValues() {
        return values;
    }
}
