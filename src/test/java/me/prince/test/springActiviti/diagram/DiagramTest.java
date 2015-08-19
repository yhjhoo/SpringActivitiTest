package me.prince.test.springActiviti.diagram;

import static org.junit.Assert.assertNotNull;
import me.prince.test.springActiviti.engine.ProcessEngineTest;

import org.activiti.engine.*;
import org.activiti.engine.runtime.*;
import org.activiti.engine.test.*;
import org.activiti.engine.task.Task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DiagramTest extends ProcessEngineTest{

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Test
//	@Deployment(resources = {"org/activiti/test/ReceiveTaskTest.testWaitStateBehavior.bpmn"})
	public void testDiagram() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("receiveTask");
		assertNotNull(processInstance);
		
		Execution execution = runtimeService.createExecutionQuery()
			      .processInstanceId(processInstance.getId())
			      .activityId("waitState")
			      .singleResult();
		assertNotNull(execution);
		
		runtimeService.signal(execution.getId());
		System.out.println("\n1----------------------------------");
		for(Task tmp_tsk : taskService.createTaskQuery().list()){
			System.out.println(tmp_tsk.getName());
		}
		
	}

}
