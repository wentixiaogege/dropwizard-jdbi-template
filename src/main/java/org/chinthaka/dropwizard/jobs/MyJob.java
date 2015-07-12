package org.chinthaka.dropwizard.jobs;

import io.dropwizard.lifecycle.setup.LifecycleEnvironment;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.chinthaka.dropwizard.api.beans.User;
import org.chinthaka.dropwizard.resources.UserResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.xeiam.sundial.Job;
import com.xeiam.sundial.SundialJobScheduler;
import com.xeiam.sundial.annotations.CronTrigger;
import com.xeiam.sundial.exceptions.JobInterruptException;


@CronTrigger(cron = "0/5 * * * * ?")
public class MyJob extends Job {

  private final Logger logger = LoggerFactory.getLogger(MyJob.class);

  @Override
  public void doRun() throws JobInterruptException {

    // pull object from ServletContext, which was added in the pllication's run method
//    String myObject = (String) SundialJobScheduler.getServletContext().getAttribute("MyKey");

	  
	/*User testUser = new User("23","jack","city");
	  
    UserResourceImpl userResourceImpl = (UserResourceImpl) SundialJobScheduler.getServletContext().getAttribute("MyKey");
//    logger.info("MyJob says: " + myObject);
    
    userResourceImpl.addUser(testUser);
   
    System.out.println("cron job"+ userResourceImpl.getUserById("23"));*/
	  
	final ExecutorService executor = (ExecutorService) SundialJobScheduler.getServletContext().getAttribute("ExecutorService");
	  
//	ExecutorService 
//    TestFuture testFuture =new TestFuture();
    
	Callable<String> asyncTask = new Callable<String>() {
		@Override
		public String call() throws Exception {

		System.out.println("running here");
		
		User testUser = new User("23","jack","city");
		  
	    UserResourceImpl userResourceImpl = (UserResourceImpl) SundialJobScheduler.getServletContext().getAttribute("MyKey");
//	    logger.info("MyJob says: " + myObject);
	    
	    userResourceImpl.addUser(testUser);
	   
	    System.out.println("cron job"+ userResourceImpl.getUserById("23"));
		
		return "ok";
		}
	};
	Future<String> future =   executor.submit(asyncTask);

//	ListenableFuture<String> listenableFuture = testFuture.submit(asyncTask);
		try {
			System.out.println(	"future.get();"+future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(	"future.get(); errors ");
			future.cancel(true);
		}
	/*try {
	    System.out.println(	"future.get();"+future.get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
  
  }
}
