package org.chinthaka.dropwizard.jobs;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;

@OnApplicationStart
public class ApplicationStartTestJob extends Job {

    public static final List<String> results = Lists.newArrayList();

    @Override
    public void doJob() {
//    	while(true){
    		 results.add(getClass().getName() + " " + new Date());
    	     System.out.println("System start2222222222222222");
    	    /* try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}*/
       
    }
}
