/*
 * Code made by: Harry Booth-Beach
 * Date created: 20/05/2024
 * Date modified: 20/05/2024
 */
package code.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import code.sensor.AirspeedSensor;
import code.sensor.AltitudeSensor;
import code.sensor.AttitudeSensor;
import code.sensor.Engine;

public class Simulator {
    private List<Instance> airspeedData;
    private List<Instance> altitudeData;
    private List<Instance> attitudeData;
    private List<Instance> engineData;

    private List<AirspeedSensor> airspeedSensors;
    private List<AltitudeSensor> altitudeSensors;
    private List<AttitudeSensor> attitudeSensors;
    private List<Engine> engineSensors;

    public Simulator() {
        airspeedData = new  ArrayList<>();
        altitudeData = new  ArrayList<>();
        attitudeData = new  ArrayList<>();
        engineData = new  ArrayList<>(); 

        airspeedSensors = new  ArrayList<>();
        altitudeSensors = new  ArrayList<>();
        attitudeSensors = new  ArrayList<>();
        engineSensors = new  ArrayList<>();
    }

    /**
     * Method for reading CSV files
     * @param fileName
     * @param sim
     * @return
     */
    public static ArrayList<Instance> readData(String fileName, Simulator sim) {
        List<String> fieldNames = new ArrayList<>();
		ArrayList<Instance> instances = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(fileName));
			sc.useDelimiter(",|\\n");
			// save field names
            fieldNames = Arrays.asList(sc.nextLine().split(","));
            
			// parse data
			while (sc.hasNext()) {
                int id = 0;
				String[] values = sc.nextLine().split(",");
                HashMap<String, Double> fields = new HashMap<>();
				for (int i=0; i<fieldNames.size(); i++) {
					fields.put(fieldNames.get(i), Double.parseDouble(values[i]));
				}
				instances.add(new Instance(id, fields));
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

        // Create simulator instances for 2oo3
        for (int i=0; i<3; i++) {
            sim.airspeedSensors.add(new AirspeedSensor());
            sim.altitudeSensors.add(new AltitudeSensor(sim.airspeedData.get(0).getValues().get(0), sim.airspeedData.get(0).getValues().get(1)));
            sim.attitudeSensors.add(new AttitudeSensor(sim.altitudeData.get(0).getValues().get(0), sim.altitudeData.get(0).getValues().get(1), 
            sim.altitudeData.get(0).getValues().get(3)));
            sim.engineSensors.add(new Engine(sim.engineData.get(0).getValues().get(0), sim.engineData.get(0).getValues().get(1)));
        }
    }
}
