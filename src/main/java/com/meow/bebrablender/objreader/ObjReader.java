package com.meow.bebrablender.objreader;

import com.meow.bebrablender.math.vectors.Vector2d;
import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.model.Polygon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ObjReader {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";

    private final Model result;
    private final Path path;
    private List<String> lines;

    public ObjReader(Path path) throws IOException {
        this.path = path;
        this.result = new Model();
        this.lines = Files.readAllLines(path);
    }

    public Model read() {
        loadInModel();
        checkPolygons();

        return result;
    }

    private void checkPolygons() {
        for (Polygon face : result.getPolygons()) {
            // проверка вершин
            checkVertInFace(face);
            // проверка нормалей
            checkNormInFace(face);
            // проверка текстурных вершин
            checkTextureInFace(face);

        }
    }

    private void checkTextureInFace(Polygon face) {
        int textureIndicesSize = result.getTextureVertices().size();

        if (!face.getTextureVertexIndices().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                int textureIndex = face.getTextureVertexIndices().get(i);
                if (textureIndex < 0) {
                    face.getTextureVertexIndices().set(i, result.getTextureVertices().size() + 1 + textureIndex);
                    textureIndex = face.getTextureVertexIndices().get(i);
                }
                if (textureIndex >= result.getTextureVertices().size() || textureIndex < 0) {
                    throw new ObjReaderException(
                            "The polygon is specified incorrectly: texture index out of bounds.",
                            findLineInObj(
                                    OBJ_TEXTURE_TOKEN,
                                    textureIndicesSize));
                }
            }
        }
    }

    private void checkVertInFace(Polygon face) {
        int vertexIndicesSize = result.getVertices().size();

        for (int i = 0; i < 3; i++) {
            // проверка на то, что индекс вершины полигона не содержится в массиве индексов модели
            int vertexIndex = face.getVertexIndices().get(i);
            // проверка на то, что вершина задана отрицательно
            if (vertexIndex < 0) {
                face.getVertexIndices().set(i, result.getVertices().size() + 1 + vertexIndex);
                vertexIndex = face.getVertexIndices().get(i);
            }
            // проверка на vertexIndex < 0 нужна потому, что на прошлом условии мы уже перевели индексы на положительные
            if (vertexIndex >= result.getVertices().size() || vertexIndex < 0) {
                throw new ObjReaderException(
                        "The polygon is specified incorrectly: vertex index out of bounds.",
                        findLineInObj(
                                OBJ_VERTEX_TOKEN,
                                vertexIndicesSize));
            }
        }
    }

    private void checkNormInFace(Polygon face) {
        int normalIndicesSize = result.getNormals().size();

        // проверка для нормалей
        if (!face.getNormalIndices().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                int normalIndex = face.getNormalIndices().get(i);
                if (normalIndex < 0) {
                    face.getNormalIndices().set(i, result.getNormals().size() + 1 + normalIndex);
                    normalIndex = face.getNormalIndices().get(i);
                }
                if (normalIndex >= result.getNormals().size() || normalIndex < 0) {
                    throw new ObjReaderException(
                            "The polygon is specified incorrectly: normal index out of bounds.",
                            findLineInObj(
                                    OBJ_NORMAL_TOKEN,
                                    normalIndicesSize));
                }
            }
        }
    }

    private void loadInModel() {
        String line;
        ArrayList<String> wordsInLine;
        String token;
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);

            String[] split = line.split("\\s+");
            if (split.length == 1) continue;

            wordsInLine = new ArrayList<>(Arrays.asList(split));

            token = wordsInLine.getFirst();
            if(!token.equals("v")&&!token.equals("vt")&&!token.equals("vn")&!token.equals("f"))continue;
            wordsInLine.removeFirst();

            switch (token) {
                case OBJ_VERTEX_TOKEN -> result.getVertices().add(parseVertex(wordsInLine, i));

                case OBJ_TEXTURE_TOKEN -> result.getTextureVertices().add(parseTextureVertex(wordsInLine, i));

                case OBJ_NORMAL_TOKEN -> result.getNormals().add(parseNormal(wordsInLine, i));

                case OBJ_FACE_TOKEN -> {
                    result.getPolygons().add(parseFace(wordsInLine, i));
                    if (result.getPolygons().size() > 1
                            && (result.getPolygons().get(result.getPolygons().size() - 2).getTextureVertexIndices().isEmpty())
                            != (result.getPolygons().getLast().getTextureVertexIndices().isEmpty())) {
                        throw new ObjReaderException("Polygon has no texture vertices.", i);
                    }
                }

                default -> throw new ObjReaderException("Invalid token", i);
            }
        }

        if (result.getPolygons().isEmpty()) throw new ObjReaderException("OBJ has not any polygons");

        if (result.getVertices().isEmpty()) throw new ObjReaderException("OBJ has not any vertices");
    }

    public Vector3d parseVertex(final List<String> lineWithoutToken, int lineInd) {
        try {
            if (lineWithoutToken.size() > 3) {
                throw new ObjReaderException("Too much vertex arguments.", lineInd);
            }

            return new Vector3d(
                    Double.parseDouble(lineWithoutToken.get(0)),
                    Double.parseDouble(lineWithoutToken.get(1)),
                    Double.parseDouble(lineWithoutToken.get(2)));

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse double value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few vertex arguments.", lineInd);
        }
    }

    public Vector2d parseTextureVertex(final List<String> lineWithoutToken, int lineInd) {
        try {
            if (lineWithoutToken.size() > 2
                    && (Math.signum(Double.parseDouble(lineWithoutToken.get(2))) != 0)) {

                throw new ObjReaderException("Too much texture vertex arguments.", lineInd);
            }
            return new Vector2d(
                    Double.parseDouble(lineWithoutToken.get(0)),
                    Double.parseDouble(lineWithoutToken.get(1))
            );

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse double value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few texture vertex arguments.", lineInd);
        }
    }

    public Vector3d parseNormal(final List<String> lineWithoutToken, int lineInd) {
        try {
            if (lineWithoutToken.size() > 3) {
                throw new ObjReaderException("Too much normal arguments.", lineInd);
            }
            return new Vector3d(
                    Double.parseDouble(lineWithoutToken.get(0)),
                    Double.parseDouble(lineWithoutToken.get(1)),
                    Double.parseDouble(lineWithoutToken.get(2))
            );

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse double value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few normal arguments.", lineInd);
        }
    }

    protected Polygon parseFace(final ArrayList<String> lineWithoutToken, int lineInd) {
        if (lineWithoutToken.size() < 3) {
            throw new ObjReaderException("Polygon has too few vertices.", lineInd);
        }
        List<Integer> faceVertexIndices = new ArrayList<>();
        List<Integer> faceTextureVertexIndices = new ArrayList<>();
        List<Integer> faceNormalIndices = new ArrayList<>();

        for (String s : lineWithoutToken) {
            parseFaceWord(s, faceVertexIndices, faceTextureVertexIndices, faceNormalIndices, lineInd);
        }

        if (findEqualites(faceVertexIndices)) {
            throw new ObjReaderException("The polygon can`t contain the same vertex indices", lineInd);
        }

        Polygon result = new Polygon();
        result.setVertexIndices(faceVertexIndices);
        if (!faceTextureVertexIndices.isEmpty()) result.setTextureVertexIndices(faceTextureVertexIndices);
        if (!faceNormalIndices.isEmpty()) result.setNormalIndices(faceNormalIndices);
        return result;
    }

    public void parseFaceWord(
            String wordInLine,
            List<Integer> faceVertexIndices,
            List<Integer> faceTextureVertexIndices,
            List<Integer> faceNormalIndices,
            int lineInd
    ) {
        try {
            String[] wordIndices = wordInLine.split("/");
            switch (wordIndices.length) {
                case 1 -> faceVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);

                case 2 -> {
                    faceVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
                    faceTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
                }

                case 3 -> {
                    faceVertexIndices.add(Integer.parseInt(wordIndices[0]) - 1);
                    faceNormalIndices.add(Integer.parseInt(wordIndices[2]) - 1);
                    if (!wordIndices[1].isEmpty()) {
                        faceTextureVertexIndices.add(Integer.parseInt(wordIndices[1]) - 1);
                    }
                }

                default -> throw new ObjReaderException("Invalid element size.", lineInd);

            }

        } catch (NumberFormatException e) {
            throw new ObjReaderException("Failed to parse int value.", lineInd);

        } catch (IndexOutOfBoundsException e) {
            throw new ObjReaderException("Too few arguments.", lineInd);
        }
    }

    public int findLineInObj(String token, Integer indexInPolygon) {
        int rows = 1;

        String line;
        String trueToken;
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            ArrayList<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split("\\s+")));
            if (wordsInLine.isEmpty()) {
                continue;
            }

            trueToken = wordsInLine.getFirst();
            wordsInLine.removeFirst();

            if (token.equals(trueToken)) {
                if (rows != indexInPolygon) {
                    rows++;
                } else {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean findEqualites(List<Integer> faceVertexIndices) {
        for (int i = 0; i < faceVertexIndices.size() - 1; i++) {
            for (int j = i + 1; j < faceVertexIndices.size(); j++) {
                if (Objects.equals(faceVertexIndices.get(i), faceVertexIndices.get(j))) return true;
            }
        }
        return false;
    }
}
