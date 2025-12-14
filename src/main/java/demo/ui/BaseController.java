package demo.ui;

import demo.service.GeneratorService;

public abstract class BaseController {
    protected GeneratorService generatorService;

    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }
}
