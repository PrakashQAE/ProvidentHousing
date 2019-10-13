package focuz.provident.pages;

import org.openqa.selenium.support.PageFactory;

import focuz.provident.wrappers.ProvidentWrapper;

public class Page_AffordableProjects extends ProvidentWrapper{
	
	public Page_AffordableProjects(){
		
		PageFactory.initElements(driver, this);
	}

}
