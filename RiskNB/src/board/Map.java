package board;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {

    private final Territory[] territories;
    private ArrayList<Integer>[] adjacencyList;
    private int[][] distances;

    public Map(int size) {
        this.territories = new Territory[size];
        this.adjacencyList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adjacencyList[i] = new ArrayList<>();
            territories[i] = new Territory(i);
        }
    }

    public void addNeighbour(int a, int b) {
        this.adjacencyList[a].add(b);
    }

    public Territory getTerritory(int a) {
        return this.territories[a];
    }

    public ArrayList<Integer> getNeighbours(int a) {
        return this.adjacencyList[a];
    }

    public Territory[] getTerritories() {
        return this.territories;
    }

    public boolean adjacent(int t1ID, int t2ID) {
        return this.adjacencyList[t1ID].contains(t2ID);
    }

    public void configureDistances() {
        this.distances = new int[49][49];
        for (int i = 1; i < 49; i++) {
            for (int j = 1; j < 49; j++) {
                if (this.getNeighbours(i).contains(j)) {
                    distances[i][j] = 1;
                } else {
                    distances[i][j] = 1000;
                }
            }
        }

        for (int i = 1; i < 49; i++) {
            distances[i][i] = 0;
        }

        for (int k = 1; k < 49; k++) {
            for (int i = 0; i < 49; i++) {
                for (int j = 0; j < 49; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
    }

    public int getDistance(int u, int v) {
        return this.distances[u][v];
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Map other = (Map) obj;
        return Arrays.deepEquals(this.territories, other.territories);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Arrays.deepHashCode(this.territories);
        return hash;
    }

    @Override
    public Map clone() {
        Map copy = new Map(this.territories.length);
        for (int i = 1; i < 49; i++) {
            copy.territories[i] = this.territories[i].clone();
        }
        copy.adjacencyList = this.adjacencyList;
        copy.distances = this.distances;

        return copy;
    }

}
