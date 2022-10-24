package labs.lab01;

import labs.DSLab20222023Publicmaster.DSLab01ArrayPointList.PointList;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PointListCommandLine {

    public static void main(String[] args) throws IOException {
        PointList polygon = new ArrayPointList();  		// Set of vertices for a polygon
        Point vertex;
        // Initialize reader and tokenizer for the input stream
        // for reading 'tokens' (namely point values) input from the keyboard.
        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokens = new StreamTokenizer(reader);
        // Use the tokenizer's nextToken( ) method to step through a stream of tokens.
        // Use the tokenizer's instance variable nval to obtain the number read.
        // Since nval is of type double, cast it to an int when reading points x and y (int)tokens.nval
        // Read in the polygon's vertices.
        // Keep reading as long as word (not number!) has not been entered
        while (true){
            tokens.nextToken();
            switch (tokens.sval) {
                case "add":
                    vertex = new Point();
                    tokens.nextToken();
                    vertex.x = (int)tokens.nval;
                    tokens.nextToken();
                    vertex.y = (int)tokens.nval;
                    polygon.append(vertex);
                    break;
                case "curr":
                    vertex = polygon.getCursor();
                    System.out.println("(" + vertex.x + "," + vertex.y + ")");
                    break;
                case "next":
                    System.out.println(polygon.goToNext());
                    break;
                case "prev":
                    polygon.goToPrior();
                    break;
                case "start":
                    System.out.println(polygon.goToBeginning());
                    break;
                case "end":
                    System.out.println(polygon.goToEnd());
                    break;
                case "empty":
                    System.out.println(polygon.isEmpty());
                    break;
                case "full":
                    System.out.println(polygon.isFull());
                    break;
                case "clear":
                    polygon.clear();
                    break;
                case "quit":
                    return;
            }
        }
    }
}
