/*
 * Code made by: Harry Booth-Beach
 * Date created: 20/05/2024
 * Date modified: 24/05/2024
 */
package code.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import code.core.CoreSystem;
import code.sensor.Sensor;
import code.sensor.AirspeedSensor;
import code.sensor.AltitudeSensor;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;

public class Simulator {
    private List<Instance> airspeedData;
    private List<Instance> altitudeData;
    private List<Instance> attitudeData;
    private List<Instance> engineData;
    private CoreSystem cS;

    public Simulator() {
        airspeedData = new  ArrayList<>();
        altitudeData = new  ArrayList<>();
        attitudeData = new  ArrayList<>();
        engineData = new  ArrayList<>(); 

        cS = new CoreSystem();
    }

    /**
     * Method for reading CSV files
     * @param fileName
     * @param sim
     * @return
     */
    public static ArrayList<Instance> readData(String fileName, Simulator sim) {
        ArrayList<String> fieldNames = new ArrayList<>();
		ArrayList<Instance> instances = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(fileName));
			sc.useDelimiter(",|\\n");
			// save field names
            String[] fieldArray = sc.nextLine().split(",");
            for (String s : fieldArray) {
                fieldNames.add(s);
            }
            
			// parse data
			while (sc.hasNext()) {
                int id = 0;
				String[] values = sc.nextLine().split(",");
                HashMap<String, Double> fields = new HashMap<>();
				for (int i=0; i<fieldNames.size(); i++) {
					fields.put(fieldNames.get(i), Double.parseDouble(values[i]));
				}
				instances.add(new Instance(id, fieldNames,fields));
                id++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
		return instances;
	}

    /**
     * Main method for running the simulator
     * @param args
     */
    public static void main(String[] args) {
        // Check there are the correct amount of args
        if (args.length != 4) {
            System.err.println("Usage: java Simulator <airspeed.csv> <altitude.csv> <attitude.csv> <engine.csv>");
            System.exit(1);
        }

        // Store file names from command line args
        String airSpeedFile = args[0];
        String altitudeFile = args[1];
        String attitudeFile = args[2];
        String engineFile = args[3];

        // Create simulator instance
        Simulator sim = new Simulator();

        // Take all sensor data
        sim.airspeedData = readData("data/airspeed/" + airSpeedFile, sim);
        sim.altitudeData = readData("data/altitude/" + altitudeFile, sim);
        sim.attitudeData = readData("data/attitude/" + attitudeFile, sim);
        sim.engineData = readData("data/engine/" + engineFile, sim);

        // Start simulator running
        sim.cS.start();

        // Create lists to store sensors to update
        List<Sensor> airspeedSensor = new ArrayList<>();
        List<Sensor> altitudeSensor = new ArrayList<>();
        List<Sensor> attitudeSensor = new ArrayList<>();
        Engine[] engineSensor = new Engine[2];

        // set sensor lists through core system getters
        airspeedSensor = sim.cS.getAirspeedSensors();
        altitudeSensor = sim.cS.getAltitudeSensors();
        attitudeSensor = sim.cS.getAttitudeSensors();
        engineSensor = sim.cS.getEngines();
    }
}
