package com.example.demo;

/*
 * Abiklass otsingute teksti hoidmiseks, mis ekraanilst sesestatakse
 */
public class Content {

	private String content;
	
	public Content() {
		
	}

	public Content(String value) {
		this.content=value.trim();
	}
	
	
    public String getContent() {
    	if (this.content == null)
    	{
    		this.content="";
    	}
        return content.trim();
    }
    
    public void setContent(String content) {
        this.content = content.trim();
    }

}
