package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);

            if (neighbour.isFavorite()) {
                favoriteNeighbours.add(neighbour);
            }
        }

        return favoriteNeighbours;
    }

    public void setFavorite(Neighbour neighbour, boolean favorite) {

        int index = 0;

        Neighbour foundNeighbour = null;


        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour currentNeighbour = neighbours.get(i);

            if (neighbour.getId() == currentNeighbour.getId()) {
                foundNeighbour = currentNeighbour;
                index = i;
                break;
            }
        }

        if (foundNeighbour != null) {
            foundNeighbour.setFavorite(favorite);

            neighbours.set(index,foundNeighbour);
        }
    }
}



