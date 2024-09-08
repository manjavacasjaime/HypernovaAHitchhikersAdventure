package hypernova.hithchhiker.guide.galaxy.managers;

import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveOne;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveZero;
import hypernova.hithchhiker.guide.galaxy.levels.common.ConsultGuide;

public class LevelManager {
    public ConsultGuide consultGuide;
    public ObjectiveZero objectiveZero;
    public ObjectiveOne objectiveOne;

    public LevelManager(ValueManager valManager) {
        consultGuide = new ConsultGuide();
        objectiveZero = new ObjectiveZero(valManager);
        objectiveOne = new ObjectiveOne(valManager);
    }
}
