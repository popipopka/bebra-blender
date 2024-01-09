package com.meow.bebrablender.render_engine;

import com.meow.bebrablender.math.MathUtils;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.points.Point2d;
import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.model.ModelContainer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

import static com.meow.bebrablender.render_engine.GraphicConveyor.vertexToPoint;

public class RenderEngine {

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final ModelContainer container,
            final int width,
            final int height) {
        Model mesh = container.getModel();
        GraphicConveyor conveyor = container.getConveyor();

        Matrix4d modelMatrix = conveyor.getModelMatrix();
        Matrix4d viewMatrix = camera.getViewMatrix();
        Matrix4d projectionMatrix = camera.getProjectionMatrix();

        Matrix4d modelViewProjectionMatrix = new Matrix4d(modelMatrix.data());
        modelViewProjectionMatrix.mul(viewMatrix);
        modelViewProjectionMatrix.mul(projectionMatrix);

        final int nPolygons = mesh.getPolygons().size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.getPolygons().get(polygonInd).getVertexIndices().size();

            List<Point2d> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3d vertex = mesh.getVertices().get(mesh.getPolygons().get(polygonInd).getVertexIndices().get(vertexInPolygonInd));

                Vector3d vertexVector = new Vector3d(vertex.x(), vertex.y(), vertex.z());

                Point2d resultPoint = vertexToPoint(MathUtils.mulMatrix4ByVector3(modelViewProjectionMatrix, vertexVector).norm(), width, height);
                resultPoints.add(resultPoint);
            }

            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).getX(),
                        resultPoints.get(vertexInPolygonInd - 1).getY(),
                        resultPoints.get(vertexInPolygonInd).getX(),
                        resultPoints.get(vertexInPolygonInd).getY());
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).getX(),
                        resultPoints.get(nVerticesInPolygon - 1).getY(),
                        resultPoints.getFirst().getX(),
                        resultPoints.getFirst().getY());
        }
    }
}