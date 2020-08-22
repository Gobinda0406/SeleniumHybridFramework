package com.techauto.framework;

import driver.AutoDriver;

public abstract class ScriptLibrary {

	protected DataProvider dataProvider;
	protected Router router;
	protected AutoDriver driver;
    protected AutoReport result;
	

	/**
	 * Constructor is to initialize {@link Router} Object, it encapsulates
	 * dependent Object required to develop a script
	 * 
	 * @param router 
	 */
	public ScriptLibrary(Router router) {
		this.router=router;
		this.dataProvider = router.getDataProvider();
		this.driver=router.getAutoDriver();
		this.result=router.getResult();
	}
	
	

}
