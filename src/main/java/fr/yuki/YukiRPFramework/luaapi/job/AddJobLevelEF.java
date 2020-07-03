package fr.yuki.YukiRPFramework.luaapi.job;

import fr.yuki.YukiRPFramework.job.CustomJob;
import fr.yuki.YukiRPFramework.job.Job;
import fr.yuki.YukiRPFramework.manager.JobManager;
import fr.yuki.YukiRPFramework.model.JobLevel;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.plugin.ExportFunction;

public class AddJobLevelEF implements ExportFunction {
    public static int tempJobLevelId = -1;

    @Override
    public Object call(Object[] objects) {
        Job job = JobManager.getJobs().get(objects[0].toString());
        if(job == null) return false;
        JobLevel jobLevel = new JobLevel();
        jobLevel.setId(tempJobLevelId--);
        jobLevel.setJobId(objects[0].toString());
        jobLevel.setName(objects[1].toString());
        jobLevel.setLevel(Integer.parseInt(objects[2].toString()));
        jobLevel.setExpFloor(Integer.parseInt(objects[3].toString()));
        JobManager.getJobLevels().add(jobLevel);
        Onset.print("Added new job level for job="+jobLevel.getJobId());
        return true;
    }
}
