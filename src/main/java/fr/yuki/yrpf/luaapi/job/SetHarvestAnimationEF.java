package fr.yuki.yrpf.luaapi.job;

import fr.yuki.yrpf.character.CharacterLoopAnimation;
import fr.yuki.yrpf.job.Job;
import fr.yuki.yrpf.job.harvest.CustomHarvestableObject;
import fr.yuki.yrpf.manager.JobManager;
import net.onfirenetwork.onsetjava.plugin.ExportFunction;

public class SetHarvestAnimationEF implements ExportFunction {
    @Override
    public Object call(Object[] objects) {
        Job job = JobManager.getJobs().get(objects[0].toString());
        if(job == null) return false;
        CustomHarvestableObject harvestableObject = (CustomHarvestableObject)job.getHarvestableObjectsTemplate().stream().filter(x ->
                x.getName().equals(objects[1].toString())).findFirst().orElse(null);
        if(harvestableObject == null) return false;

        CharacterLoopAnimation characterLoopAnimation = new CharacterLoopAnimation(objects[2].toString(),
                Integer.parseInt(objects[3].toString()), Integer.parseInt(objects[4].toString()), objects[5].toString());
        harvestableObject.setCharacterLoopAnimation(characterLoopAnimation);
        return null;
    }
}
