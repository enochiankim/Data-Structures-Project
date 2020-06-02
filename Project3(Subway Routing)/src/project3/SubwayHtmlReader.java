/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * SubwayHtml Reader, the main idea of this class is connect to the mta service
 * site(which is trash just like the mta) ,scan the lines and the routes on each
 * line. Afterwards, the reader takes the each lines and the routes and reads
 * them into a file.
 *
 * @author Enkim
 */
public class SubwayHtmlReader {

    //Creates new File to read the lines into.
    FileOutput subwayData = new FileOutput("SubwayLineData.txt");

    //Connects to the Mta websites , get the correct tables from the mta website , so it knows which text to scan into.
    public SubwayHtmlReader() {
        Document getLines = null;
        try {
            getLines = Jsoup.connect("http://web.mta.info/nyct/service/").get();
        } catch (Exception e) {
            System.exit(1);
        }
        Elements subwayLineElements = null;

        subwayLineElements = getLines.select("table[width=650]");
        subwayLineElements = subwayLineElements.select("table[align=center]");
        subwayLineElements = subwayLineElements.select("a[href]");

        //this for-loop, cycles through every single line of element from the mta website and edits each of the lines.
        for (Element e : subwayLineElements) {
            Document line = null;
            try {
                line = Jsoup.connect("http://web.mta.info/nyct/service/" + e.attributes().get("href")).get();
            } catch (Exception d) {
                System.exit(1);
            }

            System.out.println(
                    e.attributes().get("href"));

            Elements stationLineElements = null;

            stationLineElements = line.select("table");
            stationLineElements = stationLineElements.last().select("tr[height = 25]");
            stationLineElements = stationLineElements.select("span.emphasized");
            String stationString = "";

            for (Element ex : stationLineElements) {
                String stationName = ex.text();
                stationName = stationName.replace("/", "").trim();
                stationName = stationName.replace("- ", "-").trim();

                stationString += (stationName + ",");
            }

            String output = e.attributes().get("href").replace(".htm", ":") + stationString;

            output = output.replaceAll(",$", "");

            subwayData.writeLine(output);
            subwayData.flush();
        }
    }

    //Reads in every single lines that was stored and prints the lines into a text page.
    public static void readFromFile(TrainLines trainLine) {
        TextFileInput fileReader = new TextFileInput("SubwayLineData.txt");
        String readline = fileReader.readLine();
        while (readline != null) {
            //Creates the train line and adds in other train lines into the hashmap.
            String lineName = readline.substring(0, readline.indexOf(':'));
            String stations = readline.substring(readline.indexOf(':') + 1);
            String stationsNames[] = stations.split(",");
            trainLine.addLine(lineName, stationsNames);
            readline = fileReader.readLine();
        }
    }
}
