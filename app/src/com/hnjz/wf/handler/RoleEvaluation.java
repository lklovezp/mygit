package com.hnjz.wf.handler;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * ½ÇÉ«ÆÀ¹À
 * 
 * @author zn
 * @version $Id: RoleEvaluation.java, v 0.1 2013-1-11 ÉÏÎç06:58:38 Administrator
 *          Exp $
 */
public class RoleEvaluation implements DecisionHandler {

    /**  */
    private static final long serialVersionUID = 1L;

    @Override
    public String decide(OpenExecution arg0) {
	String myRole = (String) arg0.getVariable("myRole");
	String result = "to superior";
	if (myRole.equals("1")) {

	} else if (myRole.equals("2")) {
	    result = "to vice president";
	} else if (myRole.equals("3")) {
	    result = "to president";
	}
	return result;
    }

}
