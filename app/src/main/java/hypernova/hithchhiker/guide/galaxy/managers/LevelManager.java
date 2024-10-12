package hypernova.hithchhiker.guide.galaxy.managers;

import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveFive;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveFour;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveOne;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveThree;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveTwo;
import hypernova.hithchhiker.guide.galaxy.levels.ObjectiveZero;
import hypernova.hithchhiker.guide.galaxy.levels.common.CommonAnswers;
import hypernova.hithchhiker.guide.galaxy.levels.common.ConsultGuide;

public class LevelManager {
    public CommonAnswers commonAnswers;
    public ConsultGuide consultGuide;
    public ObjectiveZero objectiveZero;
    public ObjectiveOne objectiveOne;
    public ObjectiveTwo objectiveTwo;
    public ObjectiveThree objectiveThree;
    public ObjectiveFour objectiveFour;
    public ObjectiveFive objectiveFive;

    public LevelManager(ValueManager valManager) {
        commonAnswers = new CommonAnswers(valManager);
        consultGuide = new ConsultGuide();
        objectiveZero = new ObjectiveZero(valManager);
        objectiveOne = new ObjectiveOne(valManager);
        objectiveTwo = new ObjectiveTwo(valManager);
        objectiveThree = new ObjectiveThree(valManager);
        objectiveFour = new ObjectiveFour(valManager);
        objectiveFive = new ObjectiveFive(valManager);
    }
}
