package hypernova.hithchhiker.guide.galaxy.managers;

import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveOne;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveZero;

public class LevelManager {

    public ObjectiveZero objectiveZero;
    public ObjectiveOne objectiveOne;

    public LevelManager(ValueManager valManager) {
        objectiveZero = new ObjectiveZero(valManager);
        objectiveOne = new ObjectiveOne(valManager);
    }
}
