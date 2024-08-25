package hypernova.hithchhiker.guide.galaxy.managers;

import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveZero;

public class LevelManager {

    public ObjectiveZero objectiveZero;

    public LevelManager (ValueManager valManager) {
        objectiveZero = new ObjectiveZero(valManager);
    }
}
