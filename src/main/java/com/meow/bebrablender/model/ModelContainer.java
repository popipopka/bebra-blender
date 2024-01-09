package com.meow.bebrablender.model;

import com.meow.bebrablender.render_engine.GraphicConveyor;

public class ModelContainer {
    private Model model;
    private GraphicConveyor conveyor;

    public ModelContainer(Model model) {
        this.model = model;
        this.conveyor = new GraphicConveyor();
    }

    public Model getModel() {
        return model;
    }

    public GraphicConveyor getConveyor() {
        return conveyor;
    }
}
