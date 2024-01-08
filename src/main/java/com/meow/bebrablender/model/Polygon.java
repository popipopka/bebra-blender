package com.meow.bebrablender.model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Integer> vertexIndices;
    private List<Integer> textureVertexIndices;
    private List<Integer> normalIndices;


    public Polygon() {
        vertexIndices = new ArrayList<>();
        textureVertexIndices = new ArrayList<>();
        normalIndices = new ArrayList<>();
    }

    public void setVertexIndices(List<Integer> vertexIndices) {
        if (vertexIndices.size() < 3) {
            throw new IllegalArgumentException("Кол-во индексов вершин меньше 3");
        }
        this.vertexIndices = vertexIndices;
    }

    public void setTextureVertexIndices(List<Integer> textureVertexIndices) {
        if (textureVertexIndices.size() < 3) {
            throw new IllegalArgumentException("Кол-во индексов текстурных вершин меньше 3");
        }
        this.textureVertexIndices = textureVertexIndices;
    }

    public void setNormalIndices(List<Integer> normalIndices) {
        if (normalIndices.size() < 3) {
            throw new IllegalArgumentException("Кол-во индексов нормалей меньше 3");
        }
        this.normalIndices = normalIndices;
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public List<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public List<Integer> getNormalIndices() {
        return normalIndices;
    }

    public int numberOfVertices() {
        return normalIndices.size();
    }

    public boolean isTriangle() {
        return vertexIndices.size() == 3;
    }
}
