package com.glanexdev.blog.adsscript.parser;
/* © 02/03/2018 created by Hendriyawan
* ScriptParser.java 
* parser adsense script
*/
public class ScriptParser
{
	private String code;
	/**public contructor
	* ScriptParser
	*/
	public ScriptParser(){
		
	}
	
	/* doParser
	* @param code
	*/
	public void doParser(String code){
		this.code = code;
	}
	
	/* get script ads parsed*/
	public String getParsedCode(){
		String parsed = code.replace("<", "&lt;").replace(">","&gt;").replace("\"", "&quot;").replace("\'", "&#39;");
		return parsed;
	}
}
