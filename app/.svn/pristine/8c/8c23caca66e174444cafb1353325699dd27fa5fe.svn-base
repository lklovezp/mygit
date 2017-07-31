package com.hnjz.wf.handler;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * ÌìÊýÆÀ¹À
 * 
 * @author zn
 * @version $Id: RoleEvaluation.java, v 0.1 2013-1-11 ÉÏÎç06:58:38 Administrator
 *          Exp $
 */
public class DayEvaluation implements DecisionHandler {

    /**  */
    private static final long serialVersionUID = 1L;

    @Override
    public String decide(OpenExecution arg0) {
	Integer myDay = (Integer) arg0.getVariable("myDay");
	String result = "superior to end";
	if (myDay <= 1) {

	} else {
	    result = "to vice president";
	}
	return result;
    }

}
